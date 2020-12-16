package jopss.exemplo.microserviceemail.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import jopss.exemplo.microserviceemail.integracao.EmailAdapter;
import jopss.exemplo.microserviceemail.rest.dto.Email;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmailRestTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private EmailAdapter adapter;

	@BeforeEach
	public void init(){
		Mockito.doNothing().when(adapter).integrar(Mockito.any());
	}

	@Test
	public void emailSemDados() throws Exception {
		Email dado = new Email();

		this.mockMvc.perform(post("/email")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dado)))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void enviarEmailSemPara() throws Exception {
		Email dado = getEmail();
		dado.setPara(new ArrayList<>());
		this.mockMvc.perform(post("/email")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dado)))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void enviarEmailSemDe() throws Exception {
		Email dado = getEmail();
		dado.setDe(null);
		this.mockMvc.perform(post("/email")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dado)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.de").value("meuemailpadrao@provedor.com.br"))
				.andExpect(jsonPath("$.titulo").value("titulo do email"))
				.andExpect(jsonPath("$.mensagem").value("mensagem do email"));
	}

	@Test
	public void enviarEmailCompleto() throws Exception {
		Email dado = getEmail();
		this.mockMvc.perform(post("/email")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dado)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.de").value("emailde@email.com"))
				.andExpect(jsonPath("$.titulo").value("titulo do email"))
				.andExpect(jsonPath("$.mensagem").value("mensagem do email"));
	}

	private Email getEmail() {
		Email dado = new Email();
		dado.setDe("emailde@email.com");
		dado.setPara(Arrays.asList("emailpara@email.com"));
		dado.setTitulo("titulo do email");
		dado.setMensagem("mensagem do email");
		return dado;
	}
}
