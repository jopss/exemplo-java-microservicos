package jopss.exemplo.microserviceproposta.negocio;

import jopss.exemplo.microserviceproposta.integracao.documento.DocumentoAdapter;
import jopss.exemplo.microserviceproposta.integracao.documento.DocumentoRequisicao;
import jopss.exemplo.microserviceproposta.negocio.modelo.ClienteTemporario;
import jopss.exemplo.microserviceproposta.negocio.modelo.DocumentoCliente;
import jopss.exemplo.microserviceproposta.negocio.modelo.Proposta;
import jopss.exemplo.microserviceproposta.negocio.repository.ClienteTemporarioRepository;
import jopss.exemplo.microserviceproposta.negocio.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TerceiraEtapaPropostaService {

    @Autowired
    private DocumentoAdapter documentoAdapter;

    @Autowired
    private ClienteTemporarioRepository clienteTemporarioRepository;

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private PropostaService propostaService;

    @Transactional
    public Proposta cadastrar(String codigo, MultipartFile file) {
        Proposta proposta = this.propostaService.buscarProposta(codigo);
        this.tratarDocumento(proposta, file);
        return this.andarProposta(proposta);
    }

    private void tratarDocumento(Proposta proposta, MultipartFile file){
        ClienteTemporario cliente = proposta.getCliente();
        DocumentoCliente documento = this.documentoAdapter.executar(new DocumentoRequisicao(cliente.getId(), file));
        cliente.setDocumento(documento);
        this.clienteTemporarioRepository.save(cliente);
    }

    private Proposta andarProposta(Proposta proposta){
        proposta.andarSituacaoTerceiraEtapa();
        return this.propostaRepository.save(proposta);
    }

}
