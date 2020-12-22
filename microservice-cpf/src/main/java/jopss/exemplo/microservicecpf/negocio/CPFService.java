package jopss.exemplo.microservicecpf.negocio;

import org.springframework.stereotype.Service;

@Service
public class CPFService {

    public String tratar(String cpf){
        return cpf.replaceAll("\\.", "").replaceAll("-", "");
    }
}
