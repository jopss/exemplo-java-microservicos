package jopss.exemplo.microserviceendereco.negocio;

import jopss.exemplo.microserviceendereco.excecao.EnderecoException;
import jopss.exemplo.microserviceendereco.integracao.ViaCepAdapter;
import jopss.exemplo.microserviceendereco.negocio.modelo.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EnderecoService {

    @Autowired
    private ViaCepAdapter adapter;

    @Autowired
    private EnderecoRepository repository;

    @Autowired
    private EnderecoFormatter formatter;

    @Transactional
    public Endereco buscarCep(String cep){
        cep = this.formatter.formatarCep(cep);
        Endereco endereco = this.repository.findByCep(cep);

        if(endereco == null) {
            endereco = this.buscarExternoESalvarLocal(cep);
        }
        return endereco;
    }


    private Endereco buscarExternoESalvarLocal(String cep) {
        Endereco endereco = this.adapter.buscarExterno(cep);
        if (endereco == null) {
            throw new EnderecoException("Cep invalido");
        }
        return this.repository.save(endereco);
    }
}
