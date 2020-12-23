package jopss.exemplo.microserviceproposta.negocio.repository;

import jopss.exemplo.microserviceproposta.negocio.modelo.Endereco;
import org.springframework.data.repository.CrudRepository;

public interface EnderecoRepository extends CrudRepository<Endereco, Long> {
}
