package jopss.exemplo.microserviceproposta.negocio.modelo;

import jopss.exemplo.microserviceproposta.excecao.PropostaException;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * Cliente temporario da proposta. Esses dados vem do cadastro publico, pode ser fake ou incompleto.
 * Apos confirmação da proposta, será solicitado a criação de um cliente unico no banco (integracao).
 */
@Entity
public class ClienteTemporario extends AbstractPersistable<Long> {

    @NotBlank
    private String nome;
    @NotBlank
    private String sobrenome;
    @NotBlank
    private String cpf;
    @NotBlank
    private String email;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @ManyToOne
    private Endereco endereco;

    @OneToOne(cascade = CascadeType.ALL)
    private DocumentoCliente documento;

    public ClienteTemporario(){}

    public ClienteTemporario(String nome, String sobrenome, String cpf, String email, Date dataNascimento) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.validarDataMaiorQueDezoito();
    }

    private void validarDataMaiorQueDezoito(){
        LocalDate nasc = LocalDateTime.ofInstant(this.dataNascimento.toInstant(), ZoneOffset.UTC).toLocalDate();
        Period periodo = Period.between(nasc, LocalDate.now());
        if(periodo.getYears() < 18){
            throw new PropostaException("Somente é possivel cadastrar clientes maiores de 18 anos");
        }
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public DocumentoCliente getDocumento() {
        return documento;
    }

    public void setDocumento(DocumentoCliente documento) {
        this.documento = documento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
