package jopss.exemplo.microservicedocumento.negocio.modelo;

public class Arquivo {

    private Documento documento;
    private String arquivoBase64;

    public Arquivo(){}

    public Arquivo(Documento documento, String arquivoBase64) {
        this.documento = documento;
        this.arquivoBase64 = arquivoBase64;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public String getArquivoBase64() {
        return arquivoBase64;
    }

    public void setArquivoBase64(String arquivoBase64) {
        this.arquivoBase64 = arquivoBase64;
    }
}
