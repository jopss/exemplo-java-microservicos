package jopss.exemplo.microserviceendereco.negocio;

import org.springframework.stereotype.Service;

@Service
public class EnderecoFormatter {
    public String formatarCep(String cep) {
        return cep.replaceAll("\\.", "").replaceAll("\\-", "");
    }
}
