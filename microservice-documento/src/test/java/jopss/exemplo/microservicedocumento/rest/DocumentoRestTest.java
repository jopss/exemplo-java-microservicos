package jopss.exemplo.microservicedocumento.rest;

import jopss.exemplo.microservicedocumento.negocio.DocumentoRepository;
import jopss.exemplo.microservicedocumento.negocio.modelo.Documento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DocumentoRestTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DocumentoRepository repository;

    private Documento documento;
    private MockMultipartFile file;

    @BeforeEach
    public void init(){
        this.documento = new Documento(1L, "arquivo.png");
        Mockito.when(repository.save(Mockito.any())).thenReturn(documento);
        this.file = new MockMultipartFile(
                "file",
                "arquivo.png",
                MediaType.TEXT_PLAIN_VALUE,
                "Isso eh um texto".getBytes()
        );
    }

    @Test
    public void buscarImagemPessoaNova() throws Exception {
        Mockito.when(repository.findByIdPessoa(Mockito.any())).thenReturn(null);

        this.mockMvc.perform(multipart("/documento/pessoa/1").file(file)
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.content.idPessoa").value(documento.getIdPessoa()))
                .andExpect(jsonPath("$.content.nomeDocumento").value(documento.getNomeDocumento()));
    }

    @Test
    public void buscarImagemPessoaNaBase() throws Exception {
        Mockito.when(repository.findByIdPessoa(Mockito.any())).thenReturn(documento);

        this.mockMvc.perform(multipart("/documento/pessoa/1").file(file)
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.idPessoa").value(documento.getIdPessoa()))
                .andExpect(jsonPath("$.content.nomeDocumento").value(documento.getNomeDocumento()));
    }

}
