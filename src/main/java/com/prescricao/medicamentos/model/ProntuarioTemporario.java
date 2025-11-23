package com.prescricao.medicamentos.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRONTUARIO_TEMPORARIO")
public class ProntuarioTemporario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPRONTU_TEMP")
    private Integer id;

    @Column(name = "ID_PACIENTE")
    private Integer paciente;

    @Column(name = "ID_PROFISSIO")
    private Integer profissional;

    @Column(name = "ID_ESPEC")
    private Integer especialidade;

    @Column(name = "ID_PROCED")
    private Integer procedimento;

    @Column(name = "DATAPROCED")
    private LocalDateTime dataProcedimento;

    @Column(name = "DESCRPRONTU", columnDefinition = "TEXT")
    private String textoPrescricao;

    @Column(name = "LINKPROCED")
    private String linkProced;

    @Column(name = "AUTOPACVISU")
    private String autoPacVisu;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS_APROVACAO")
    private StatusAprovacao statusAprovacao;

    @Column(name = "ID_SUPERVISOR")
    private Integer idSupervisor;

    @Column(name = "DATA_DECISAO")
    private LocalDateTime dataDecisao;

    @Column(name = "MOTIVO_REPROVACAO")
    private String motivoReprovacao;

    public enum StatusAprovacao {
        PENDENTE,
        APROVADO,
        REPROVADO
    }

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getPaciente() { return paciente; }
    public void setPaciente(Integer paciente) { this.paciente = paciente; }

    public Integer getProfissional() { return profissional; }
    public void setProfissional(Integer profissional) { this.profissional = profissional; }

    public Integer getEspecialidade() { return especialidade; }
    public void setEspecialidade(Integer especialidade) { this.especialidade = especialidade; }

    public Integer getProcedimento() { return procedimento; }
    public void setProcedimento(Integer procedimento) { this.procedimento = procedimento; }

    public LocalDateTime getDataProcedimento() { return dataProcedimento; }
    public void setDataProcedimento(LocalDateTime dataProcedimento) { this.dataProcedimento = dataProcedimento; }

    public String getTextoPrescricao() { return textoPrescricao; }
    public void setTextoPrescricao(String textoPrescricao) { this.textoPrescricao = textoPrescricao; }

    public String getLinkProced() { return linkProced; }
    public void setLinkProced(String linkProced) { this.linkProced = linkProced; }

    public String getAutoPacVisu() { return autoPacVisu; }
    public void setAutoPacVisu(String autoPacVisu) { this.autoPacVisu = autoPacVisu; }

    public StatusAprovacao getStatusAprovacao() { return statusAprovacao; }
    public void setStatusAprovacao(StatusAprovacao statusAprovacao) { this.statusAprovacao = statusAprovacao; }

    public Integer getIdSupervisor() { return idSupervisor; }
    public void setIdSupervisor(Integer idSupervisor) { this.idSupervisor = idSupervisor; }

    public LocalDateTime getDataDecisao() { return dataDecisao; }
    public void setDataDecisao(LocalDateTime dataDecisao) { this.dataDecisao = dataDecisao; }

    public String getMotivoReprovacao() { return motivoReprovacao; }
    public void setMotivoReprovacao(String motivoReprovacao) { this.motivoReprovacao = motivoReprovacao; }
}
