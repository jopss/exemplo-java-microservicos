package jopss.exemplo.microservicecliente.negocio.repository;

import jopss.exemplo.microservicecliente.negocio.modelo.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
    Cliente findClienteByCpf(String cpf);
}
