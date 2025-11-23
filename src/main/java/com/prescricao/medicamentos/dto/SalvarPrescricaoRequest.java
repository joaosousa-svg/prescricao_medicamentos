package com.prescricao.medicamentos.dto;

public class SalvarPrescricaoRequest {

    private Integer idPaciente;
    private Integer idProcedimento;
    private String textoPrescricao;

    public Integer getIdPaciente() { return idPaciente; }
    public void setIdPaciente(Integer idPaciente) { this.idPaciente = idPaciente; }

    public Integer getIdProcedimento() { return idProcedimento; }
    public void setIdProcedimento(Integer idProcedimento) { this.idProcedimento = idProcedimento; }

    public String getTextoPrescricao() { return textoPrescricao; }
    public void setTextoPrescricao(String textoPrescricao) { this.textoPrescricao = textoPrescricao; }
}
