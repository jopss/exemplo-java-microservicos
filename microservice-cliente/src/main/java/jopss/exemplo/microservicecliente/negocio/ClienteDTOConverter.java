package jopss.exemplo.microservicecliente.negocio;

import jopss.exemplo.microservicecliente.negocio.modelo.Cliente;
import jopss.exemplo.microservicecliente.negocio.modelo.Endereco;
import jopss.exemplo.microservicecliente.rest.dto.ClienteDTO;
import org.springframework.stereotype.Service;

@Service
public class ClienteDTOConverter {

    public Cliente clienteDTOParaModelo(ClienteDTO dto) {
        Endereco end = new Endereco(dto.getCep(), dto.getLogradouro(), dto.getBairro(), dto.getComplemento(), dto.getCidade(), dto.getEstado());
        return new Cliente(dto.getNome(), dto.getSobrenome(), dto.getCpf(), dto.getEmail(), dto.getDataNascimento(), end);
    }
}
