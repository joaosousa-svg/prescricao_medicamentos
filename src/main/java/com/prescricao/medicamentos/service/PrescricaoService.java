package com.prescricao.medicamentos.service;

import com.prescricao.medicamentos.dto.CorrigirPrescricaoRequest;
import com.prescricao.medicamentos.dto.PrescricaoDTO;
import com.prescricao.medicamentos.model.*;
import com.prescricao.medicamentos.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrescricaoService {

    private final ProntuarioTemporarioRepository prontuarioRepository;
    private final PacienteRepository pacienteRepository;
    private final PessoaRepository pessoaRepository;
    private final ProfissionalRepository profissionalRepository;
    private final EspecialidadeRepository especialidadeRepository;
    private final ProcedimentoRepository procedimentoRepository;
    private final UserContextService userContext;

    public PrescricaoService(ProntuarioTemporarioRepository prontuarioRepository,
                            PacienteRepository pacienteRepository,
                            PessoaRepository pessoaRepository,
                            ProfissionalRepository profissionalRepository,
                            EspecialidadeRepository especialidadeRepository,
                            ProcedimentoRepository procedimentoRepository,
                            UserContextService userContext) {
        this.prontuarioRepository = prontuarioRepository;
        this.pacienteRepository = pacienteRepository;
        this.pessoaRepository = pessoaRepository;
        this.profissionalRepository = profissionalRepository;
        this.especialidadeRepository = especialidadeRepository;
        this.procedimentoRepository = procedimentoRepository;
        this.userContext = userContext;
    }

    /**
     * FUNCIONALIDADE 1: Listar Prescrições
     * Filtro: WHERE ID_PROFISSIO = profissionalId do contexto
     * Busca apenas dados essenciais: ID, Paciente, Data, Status
     */
    @Transactional(readOnly = true)
    public List<PrescricaoDTO> getMinhasPrescricoes() {
        Integer profissionalId = userContext.getProfissionalId();
        
        List<ProntuarioTemporario> prontuarios = prontuarioRepository.findByProfissional(profissionalId);
        List<PrescricaoDTO> dtos = new ArrayList<>();

        for (ProntuarioTemporario p : prontuarios) {
            PrescricaoDTO dto = new PrescricaoDTO();
            dto.setId(p.getId());
            dto.setIdPaciente(p.getPaciente());
            dto.setDataProcedimento(p.getDataProcedimento());
            dto.setTextoPrescricao(p.getTextoPrescricao());
            dto.setLinkProced(p.getLinkProced());
            dto.setAutoPacVisu(p.getAutoPacVisu());
            dto.setStatus(p.getStatusAprovacao() != null ? p.getStatusAprovacao().name() : "PENDENTE");
            dto.setStatusAprovacao(p.getStatusAprovacao() != null ? p.getStatusAprovacao().name() : "PENDENTE");
            dto.setMotivoReprovacao(p.getMotivoReprovacao());

            // BUSCAR NOME DO PACIENTE
            Integer pacienteId = p.getPaciente();
            if (pacienteId != null) {
                pacienteRepository.findById(pacienteId).ifPresent(paciente -> {
                    Long idDoc = paciente.getIdDocumento();
                    if (idDoc != null) {
                        pessoaRepository.findByIdDocumento(idDoc).ifPresent(pessoa -> {
                            dto.setNomePaciente(pessoa.getNome());
                        });
                    }
                });
            }

            // BUSCAR NOME DO PROFISSIONAL
            Integer profId = p.getProfissional();
            if (profId != null) {
                profissionalRepository.findById(profId).ifPresent(prof -> {
                    Long idDoc = prof.getIdDocumento();
                    if (idDoc != null) {
                        pessoaRepository.findByIdDocumento(idDoc).ifPresent(pessoa -> {
                            dto.setNomeProfissional(pessoa.getNome());
                        });
                    }
                });
            }

            // BUSCAR ESPECIALIDADE
            Integer especId = p.getEspecialidade();
            if (especId != null) {
                especialidadeRepository.findById(especId).ifPresent(espec -> {
                    dto.setEspecialidade(espec.getDescricao());
                    dto.setCodigoEspecialidade(espec.getCodigo());
                });
            }

            // BUSCAR PROCEDIMENTO
            Integer procId = p.getProcedimento();
            if (procId != null) {
                dto.setIdProcedimento(procId);
                procedimentoRepository.findById(procId).ifPresent(proc -> {
                    dto.setProcedimento(proc.getDescricao());
                    dto.setCodigoProcedimento(proc.getCodigo());
                    dto.setValorProcedimento(proc.getValor());
                });
            }

            // SE REPROVADO, BUSCAR DADOS DO SUPERVISOR
            if (p.getStatusAprovacao() == ProntuarioTemporario.StatusAprovacao.REPROVADO) {
                dto.setDataDecisao(p.getDataDecisao());
                Integer supId = p.getIdSupervisor();
                if (supId != null) {
                    dto.setIdSupervisor(supId);
                    profissionalRepository.findById(supId).ifPresent(sup -> {
                        Long idDoc = sup.getIdDocumento();
                        if (idDoc != null) {
                            pessoaRepository.findByIdDocumento(idDoc).ifPresent(pessoa -> {
                                dto.setNomeSupervisor(pessoa.getNome());
                            });
                        }
                    });
                }
            }

            dtos.add(dto);
        }

        return dtos;
    }



    /**
     * FUNCIONALIDADE 2: Corrigir Prescrição Reprovada
     * - Atualiza DESCRPRONTU (texto da prescrição)
     * - Reseta STATUS_APROVACAO para "PENDENTE"
     * - Limpa MOTIVO_REPROVACAO e DATA_DECISAO
     */
    @Transactional
    public ProntuarioTemporario corrigirPrescricao(CorrigirPrescricaoRequest request) {
        Integer prescricaoId = request.getId();
        if (prescricaoId == null) {
            throw new RuntimeException("ID da prescrição não pode ser nulo");
        }
        ProntuarioTemporario prontuario = prontuarioRepository.findById(prescricaoId)
                .orElseThrow(() -> new RuntimeException("Prescrição não encontrada"));

        prontuario.setTextoPrescricao(request.getTextoPrescricao());
        prontuario.setStatusAprovacao(ProntuarioTemporario.StatusAprovacao.PENDENTE);
        prontuario.setMotivoReprovacao(null);
        prontuario.setDataDecisao(null);

        return prontuarioRepository.save(prontuario);
    }

    /**
     * FUNCIONALIDADE 3: Editar Prescrição Pendente
     * - Permite edição ANTES do supervisor analisar
     * - Apenas para prescrições com STATUS_APROVACAO = PENDENTE
     */
    @Transactional
    public ProntuarioTemporario editarPrescricao(CorrigirPrescricaoRequest request) {
        Integer prescricaoId = request.getId();
        if (prescricaoId == null) {
            throw new RuntimeException("ID da prescrição não pode ser nulo");
        }
        ProntuarioTemporario prontuario = prontuarioRepository.findById(prescricaoId)
                .orElseThrow(() -> new RuntimeException("Prescrição não encontrada"));

        // Validar que está PENDENTE
        if (prontuario.getStatusAprovacao() != ProntuarioTemporario.StatusAprovacao.PENDENTE) {
            throw new RuntimeException("Apenas prescrições PENDENTES podem ser editadas. Use corrigir para prescrições reprovadas.");
        }

        prontuario.setTextoPrescricao(request.getTextoPrescricao());
        return prontuarioRepository.save(prontuario);
    }

    /**
     * FUNCIONALIDADE 4: Cancelar Prescrição
     * - DELETE direto de PRONTUARIO_TEMPORARIO
     * - Sem volta! Registro é apagado permanentemente
     */
    @Transactional
    public void cancelarPrescricao(Integer prescricaoId) {
        if (prescricaoId == null) {
            throw new RuntimeException("ID da prescrição não pode ser nulo");
        }
        
        ProntuarioTemporario prontuario = prontuarioRepository.findById(prescricaoId)
                .orElseThrow(() -> new RuntimeException("Prescrição não encontrada"));
        
        prontuarioRepository.delete(prontuario);
    }

    /**
     * DEBUG: Verificar dados no banco
     */
    @Transactional(readOnly = true)
    public String debugDados() {
        Integer profissionalId = userContext.getProfissionalId();
        long totalProntuarios = prontuarioRepository.count();
        List<ProntuarioTemporario> meusProntuarios = prontuarioRepository.findByProfissional(profissionalId);
        
        return String.format(
            "Profissional ID: %d | Total prontuários no banco: %d | Meus prontuários: %d", 
            profissionalId, totalProntuarios, meusProntuarios.size()
        );
    }
}
