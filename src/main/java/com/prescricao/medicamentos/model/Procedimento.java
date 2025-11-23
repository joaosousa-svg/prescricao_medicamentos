package com.prescricao.medicamentos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PROCEDIMENTO")
public class Procedimento {

    @Id
    @Column(name = "IDPROCED")
    private Integer id;

    @Column(name = "CODPROCED")
    private String codigo;

    @Column(name = "DESCRPROC")
    private String descricao;

    @Column(name = "VALORPROC")
    private Double valor;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }
}
