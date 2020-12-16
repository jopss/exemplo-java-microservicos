package jopss.exemplo.microserviceemail.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public class Email {

    private String de;

    @NotNull
    private List<String> para;

    @NotBlank
    private String titulo;

    @NotBlank
    private String mensagem;

    @JsonIgnore
    public String getParaTextual(){
       return this.para.stream().collect(Collectors.joining(","));
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public List<String> getPara() {
        return para;
    }

    public void setPara(List<String> para) {
        this.para = para;
    }
}
