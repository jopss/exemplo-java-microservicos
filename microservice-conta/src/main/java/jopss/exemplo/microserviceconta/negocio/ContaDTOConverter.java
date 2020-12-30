package jopss.exemplo.microserviceconta.negocio;

import jopss.exemplo.microserviceconta.negocio.modelo.Conta;
import jopss.exemplo.microserviceconta.rest.dto.ContaDTO;
import org.springframework.stereotype.Service;

@Service
public class ContaDTOConverter {

    public Conta contaDTOParaModelo(ContaDTO dto) {
        return new Conta(dto.getIdCliente(), dto.getIdProposta());
    }
}
