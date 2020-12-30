package jopss.exemplo.microservicecliente.negocio;

import jopss.exemplo.microservicecliente.excecao.ClienteException;
import jopss.exemplo.microservicecliente.excecao.ClienteInexistenteException;
import jopss.exemplo.microservicecliente.negocio.modelo.Cliente;
import jopss.exemplo.microservicecliente.negocio.repository.ClienteRepository;
import jopss.exemplo.microservicecliente.rest.dto.ClienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private ClienteDTOConverter converter;

    public Cliente cadastrar(ClienteDTO dto){
        Cliente cliente = this.converter.clienteDTOParaModelo(dto);
        Cliente base = this.repository.findClienteByCpf(cliente.getCpf());
        if(!cliente.isNew() || base != null){
            throw new ClienteException("Cliente ja existe. Efetue a busca.");
        }
        return this.repository.save(cliente);
    }

    public Cliente buscarPorId(Long id) {
        return this.repository.findById(id).orElseThrow(() -> new ClienteInexistenteException("Cliente inexistente"));
    }
}
