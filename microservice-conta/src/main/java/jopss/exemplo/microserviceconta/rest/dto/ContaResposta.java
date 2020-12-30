package jopss.exemplo.microserviceconta.rest.dto;

import jopss.exemplo.microserviceconta.integracao.cliente.ClienteAPI;
import jopss.exemplo.microserviceconta.negocio.modelo.Conta;

/**
 * Classe englobanco dados de conta, proposta e cliente.
 */
public class ContaResposta {

    private Conta conta;
    private ClienteAPI cliente;

}
