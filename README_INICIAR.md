# Como Iniciar o Sistema

## SEMPRE use o script de inicialização:

### Windows PowerShell:
```powershell
cd d:\Sistemas\prescricao_medicamentos
.\iniciar.ps1
```

## O script faz automaticamente:
1. ✅ Para todos processos Java
2. ✅ Libera a porta 8080
3. ✅ Verifica se está livre
4. ✅ Inicia a aplicação

## Acesse no navegador:
**http://localhost:8080/index.html**

---

## ⚠️ IMPORTANTE SOBRE LOMBOK:

**LOMBOK FOI REMOVIDO DO PROJETO!**

- **Motivo:** Java 24 é incompatível com Lombok 1.18.36
- **Solução:** Todos getters/setters foram implementados MANUALMENTE
- **Classes afetadas:**
  - ✅ Paciente.java
  - ✅ Pessoa.java
  - ✅ Profissional.java
  - ✅ Especialidade.java
  - ✅ Procedimento.java
  - ✅ ProntuarioTemporario.java
  - ✅ Usuario.java

**Se o coordenador exigir Lombok:**
1. Fazer downgrade para Java 17
2. Reativar dependência Lombok no pom.xml
3. Substituir getters/setters manuais por @Data/@Getter/@Setter

---

## Tecnologias Utilizadas:
- ✅ Spring Boot 3.4.0
- ✅ Java 24.0.2
- ✅ MySQL 8.0.44
- ✅ JPA/Hibernate
- ✅ Bootstrap 5.3
- ✅ REST API
- ❌ Lombok (removido por incompatibilidade)
