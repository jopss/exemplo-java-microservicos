package jopss.exemplo.microserviceproposta.integracao.cliente;

import jopss.exemplo.microserviceproposta.integracao.RequisicaoMicroservices;

import java.util.Date;

public class ClienteRequisicao extends RequisicaoMicroservices {
    private String nome;
    private String sobrenome;
    private String cpf;
    private String email;
    private Date dataNascimento;
    private String cep;
    private String logradouro;
    private String bairro;
    private String complemento;
    private String cidade;
    private String estado;

    public ClienteRequisicao(String nome, String sobrenome, String cpf, String email, Date dataNascimento, String cep, String logradouro, String bairro, String complemento, String cidade, String estado) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.complemento = complemento;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public String getCep() {
        return cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }
}
