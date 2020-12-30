package jopss.exemplo.microserviceproposta.integracao.cliente;

import jopss.exemplo.microserviceproposta.negocio.modelo.ClienteTemporario;
import org.springframework.stereotype.Service;

@Service
public class ClienteConverter {

    public ClienteRequisicao modeloParaClienteRequisicao(ClienteTemporario temporario){
        return new ClienteRequisicao(temporario.getNome(), temporario.getSobrenome(), temporario.getCpf(), temporario.getEmail(), temporario.getDataNascimento(),
                temporario.getEndereco().getCep(),temporario.getEndereco().getLogradouro(), temporario.getEndereco().getBairro(), temporario.getEndereco().getComplemento(),
                temporario.getEndereco().getCidade(), temporario.getEndereco().getEstado());
    }

}
