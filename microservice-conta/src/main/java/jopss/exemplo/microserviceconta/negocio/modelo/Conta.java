package jopss.exemplo.microserviceconta.negocio.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Random;

@Entity
public class Conta extends AbstractPersistable<Long> {

    private static final Integer BANCO = 001;
    private static final Integer AGENCIA = 0001;

    @Min(4)
    @Max(4)
    @NotNull
    @Column(length = 4)
    private Integer agencia;

    @Min(8)
    @Max(8)
    @NotNull
    @Column(length = 8, unique = true)
    private Integer numero;

    @Min(3)
    @Max(3)
    @NotNull
    @Column(length = 3)
    private Integer banco;

    @Min(0)
    @NotNull
    private Double saldo = 0.0;

    @JsonIgnore
    @NotNull
    @Column(unique = true)
    //id do cliente real do microservico-cliente
    private Long idCliente;

    @JsonIgnore
    @NotNull
    @Column(unique = true)
    //id da proposta do microservico-proposta
    private Long idProposta;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dataCriacao;

    public Conta(){}
    public Conta(Long idCliente, Long idProposta) {
        this.idCliente = idCliente;
        this.idProposta = idProposta;
        this.agencia = AGENCIA;
        this.banco = BANCO;
        this.criarNumeroAleatorio();
    }

    private void criarNumeroAleatorio(){
        this.numero = new Random().nextInt(99999999);
    }

    public Integer getAgencia() {
        return agencia;
    }

    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getBanco() {
        return banco;
    }

    public void setBanco(Integer banco) {
        this.banco = banco;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdProposta() {
        return idProposta;
    }

    public void setIdProposta(Long idProposta) {
        this.idProposta = idProposta;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
