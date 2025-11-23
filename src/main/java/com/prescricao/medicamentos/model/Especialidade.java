package com.prescricao.medicamentos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ESPECIALIDADE")
public class Especialidade {

    @Id
    @Column(name = "IDESPEC")
    private Integer id;

    @Column(name = "CODESPEC")
    private String codigo;

    @Column(name = "DESCESPEC")
    private String descricao;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
