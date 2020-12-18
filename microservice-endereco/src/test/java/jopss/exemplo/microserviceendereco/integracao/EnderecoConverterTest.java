package jopss.exemplo.microserviceendereco.integracao;

import jopss.exemplo.microserviceendereco.negocio.modelo.Endereco;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EnderecoConverterTest {

    @Autowired
    private EnderecoConverter converter;

    @Test
    public void viaCepInvalido(){
        ViaCep via = new ViaCep();

        Assert.assertNull(this.converter.viaCepParaEndereco(null));
        Assert.assertNull(this.converter.viaCepParaEndereco(via));

        via.setCep("");
        Assert.assertNull(this.converter.viaCepParaEndereco(null));
    }

    @Test
    public void viaCepValido(){
        ViaCep via = new ViaCep();
        via.setCep("72221-211");
        via.setLocalidade("Cidade");
        via.setUf("UF");

        Endereco endereco = this.converter.viaCepParaEndereco(via);
        Assert.assertEquals(endereco.getCep(), "72221211");
        Assert.assertEquals(endereco.getLocalidade(), "Cidade");
        Assert.assertEquals(endereco.getUf(), "UF");
    }
}
