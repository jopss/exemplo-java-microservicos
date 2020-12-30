package jopss.exemplo.microserviceconta.negocio.repository;

import jopss.exemplo.microserviceconta.negocio.modelo.Conta;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ContaRepository extends CrudRepository<Conta, Long> {
    Optional<Conta> findContaByNumero(Integer numero);
}
