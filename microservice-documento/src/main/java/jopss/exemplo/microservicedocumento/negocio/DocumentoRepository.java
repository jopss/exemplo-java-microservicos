package jopss.exemplo.microservicedocumento.negocio;

import jopss.exemplo.microservicedocumento.negocio.modelo.Documento;
import org.springframework.data.repository.CrudRepository;

public interface DocumentoRepository extends CrudRepository<Documento, Long> {

    Documento findByIdPessoa(Long idPessoa);
}
