package jopss.exemplo.microserviceproposta.excecao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DocumentoException extends RuntimeException {
    public DocumentoException(String message) {
        super(message);
    }
    public DocumentoException(Throwable cause) {
        super(cause);
    }
}
