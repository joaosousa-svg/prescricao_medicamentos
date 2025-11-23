# 🏥 Módulo de Prescrição de Medicamentos

Sistema de gerenciamento de prescrições médicas desenvolvido com **Spring Boot 3.4.0** e **MySQL 8.0**.

## 🚀 Tecnologias

- **Java 17**
- **Spring Boot 3.4.0**
- **Spring Data JPA**
- **MySQL 8.0** (Banco Legado)
- **Lombok**
- **Bootstrap 5.3**

## ⚙️ Configuração

### 1. Configurar Banco de Dados

Edite o arquivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/SEU_BANCO
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
```

⚠️ **IMPORTANTE**: O banco é **legado** e não será alterado (`ddl-auto=none`).

### 2. Estrutura do Banco

O sistema espera as seguintes tabelas:

#### Tabelas de Leitura
- `DOCUMENTO`
- `PESSOA` (contém NOMEPESSOA)
- `PESSOAFIS`
- `PESSOAJUR` (contém NOMEFAN - Nome do Profissional)
- `PACIENTE`
- `PROFISSIONAL`
- `USUARIO`
- `ESPECIALIDADE`
- `PROCEDIMENTO`

#### Tabela de Escrita
- `PRONTUARIO_TEMPORARIO` (com campos de controle de aprovação)

### 3. Executar o Projeto

```bash
# Compilar
mvn clean install

# Executar
mvn spring-boot:run
```

O servidor estará disponível em: `http://localhost:8080`

## 📋 Funcionalidades

### Backend (REST API)

- `GET /api/prescricoes` - Lista prescrições do profissional logado
- `POST /api/prescricoes` - Salva nova prescrição
- `PUT /api/prescricoes/corrigir` - Corrige prescrição reprovada

### Frontend

Acesse: `http://localhost:8080/index.html`

**Features:**
- ✅ Listagem de prescrições
- ✅ Linhas vermelhas para prescrições reprovadas
- ✅ Modal para criar nova prescrição
- ✅ Modal para corrigir prescrições com alerta do motivo
- ✅ Design com cores suaves (branco + azul claro)
- ✅ Botão "Corrigir" laranja (psicologia das cores)

## 🔐 Usuário Mock

O sistema simula um usuário logado:

- **Usuário ID**: 60
- **Profissional ID**: 99
- **Especialidade ID**: 11
- **Tipo**: Técnico Básico (2)

## 📊 Fluxo de Aprovação

1. **PENDENTE** → Prescrição criada, aguardando aprovação
2. **APROVADO** → Prescrição aprovada pelo supervisor
3. **REPROVADO** → Prescrição reprovada, pode ser corrigida

Ao corrigir, o status volta para **PENDENTE**.

## 🎨 Design

- Cores suaves: branco + azul claro (#E8F4F8)
- Linhas vermelhas para reprovações
- Botão "Corrigir" laranja (#FF9800) - ação corretiva

## 📁 Estrutura do Projeto

```
src/main/java/com/prescricao/medicamentos/
├── PrescricaoMedicamentosApplication.java
├── controller/
│   └── PrescricaoController.java
├── dto/
│   ├── PrescricaoDTO.java
│   ├── SalvarPrescricaoRequest.java
│   └── CorrigirPrescricaoRequest.java
├── model/
│   ├── Documento.java
│   ├── Pessoa.java
│   ├── PessoaFisica.java
│   ├── PessoaJuridica.java
│   ├── Paciente.java
│   ├── Profissional.java
│   ├── Usuario.java
│   ├── Especialidade.java
│   ├── Procedimento.java
│   └── ProntuarioTemporario.java
├── repository/
│   ├── ProntuarioTemporarioRepository.java
│   ├── PacienteRepository.java
│   ├── ProfissionalRepository.java
│   ├── PessoaRepository.java
│   ├── PessoaJuridicaRepository.java
│   ├── EspecialidadeRepository.java
│   └── ProcedimentoRepository.java
└── service/
    ├── UserContextService.java
    └── PrescricaoService.java

src/main/resources/
├── application.properties
└── static/
    └── index.html
```

## 📝 Notas Importantes

1. O nome do **Paciente** vem de: `PRONTUARIO_TEMPORARIO → PACIENTE → IDDOCUMENTO → PESSOA.NOMEPESSOA`

2. O nome do **Profissional** vem de: `PRONTUARIO_TEMPORARIO → PROFISSIONAL → IDDOCUMENTO → PESSOAJUR.NOMEFAN`

3. O sistema **NÃO modifica** a estrutura do banco (`ddl-auto=none`)

4. A estratégia de nomenclatura é: `PhysicalNamingStrategyStandardImpl` (mantém nomes originais)

## 🐛 Troubleshooting

- **Erro de conexão**: Verifique as credenciais no `application.properties`
- **Tabela não encontrada**: Confirme que todas as tabelas existem no banco
- **CORS**: O backend está configurado com `@CrossOrigin(origins = "*")`

---

**Desenvolvido com ❤️ para sistemas de saúde universitários**
