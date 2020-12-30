package jopss.exemplo.microserviceproposta.negocio;

import jopss.exemplo.microserviceproposta.excecao.PropostaInexistenteException;
import jopss.exemplo.microserviceproposta.excecao.SituacaoPropostaInvalidaException;
import jopss.exemplo.microserviceproposta.integracao.cliente.ClienteAdapter;
import jopss.exemplo.microserviceproposta.integracao.cliente.ClienteConverter;
import jopss.exemplo.microserviceproposta.integracao.cliente.ClienteRequisicao;
import jopss.exemplo.microserviceproposta.integracao.conta.ContaAdapter;
import jopss.exemplo.microserviceproposta.integracao.conta.ContaConverter;
import jopss.exemplo.microserviceproposta.integracao.conta.ContaRequisicao;
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

    @Autowired
    private ContaAdapter contaAdapter;

    @Autowired
    private ContaConverter contaConverter;

    @Autowired
    private TextoEmail textoEmail;

    public Proposta buscarProposta(String codigo){
        return this.propostaRepository.findByCodigo(codigo).orElseThrow(() -> new PropostaInexistenteException("Nao existe uma proposta com codigo "+codigo));
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

        this.cadastrarClienteRealInserindoId(proposta);
        this.cadastrarContaInserindoId(proposta);

        this.emailAdapter.executar(this.textoEmail.aceite(proposta));
        return this.propostaRepository.save(proposta);
    }

    public Proposta recusar(String codigo) {
        Proposta proposta = this.detalhar(codigo);
        proposta.andarSituacaoRecusada();
        this.emailAdapter.executar(this.textoEmail.recusa(proposta));
        return this.propostaRepository.save(proposta);
    }

    private void enviarEmail(ClienteTemporario cliente, String titulo, String mensagem){
        this.emailAdapter.executar(new EmailAPI(cliente.getEmail(), titulo, mensagem));
    }

    private void cadastrarClienteRealInserindoId(Proposta proposta) {
        ClienteRequisicao req = this.clienteConverter.modeloParaClienteRequisicao(proposta.getCliente());
        proposta.setIdCliente(this.clienteAdapter.executar(req));
    }

    private void cadastrarContaInserindoId(Proposta proposta) {
        ContaRequisicao req = this.contaConverter.modeloParaContaRequisicao(proposta);
        proposta.setIdConta(this.contaAdapter.executar(req));
    }

    public Proposta buscarPorId(Long id) {
        return this.propostaRepository.findById(id).orElseThrow(() -> new PropostaInexistenteException("Nao existe uma proposta com id "+id));
    }
}
