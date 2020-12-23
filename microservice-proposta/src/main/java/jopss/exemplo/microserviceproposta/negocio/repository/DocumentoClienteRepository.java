package jopss.exemplo.microserviceproposta.negocio.repository;

import jopss.exemplo.microserviceproposta.negocio.modelo.DocumentoCliente;
import org.springframework.data.repository.CrudRepository;

public interface DocumentoClienteRepository extends CrudRepository<DocumentoCliente, Long> {
}
