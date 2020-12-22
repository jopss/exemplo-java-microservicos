package jopss.exemplo.microservicecpf.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CPFRestTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void validarCPFValido() throws Exception {

		this.mockMvc.perform(get("/cpf/46812452327/validar-tratar")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").value("46812452327"));
	}

	@Test
	public void validarCPFComPontosValido() throws Exception {
		this.mockMvc.perform(get("/cpf/468.124.523-27/validar-tratar")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").value("46812452327"));
	}

	@Test
	public void validarCPFInvalido() throws Exception {
		this.mockMvc.perform(get("/cpf/12312452327/validar-tratar")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void validarCPFVazio() throws Exception {
		this.mockMvc.perform(get("/cpf/null/validar-tratar")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
}
