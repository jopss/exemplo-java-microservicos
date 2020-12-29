package jopss.exemplo.microserviceproposta.negocio.repository;

import jopss.exemplo.microserviceproposta.negocio.modelo.Proposta;
import org.springframework.data.repository.CrudRepository;

public interface PropostaRepository extends CrudRepository<Proposta, Long> {
    Proposta findByCodigo(String codigo);
}
