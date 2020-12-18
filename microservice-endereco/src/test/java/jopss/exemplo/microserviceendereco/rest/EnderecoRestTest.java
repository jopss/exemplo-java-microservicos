package jopss.exemplo.microserviceendereco.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import jopss.exemplo.microserviceendereco.integracao.ViaCepAdapter;
import jopss.exemplo.microserviceendereco.negocio.EnderecoRepository;
import jopss.exemplo.microserviceendereco.negocio.modelo.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EnderecoRestTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ViaCepAdapter adapter;

    @MockBean
    private EnderecoRepository repository;

    private Endereco endereco;

    @BeforeEach
    public void init(){
        endereco = new Endereco();
        endereco.setCep("72221211");
        endereco.setLocalidade("Cidade");
        endereco.setUf("UF");
        Mockito.when(adapter.buscarExterno(Mockito.any())).thenReturn(endereco);
        Mockito.when(repository.save(Mockito.any())).thenReturn(endereco);
    }

    @Test
    public void buscarCepBase() throws Exception {
        Mockito.when(repository.findByCep(Mockito.any())).thenReturn(endereco);

        this.mockMvc.perform(get("/endereco/buscar?cep=" + endereco.getCep())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cep").value(endereco.getCep()))
                .andExpect(jsonPath("$.localidade").value(endereco.getLocalidade()))
                .andExpect(jsonPath("$.uf").value(endereco.getUf()));
    }

    @Test
    public void buscarCepExternoESalvar() throws Exception {
        Mockito.when(repository.findByCep(Mockito.any())).thenReturn(null);

        this.mockMvc.perform(get("/endereco/buscar?cep=" + endereco.getCep())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cep").value(endereco.getCep()))
                .andExpect(jsonPath("$.localidade").value(endereco.getLocalidade()))
                .andExpect(jsonPath("$.uf").value(endereco.getUf()));
    }

    @Test
    public void buscarCepExternoErrado() throws Exception {
        Mockito.when(repository.findByCep(Mockito.any())).thenReturn(null);
        Mockito.when(adapter.buscarExterno(Mockito.any())).thenReturn(null);

        this.mockMvc.perform(get("/endereco/buscar?cep=123")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
