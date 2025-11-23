package com.prescricao.medicamentos.dto;

import java.time.LocalDateTime;

public class PrescricaoDTO {
    private Integer id;
    private Integer idPaciente;
    private String nomePaciente;
    private String nomeProfissional;
    private String especialidade;
    private String codigoEspecialidade;
    private Integer idProcedimento;
    private String procedimento;
    private String codigoProcedimento;
    private Double valorProcedimento;
    private LocalDateTime dataProcedimento;
    private String textoPrescricao;
    private String linkProced;
    private String autoPacVisu;
    private String status;
    private String statusAprovacao;
    private String motivoReprovacao;
    private Integer idSupervisor;
    private String nomeSupervisor;
    private LocalDateTime dataDecisao;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public Integer getIdPaciente() { return idPaciente; }
    public void setIdPaciente(Integer idPaciente) { this.idPaciente = idPaciente; }
    
    public String getNomePaciente() { return nomePaciente; }
    public void setNomePaciente(String nomePaciente) { this.nomePaciente = nomePaciente; }
    
    public LocalDateTime getDataProcedimento() { return dataProcedimento; }
    public void setDataProcedimento(LocalDateTime dataProcedimento) { this.dataProcedimento = dataProcedimento; }
    
    public String getTextoPrescricao() { return textoPrescricao; }
    public void setTextoPrescricao(String textoPrescricao) { this.textoPrescricao = textoPrescricao; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getStatusAprovacao() { return statusAprovacao; }
    public void setStatusAprovacao(String statusAprovacao) { this.statusAprovacao = statusAprovacao; }
    
    public String getMotivoReprovacao() { return motivoReprovacao; }
    public void setMotivoReprovacao(String motivoReprovacao) { this.motivoReprovacao = motivoReprovacao; }

    public String getNomeProfissional() { return nomeProfissional; }
    public void setNomeProfissional(String nomeProfissional) { this.nomeProfissional = nomeProfissional; }

    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }

    public String getCodigoEspecialidade() { return codigoEspecialidade; }
    public void setCodigoEspecialidade(String codigoEspecialidade) { this.codigoEspecialidade = codigoEspecialidade; }

    public Integer getIdProcedimento() { return idProcedimento; }
    public void setIdProcedimento(Integer idProcedimento) { this.idProcedimento = idProcedimento; }

    public String getProcedimento() { return procedimento; }
    public void setProcedimento(String procedimento) { this.procedimento = procedimento; }

    public String getCodigoProcedimento() { return codigoProcedimento; }
    public void setCodigoProcedimento(String codigoProcedimento) { this.codigoProcedimento = codigoProcedimento; }

    public Double getValorProcedimento() { return valorProcedimento; }
    public void setValorProcedimento(Double valorProcedimento) { this.valorProcedimento = valorProcedimento; }

    public String getLinkProced() { return linkProced; }
    public void setLinkProced(String linkProced) { this.linkProced = linkProced; }

    public String getAutoPacVisu() { return autoPacVisu; }
    public void setAutoPacVisu(String autoPacVisu) { this.autoPacVisu = autoPacVisu; }

    public Integer getIdSupervisor() { return idSupervisor; }
    public void setIdSupervisor(Integer idSupervisor) { this.idSupervisor = idSupervisor; }

    public String getNomeSupervisor() { return nomeSupervisor; }
    public void setNomeSupervisor(String nomeSupervisor) { this.nomeSupervisor = nomeSupervisor; }

    public LocalDateTime getDataDecisao() { return dataDecisao; }
    public void setDataDecisao(LocalDateTime dataDecisao) { this.dataDecisao = dataDecisao; }
}
