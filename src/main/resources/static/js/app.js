// Carrega prescrições ao iniciar
document.addEventListener('DOMContentLoaded', function() {
    carregarPrescricoes();
});

// Função para carregar prescrições
function carregarPrescricoes() {
    fetch('/api/prescricoes', {
        headers: {
            'Authorization': 'Basic ' + btoa('admin:admin')
        }
    })
    .then(response => {
        if (!response.ok) throw new Error('Erro ao carregar prescrições');
        return response.json();
    })
    .then(data => {
        renderizarPrescricoes(data);
    })
    .catch(error => {
        console.error('Erro:', error);
        mostrarMensagemVazia();
    });
}

// Renderiza lista de prescrições
function renderizarPrescricoes(prescricoes) {
    const lista = document.getElementById('listaPrescricoes');
    const mensagemVazia = document.getElementById('mensagemVazia');
    
    if (!prescricoes || prescricoes.length === 0) {
        lista.innerHTML = '';
        mensagemVazia.style.display = 'block';
        return;
    }
    
    mensagemVazia.style.display = 'none';
    
    // Detectar duplicados por ID_PROCEDIMENTO
    const duplicados = detectarDuplicados(prescricoes);
    
    lista.innerHTML = prescricoes.map(p => criarCardPrescricao(p, duplicados)).join('');
    feather.replace();
}

// Detecta procedimentos duplicados
function detectarDuplicados(prescricoes) {
    const contador = {};
    const duplicados = new Set();
    
    prescricoes.forEach(p => {
        if (p.idProcedimento) {
            contador[p.idProcedimento] = (contador[p.idProcedimento] || 0) + 1;
        }
    });
    
    // Marcar IDs que aparecem mais de uma vez
    Object.keys(contador).forEach(id => {
        if (contador[id] > 1) {
            duplicados.add(parseInt(id));
        }
    });
    
    return duplicados;
}

// Cria card para uma prescrição
function criarCardPrescricao(p, duplicados) {
    const isReprovado = p.statusAprovacao === 'REPROVADO';
    const isDuplicado = duplicados.has(p.idProcedimento);
    const cardClass = isReprovado ? 'prescricao-card reprovado' : 'prescricao-card';
    const botaoCancelarTexto = isDuplicado ? 'Excluir' : 'Cancelar';
    const botaoCancelarIcone = isDuplicado ? 'fa-trash-alt' : 'fa-trash';
    
    return `
        <div class="${cardClass}">
            <div class="prescricao-header">
                <div class="prescricao-id">
                    <i class="fas fa-file-medical"></i> Prontuário #${p.id}
                </div>
                <div>
                    ${getBadgeStatus(p.statusAprovacao)}
                </div>
            </div>
            
            ${isDuplicado ? `
            <div class="alert alert-warning mb-3 mt-3" style="border-left: 4px solid #fbbf24; background-color: #fff3cd; color: #856404; border-radius: 8px; padding: 0.75rem 1rem; font-weight: 500;">
                <i class="fas fa-exclamation-triangle"></i> <strong>Procedimento duplicado.</strong>
            </div>
            ` : ''}
            
            <div class="prescricao-info" style="grid-template-columns: repeat(3, 1fr);">
                <div class="info-item">
                    <span class="info-label"><i class="fas fa-hashtag"></i> ID Procedimento</span>
                    <span class="info-value destaque">${p.idProcedimento || 'N/A'}</span>
                </div>
                <div class="info-item">
                    <span class="info-label"><i class="fas fa-procedures"></i> Procedimento</span>
                    <span class="info-value">${p.procedimento || 'N/A'}</span>
                </div>
                <div class="info-item">
                    <span class="info-label"><i class="fas fa-file-alt"></i> Descrição</span>
                    <span class="info-value">${p.textoPrescricao || 'Sem descrição'}</span>
                </div>
            </div>
            
            ${isReprovado && p.motivoReprovacao ? `
            <div class="alert alert-danger mb-3 mt-3">
                <strong><i class="fas fa-exclamation-triangle"></i> Motivo da Reprovação:</strong><br>
                ${p.motivoReprovacao}
            </div>
            ` : ''}
            
            <div class="prescricao-actions">
                ${p.statusAprovacao === 'PENDENTE' ? `
                <button class="btn btn-success btn-sm" onclick="abrirModalPrescrever(${p.id})">
                    Prescrever
                </button>
                ` : ''}
                <button class="btn btn-info btn-sm" onclick="abrirModalVisualizar(${p.id})">
                    Visualizar
                </button>
                ${p.statusAprovacao === 'REPROVADO' ? `
                <button class="btn btn-warning btn-sm" onclick="abrirModalCorrigir(${p.id})">
                    Corrigir
                </button>
                ` : ''}
            </div>
        </div>
    `;
}

// Retorna badge de status
function getBadgeStatus(status) {
    switch(status) {
        case 'PENDENTE':
            return '<span class="badge bg-warning"><i class="fas fa-clock"></i> Pendente</span>';
        case 'REPROVADO':
            return '<span class="badge bg-danger"><i class="fas fa-times-circle"></i> Reprovado</span>';
        case 'APROVADO':
            return '<span class="badge bg-success"><i class="fas fa-check-circle"></i> Aprovado</span>';
        default:
            return '<span class="badge bg-warning"><i class="fas fa-clock"></i> Pendente</span>';
    }
}

// Mostra mensagem quando não há prescrições
function mostrarMensagemVazia() {
    document.getElementById('listaPrescricoes').innerHTML = '';
    document.getElementById('mensagemVazia').style.display = 'block';
}

// Formatadores
function formatarData(data) {
    if (!data) return 'N/A';
    const d = new Date(data);
    return d.toLocaleDateString('pt-BR');
}

function formatarHora(hora) {
    if (!hora) return 'N/A';
    return hora;
}

function formatarValor(valor) {
    if (!valor) return 'R$ 0,00';
    return 'R$ ' + parseFloat(valor).toFixed(2).replace('.', ',');
}

// Modal Visualizar
function abrirModalVisualizar(id) {
    fetch(`/api/prescricoes/${id}`, {
        headers: {
            'Authorization': 'Basic ' + btoa('admin:admin')
        }
    })
    .then(response => response.json())
    .then(p => {
        document.getElementById('viewId').textContent = p.id;
        document.getElementById('viewIdPaciente').textContent = p.idPaciente || 'N/A';
        document.getElementById('viewStatus').innerHTML = getBadgeStatus(p.statusAprovacao);
        document.getElementById('viewPaciente').textContent = p.nomePaciente || 'N/A';
        document.getElementById('viewProfissional').textContent = p.nomeProfissional || 'N/A';
        document.getElementById('viewEspecialidade').textContent = p.especialidade || 'N/A';
        document.getElementById('viewProcedimento').textContent = p.procedimento || 'N/A';
        document.getElementById('viewDescricao').textContent = p.textoPrescricao || 'Sem descrição';
        
        const supervisorInfo = document.getElementById('supervisorInfo');
        if (p.statusAprovacao === 'REPROVADO' && p.idSupervisor) {
            supervisorInfo.style.display = 'block';
            document.getElementById('viewSupervisor').textContent = p.nomeSupervisor || 'N/A';
            document.getElementById('viewDataDecisao').textContent = formatarData(p.dataDecisao);
            document.getElementById('viewMotivoReprovacao').textContent = p.motivoReprovacao || 'N/A';
        } else {
            supervisorInfo.style.display = 'none';
        }
        
        new bootstrap.Modal(document.getElementById('modalVisualizar')).show();
    })
    .catch(error => console.error('Erro:', error));
}

// Modal Prescrever (antigo Editar - para prontuários PENDENTES)
function abrirModalPrescrever(id) {
    fetch(`/api/prescricoes/${id}`, {
        headers: {
            'Authorization': 'Basic ' + btoa('admin:admin')
        }
    })
    .then(response => response.json())
    .then(p => {
        document.getElementById('editId').value = p.id;
        document.getElementById('editDescricao').value = p.textoPrescricao || '';
        
        new bootstrap.Modal(document.getElementById('modalEditar')).show();
    })
    .catch(error => {
        console.error('Erro ao abrir modal prescrever:', error);
        alert('Erro ao abrir modal de prescrição');
    });
}

function enviarEdicao() {
    const id = parseInt(document.getElementById('editId').value);
    const textoPrescricao = document.getElementById('editDescricao').value;
    
    if (!textoPrescricao || textoPrescricao.trim() === '') {
        alert('Por favor, preencha a prescrição!');
        return;
    }
    
    const dados = {
        id: id,
        textoPrescricao: textoPrescricao
    };
    
    fetch('/api/prescricoes/editar', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Basic ' + btoa('admin:admin')
        },
        body: JSON.stringify(dados)
    })
    .then(response => {
        if (response.ok) {
            bootstrap.Modal.getInstance(document.getElementById('modalEditar')).hide();
            carregarPrescricoes();
            alert('Prescrição salva com sucesso!');
        } else {
            return response.text().then(text => {
                alert('Erro ao salvar prescrição: ' + text);
            });
        }
    })
    .catch(error => {
        console.error('Erro:', error);
        alert('Erro ao salvar prescrição');
    });
}

// Modal Corrigir
function abrirModalCorrigir(id) {
    fetch(`/api/prescricoes/${id}`, {
        headers: {
            'Authorization': 'Basic ' + btoa('admin:admin')
        }
    })
    .then(response => response.json())
    .then(p => {
        document.getElementById('corrigirId').value = p.id;
        document.getElementById('corrigirMotivo').textContent = p.motivoReprovacao || 'Não especificado';
        document.getElementById('corrigirDescricao').value = p.textoPrescricao || '';
        
        new bootstrap.Modal(document.getElementById('modalCorrigir')).show();
    })
    .catch(error => console.error('Erro:', error));
}

function enviarCorrecao() {
    const dados = {
        id: parseInt(document.getElementById('corrigirId').value),
        textoPrescricao: document.getElementById('corrigirDescricao').value
    };
    
    fetch('/api/prescricoes/corrigir', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Basic ' + btoa('admin:admin')
        },
        body: JSON.stringify(dados)
    })
    .then(response => {
        if (response.ok) {
            bootstrap.Modal.getInstance(document.getElementById('modalCorrigir')).hide();
            carregarPrescricoes();
            alert('Correção enviada com sucesso!');
        } else {
            alert('Erro ao corrigir prontuário');
        }
    })
    .catch(error => console.error('Erro:', error));
}


