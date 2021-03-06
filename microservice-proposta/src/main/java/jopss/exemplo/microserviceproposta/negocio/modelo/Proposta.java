package jopss.exemplo.microserviceproposta.negocio.modelo;

import jopss.exemplo.microserviceproposta.excecao.SituacaoPropostaInvalidaException;
import jopss.exemplo.microserviceproposta.negocio.util.GeradorHash;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Proposta extends AbstractPersistable<Long> {

    @NotBlank
    private String codigo;

    @NotNull
    @Enumerated(EnumType.STRING)
    private SituacaoProposta situacao;

    @NotNull
    @ManyToOne
    private ClienteTemporario cliente;

    //id do cliente real retornado pelo micro de cliente
    private Long idCliente;

    //id da conta retornado pelo micro de conta
    private Long idConta;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao = new Date();

    public Proposta(){}

    public Proposta(ClienteTemporario cliente) {
        this.cliente = cliente;
        this.gerarCodigo();
        this.andarSituacaoPrimeiraEtapa();
    }

    private void andarSituacaoPrimeiraEtapa() {
        if(this.situacao != null){
            throw new SituacaoPropostaInvalidaException("Proposta invalida. Nao é possivel andar para situacao da primeira etapa");
        }
        this.situacao = SituacaoProposta.PRIMEIRA_ETAPA;
    }

    public void andarSituacaoSegundaEtapa() {
        if(this.situacao == null || !SituacaoProposta.PRIMEIRA_ETAPA.equals(this.situacao)){
            throw new SituacaoPropostaInvalidaException("Proposta invalida. Somente é possivel salvar após completar o cadastro inicial.");
        }
        this.situacao = SituacaoProposta.SEGUNDA_ETAPA;
    }

    public void andarSituacaoTerceiraEtapa() {
        if(this.situacao == null || !SituacaoProposta.SEGUNDA_ETAPA.equals(this.situacao)){
            throw new SituacaoPropostaInvalidaException("Proposta invalida. Somente é possivel salvar após completar o cadastro de endereços.");
        }
        this.situacao = SituacaoProposta.TERCEIRA_ETAPA;
    }

    public void andarSituacaoAceita() {
        if(this.situacao == null || !SituacaoProposta.TERCEIRA_ETAPA.equals(this.situacao)){
            throw new SituacaoPropostaInvalidaException("Proposta invalida. Somente é possivel salvar no detalhamento.");
        }
        this.situacao = SituacaoProposta.ACEITA;
    }

    public void andarSituacaoRecusada() {
        if(this.situacao == null || !SituacaoProposta.TERCEIRA_ETAPA.equals(this.situacao)){
            throw new SituacaoPropostaInvalidaException("Proposta invalida. Somente é possivel salvar no detalhamento.");
        }
        this.situacao = SituacaoProposta.RECUSADA;
    }

    public boolean podeDetalhar(){
        return this.situacao!=null && SituacaoProposta.TERCEIRA_ETAPA.equals(this.situacao);
    }

    private void gerarCodigo() {
        this.codigo = GeradorHash.gerarCodigo();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public SituacaoProposta getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoProposta situacao) {
        this.situacao = situacao;
    }

    public ClienteTemporario getCliente() {
        return cliente;
    }

    public void setCliente(ClienteTemporario cliente) {
        this.cliente = cliente;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdConta() {
        return idConta;
    }

    public void setIdConta(Long idConta) {
        this.idConta = idConta;
    }
}
