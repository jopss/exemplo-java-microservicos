package jopss.exemplo.microserviceproposta.integracao.email;

import java.util.ArrayList;
import java.util.List;

public class EmailAPI {
    private List<String> para = new ArrayList<>();
    private String titulo;
    private String mensagem;

    public EmailAPI(){}

    public EmailAPI(String para, String titulo, String mensagem) {
        this.para.add(para);
        this.titulo = titulo;
        this.mensagem = mensagem;
    }

    public List<String> getPara() {
        return para;
    }

    public void setPara(List<String> para) {
        this.para = para;
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
}
