package jopss.exemplo.microserviceconta.negocio;

import jopss.exemplo.microserviceconta.excecao.ContaInexistenteException;
import jopss.exemplo.microserviceconta.integracao.cliente.ClienteAPI;
import jopss.exemplo.microserviceconta.integracao.cliente.ClienteAdapter;
import jopss.exemplo.microserviceconta.integracao.cliente.ClienteRequisicao;
import jopss.exemplo.microserviceconta.integracao.email.EmailAdapter;
import jopss.exemplo.microserviceconta.integracao.proposta.PropostaAPI;
import jopss.exemplo.microserviceconta.integracao.proposta.PropostaAdapter;
import jopss.exemplo.microserviceconta.integracao.proposta.PropostaRequisicao;
import jopss.exemplo.microserviceconta.negocio.modelo.Conta;
import jopss.exemplo.microserviceconta.negocio.repository.ContaRepository;
import jopss.exemplo.microserviceconta.rest.dto.ContaDTO;
import jopss.exemplo.microserviceconta.rest.dto.ContaResposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

    @Autowired
    private ContaRepository repository;

    @Autowired
    private ContaDTOConverter converter;

    @Autowired
    private EmailAdapter emailAdapter;

    @Autowired
    private ClienteAdapter clienteAdapter;

    @Autowired
    private ContaRespostaConverter contaRespostaConverter;

    @Autowired
    private PropostaAdapter propostaAdapter;

    @Autowired
    private TextoEmail textoEmail;

    public Conta cadastrar(ContaDTO dto){
        Conta conta = this.converter.contaDTOParaModelo(dto);
        conta = this.repository.save(conta);
        this.emailAdapter.executar(this.textoEmail.criacaoConta(dto.getEmailCliente(), conta));
        return conta;
    }

    public ContaResposta buscarPorId(Long id) {
        Conta conta = this.repository.findById(id).orElseThrow(() -> new ContaInexistenteException("Nao existe uma conta com id " + id));
        return this.montarResposta(conta);
    }

    public ContaResposta buscarPorNumero(Integer numero) {
        Conta conta = this.repository.findContaByNumero(numero).orElseThrow(() -> new ContaInexistenteException("Nao existe uma conta com numero "+numero));
        return this.montarResposta(conta);
    }

    /**
     * Como o negocio esta espalhado por microservicos, vai buscar dados detalhados de cliente e proposta para
     * compor a resposta da Conta. Cada adapter eh uma requisicao HTTP interna na rede.
     *
     * @param conta Conta
     * @return ContaResposta
     */
    private ContaResposta montarResposta(Conta conta) {
        ClienteAPI clienteApi = this.clienteAdapter.executar(new ClienteRequisicao(conta.getIdCliente()));
        PropostaAPI propostaApi = this.propostaAdapter.executar(new PropostaRequisicao(conta.getIdProposta()));
        return this.contaRespostaConverter.contaModeloParaResposta(conta, clienteApi, propostaApi);
    }
}
