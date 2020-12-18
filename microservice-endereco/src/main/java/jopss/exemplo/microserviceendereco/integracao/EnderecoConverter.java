package jopss.exemplo.microserviceendereco.integracao;

import jopss.exemplo.microserviceendereco.negocio.EnderecoFormatter;
import jopss.exemplo.microserviceendereco.negocio.modelo.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoConverter {

    @Autowired
    private EnderecoFormatter formatter;

    public Endereco viaCepParaEndereco(ViaCep viaCep){
        if(viaCep == null || viaCep.getCep() == null || viaCep.getCep().isEmpty()){
            return null;
        }

        Endereco enderecoAPI = new Endereco();
        enderecoAPI.setCep(this.formatter.formatarCep(viaCep.getCep()));
        enderecoAPI.setBairro(viaCep.getBairro());
        enderecoAPI.setComplemento(viaCep.getComplemento());
        enderecoAPI.setIbge(viaCep.getIbge());
        enderecoAPI.setLocalidade(viaCep.getLocalidade());
        enderecoAPI.setLogradouro(viaCep.getLogradouro());
        enderecoAPI.setUf(viaCep.getUf());
        return enderecoAPI;
    }

}
