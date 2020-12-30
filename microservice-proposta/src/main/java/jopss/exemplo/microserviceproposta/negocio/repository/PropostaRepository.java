package jopss.exemplo.microserviceproposta.negocio.repository;

import jopss.exemplo.microserviceproposta.negocio.modelo.Proposta;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PropostaRepository extends CrudRepository<Proposta, Long> {
    Optional<Proposta> findByCodigo(String codigo);
}
