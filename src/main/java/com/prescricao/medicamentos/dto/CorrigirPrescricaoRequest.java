package com.prescricao.medicamentos.dto;

public class CorrigirPrescricaoRequest {
    private Integer id;
    private String textoPrescricao;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public String getTextoPrescricao() { return textoPrescricao; }
    public void setTextoPrescricao(String textoPrescricao) { this.textoPrescricao = textoPrescricao; }
}
