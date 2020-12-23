package jopss.exemplo.microserviceproposta.negocio.modelo;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class Endereco extends AbstractPersistable<Long> {

    @NotBlank
    private String cep;
    @NotBlank
    private String logradouro;
    private String bairro;
    private String complemento;
    @NotBlank
    private String cidade;
    @NotBlank
    private String estado;

    public Endereco(){}

    public Endereco(String cep, String logradouro, String cidade, String estado) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
