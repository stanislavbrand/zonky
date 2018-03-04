package cz.zonky.demo.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import cz.zonky.demo.model.Loan;

@Service
public class LoanServiceImpl implements LoanService {

	private static final Logger log = LoggerFactory.getLogger(LoanServiceImpl.class);

	private static final String LOANS_URL = "/marketplace";
	protected static final String HEADER_ORDER = "X-Order";
	
	protected static final String LOAN_DATE_PUBLISHED = "datePublished";
	
	private RestTemplate restTemplate;	

	public LoanServiceImpl(RestTemplateBuilder builder) {
		restTemplate = builder.build();
	}

	@Override
	public List<Loan> getLoans() {

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			headers.set(HEADER_ORDER, LOAN_DATE_PUBLISHED);

			HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
			
			ResponseEntity<List<Loan>> rateResponse = restTemplate.exchange(LOANS_URL, HttpMethod.GET, entity,
					new ParameterizedTypeReference<List<Loan>>() {
					});
			List<Loan> loans = rateResponse.getBody();
			
			if(log.isDebugEnabled()) {
				log.debug("Response status: {}, loans size: {}", rateResponse.getStatusCode(), loans.size());

				loans.stream().forEach(l -> log.debug(l.toString()));
			}
			
			return loans;
		} catch (RestClientException e) {
			log.error("Failed to get loans from marketplace.", e);
			throw new IllegalStateException(e.getMessage());
		}
	}

}
