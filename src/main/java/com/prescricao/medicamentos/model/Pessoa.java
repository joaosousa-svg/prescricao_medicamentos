package com.prescricao.medicamentos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PESSOA")
public class Pessoa {

    @Id
    @Column(name = "IDPESSOA")
    private Integer id;

    @Column(name = "ID_DOCUMENTO")
    private Long idDocumento;

    @Column(name = "NOMEPESSOA")
    private String nome;

    @Column(name = "TIPOPESSOA")
    private String tipo;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "CEP")
    private String cep;

    @Column(name = "BAIRRO")
    private String bairro;

    @Column(name = "NUMERO_ENDERECO")
    private String numeroEndereco;

    @Column(name = "COMPLEMENTO")
    private String complemento;

    @Column(name = "TELEFONE")
    private String telefone;

    @Column(name = "ESTADO_RESIDE")
    private String estadoReside;

    @Column(name = "CIDADE_RESIDE")
    private String cidadeReside;

    @Column(name = "ESTADO_NASCIMENTO")
    private String estadoNascimento;

    @Column(name = "CIDADE_NASCIMENTO")
    private String cidadeNascimento;

    public Long getIdDocumento() { return idDocumento; }
    public void setIdDocumento(Long idDocumento) { this.idDocumento = idDocumento; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }

    public String getNumeroEndereco() { return numeroEndereco; }
    public void setNumeroEndereco(String numeroEndereco) { this.numeroEndereco = numeroEndereco; }

    public String getComplemento() { return complemento; }
    public void setComplemento(String complemento) { this.complemento = complemento; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEstadoReside() { return estadoReside; }
    public void setEstadoReside(String estadoReside) { this.estadoReside = estadoReside; }

    public String getCidadeReside() { return cidadeReside; }
    public void setCidadeReside(String cidadeReside) { this.cidadeReside = cidadeReside; }

    public String getEstadoNascimento() { return estadoNascimento; }
    public void setEstadoNascimento(String estadoNascimento) { this.estadoNascimento = estadoNascimento; }

    public String getCidadeNascimento() { return cidadeNascimento; }
    public void setCidadeNascimento(String cidadeNascimento) { this.cidadeNascimento = cidadeNascimento; }
}
