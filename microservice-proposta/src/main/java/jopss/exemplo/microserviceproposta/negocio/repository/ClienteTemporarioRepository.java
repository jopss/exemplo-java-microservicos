package jopss.exemplo.microserviceproposta.negocio.repository;

import jopss.exemplo.microserviceproposta.negocio.modelo.ClienteTemporario;
import org.springframework.data.repository.CrudRepository;

public interface ClienteTemporarioRepository extends CrudRepository<ClienteTemporario, Long> {
    int countClienteTemporarioByCpfOrEmail(String cpf, String email);
}
