package jopss.exemplo.microserviceconta.integracao;

import feign.FeignException;
import feign.RetryableException;
import jopss.exemplo.microserviceconta.excecao.IntegracaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utilizando o DP template method, centraliza as chamadas dos microservices
 * e tratamentos. O metodo desta classe pai invoca a abstracao especifica
 * da classe filha.
 *
 * Ha um pouco mais de burocracia mas a vantagem é manter o padrao de chamadas e fluxo, sempre orientando a objetos.
 *
 * @param <E extends RequisicaoMicroservices> Objeto de requisicao
 * @param <R> Objeto de resposta
 */
public abstract class IntegracaoMicroservices<E extends RequisicaoMicroservices, R> {

    private Logger log = LoggerFactory.getLogger(IntegracaoMicroservices.class);

    public R executar(E req) {
        log.debug("executando integração com microservicos, tentativa "+req.getContador());
        try {
            return this.tratar(req);
        }catch(FeignException.FeignClientException e){
            log.debug("erro 400.");
            if(e.status() == 400){
                throw new IntegracaoException("Dados enviados ao microservico invalidos");
            }
            throw e;
        }catch(RetryableException e){
            log.debug("problema de conexao 'RetryableException'");
            req.adicionarContador();
            if(req.chegouLimite()) {
                log.debug("chegou ao limite, retornando erro");
                throw new IntegracaoException("Microservico fora do ar. Tente novamente.");
            }
            log.debug("executando novamente...");
            return this.executar(req);
        }
    }

    protected abstract R tratar(E object);

}
