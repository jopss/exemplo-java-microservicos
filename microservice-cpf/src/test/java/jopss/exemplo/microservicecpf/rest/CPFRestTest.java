package jopss.exemplo.microservicecpf.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import jopss.exemplo.microservicecpf.rest.dto.CPF;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CPFRestTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void validarCPFValido() throws Exception {

		this.mockMvc.perform(get("/cpf/validar-tratar?documento=46812452327")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.documento").value("46812452327"));
	}

	@Test
	public void validarCPFComPontosValido() throws Exception {
		this.mockMvc.perform(get("/cpf/validar-tratar?documento=468.124.523-27")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.documento").value("46812452327"));
	}

	@Test
	public void validarCPFInvalido() throws Exception {
		this.mockMvc.perform(get("/cpf/validar-tratar?documento=12312452327")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
}
