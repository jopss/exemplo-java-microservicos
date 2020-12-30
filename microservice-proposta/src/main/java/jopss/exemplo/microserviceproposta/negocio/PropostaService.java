package jopss.exemplo.microserviceproposta.negocio;

import jopss.exemplo.microserviceproposta.excecao.PropostaInexistenteException;
import jopss.exemplo.microserviceproposta.excecao.SituacaoPropostaInvalidaException;
import jopss.exemplo.microserviceproposta.integracao.cliente.ClienteAdapter;
import jopss.exemplo.microserviceproposta.integracao.cliente.ClienteConverter;
import jopss.exemplo.microserviceproposta.integracao.cliente.ClienteRequisicao;
import jopss.exemplo.microserviceproposta.integracao.email.EmailAPI;
import jopss.exemplo.microserviceproposta.integracao.email.EmailAdapter;
import jopss.exemplo.microserviceproposta.negocio.modelo.ClienteTemporario;
import jopss.exemplo.microserviceproposta.negocio.modelo.Proposta;
import jopss.exemplo.microserviceproposta.negocio.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropostaService {

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private EmailAdapter emailAdapter;

    @Autowired
    private ClienteAdapter clienteAdapter;

    @Autowired
    private ClienteConverter clienteConverter;

    public Proposta buscarProposta(String codigo){
        Proposta proposta = this.propostaRepository.findByCodigo(codigo);
        if(proposta == null || proposta.isNew()){
            throw new PropostaInexistenteException("Nao existe uma proposta com codigo "+codigo);
        }
        return proposta;
    }

    public Proposta detalhar(String codigo) {
        Proposta proposta = this.buscarProposta(codigo);
        if(!proposta.podeDetalhar()){
            throw new SituacaoPropostaInvalidaException("Proposta invalida para detalhamento. É necessario passar por todos os passos.");
        }
        return proposta;
    }

    public Proposta aceitar(String codigo) {
        Proposta proposta = this.detalhar(codigo);
        proposta.andarSituacaoAceita();

        Long idCliente = this.cadastrarClienteRealRetornandoId(proposta.getCliente());
        proposta.setIdCliente(idCliente);

        //TODO: criar conta
        this.enviarEmail(proposta.getCliente(), "Proposta Aceita", "Sua conta foi criada. Acesse para logar.");
        return this.propostaRepository.save(proposta);
    }

    public Proposta recusar(String codigo) {
        Proposta proposta = this.detalhar(codigo);
        proposta.andarSituacaoRecusada();
        this.enviarEmail(proposta.getCliente(), "Proposta Recusada", "Voce recusou a proposta de conta. Caso mude e opiniao aceita aqui por favor.");
        return this.propostaRepository.save(proposta);
    }

    private void enviarEmail(ClienteTemporario cliente, String titulo, String mensagem){
        this.emailAdapter.executar(new EmailAPI(cliente.getEmail(), titulo, mensagem));
    }

    private Long cadastrarClienteRealRetornandoId(ClienteTemporario cliente) {
        ClienteRequisicao clienteRequisicao = this.clienteConverter.modeloParaClienteRequisicao(cliente);
        return this.clienteAdapter.executar(clienteRequisicao);
    }
}
