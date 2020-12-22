package jopss.exemplo.microservicedocumento.excecao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DocumentoException extends RuntimeException {
    public DocumentoException(String message) {
        super(message);
    }
    public DocumentoException(Throwable cause) {
        super(cause);
    }
    public DocumentoException(String message, Throwable cause) {
        super(message, cause);
    }
}
