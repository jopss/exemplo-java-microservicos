package jopss.exemplo.microserviceendereco.negocio;

import jopss.exemplo.microserviceendereco.negocio.modelo.Endereco;
import org.springframework.data.repository.CrudRepository;

public interface EnderecoRepository extends CrudRepository<Endereco, Long> {
    Endereco findByCep(String cep);
}
