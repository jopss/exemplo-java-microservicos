package jopss.exemplo.microserviceproposta.integracao.endereco;

import jopss.exemplo.microserviceproposta.negocio.modelo.Endereco;
import org.springframework.stereotype.Service;

@Service
public class EnderecoConverter {

    public Endereco enderecoAPIParaModelo(EnderecoAPI api){
        Endereco endereco = new Endereco(api.getCep(), api.getLogradouro(), api.getLocalidade(), api.getUf());
        endereco.setBairro(api.getBairro());
        endereco.setComplemento(api.getComplemento());
        return endereco;
    }

}
