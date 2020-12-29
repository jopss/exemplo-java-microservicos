package jopss.exemplo.microserviceproposta.excecao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class SituacaoPropostaInvalidaException extends RuntimeException {
    public SituacaoPropostaInvalidaException(String message) {
        super(message);
    }
    public SituacaoPropostaInvalidaException(Throwable cause) {
        super(cause);
    }
}
