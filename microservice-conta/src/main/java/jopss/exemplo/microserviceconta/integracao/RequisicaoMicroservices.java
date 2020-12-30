package jopss.exemplo.microserviceconta.integracao;

public class RequisicaoMicroservices {
    private int contador = 1;

    public void adicionarContador(){
        this.contador++;
    }

    public boolean chegouLimite(){
        return this.contador == 3;
    }

    public int getContador() {
        return contador;
    }
}
