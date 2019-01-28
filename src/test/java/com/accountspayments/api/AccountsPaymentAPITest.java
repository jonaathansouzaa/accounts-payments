package com.accountspayments.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.accountspayments.api.adapter.AccountsPaymentAdapter;
import com.accountspayments.request.AccountsPaymentRequest;
import com.accountspayments.response.AccountsPaymentResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AccountsPaymentAPITest {

	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private AccountsPaymentAdapter accountsPaymentAdapter;
	
	@Test
	public void shouldReturnOkIfTheRequestIsCorrectly() throws Exception {
		when(accountsPaymentAdapter.handleRequest(any(AccountsPaymentRequest.class))).thenReturn(Boolean.TRUE);
		
		mockMvc.perform(post("/acccounts-payment")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new String(Files.readAllBytes(Paths.get("src/main/resources/json/request/accountPayment.json")))))
			.andExpect(status().isOk());
		
		verify(accountsPaymentAdapter).handleRequest(any(AccountsPaymentRequest.class));
	}
	
	@Test
	public void shouldReturnBadRequestIfTheRequestDontHaveSomeInformationAboutAccountsPayment() throws Exception {
		when(accountsPaymentAdapter.handleRequest(any(AccountsPaymentRequest.class))).thenThrow(IllegalArgumentException.class);
		mockMvc.perform(post("/acccounts-payment")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new String(Files.readAllBytes(Paths.get("src/main/resources/json/request/accountPaymentWithoutName.json")))))
			.andExpect(status().isBadRequest());
		
		verify(accountsPaymentAdapter).handleRequest(any(AccountsPaymentRequest.class));
	}
	
	@Test
	public void shouldReturnAListOfAccountsPayment() throws Exception {
		when(accountsPaymentAdapter.handleRequest()).thenReturn(new ArrayList<AccountsPaymentResponse>());
		mockMvc.perform(get("/acccounts-payment")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new String(Files.readAllBytes(Paths.get("src/main/resources/json/request/accountPaymentWithoutName.json")))))
			.andExpect(status().isOk());
		
		verify(accountsPaymentAdapter).handleRequest();
	}
	
}
