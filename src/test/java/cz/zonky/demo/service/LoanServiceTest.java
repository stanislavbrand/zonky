package cz.zonky.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.util.StreamUtils;

import cz.zonky.demo.model.IncomeTypeEnum;
import cz.zonky.demo.model.Loan;

@RunWith(SpringRunner.class)
@RestClientTest(LoanServiceImpl.class)
public class LoanServiceTest {
	
	@Autowired
	private LoanService loanService;
	
	@Autowired
	private MockRestServiceServer mockRestServiceServer;
		
	private String loadJson(String filename) throws IOException {
		Resource sampleJsonReource = new ClassPathResource("/sample/"+ filename);
		InputStream is = sampleJsonReource.getInputStream();
        return StreamUtils.copyToString(is, Charset.defaultCharset());
	}

	@Test(expected = IllegalStateException.class)
    public void getLoand_error() throws IOException {
    	mockRestServiceServer.expect(requestTo("/marketplace"))
            .andRespond(withServerError());

        loanService.getLoans();
    }


    @Test
    public void getLoand_oneLoan() throws IOException {
    	mockRestServiceServer.expect(requestTo("/marketplace"))
            .andRespond(withSuccess(loadJson("one_loan.json"), MediaType.APPLICATION_JSON));

        List<Loan> loans = loanService.getLoans();
        assertThat(loans).isNotNull();
        assertThat(loans).hasSize(1);
        
        mockRestServiceServer.verify();
    }
    
    @Test
    public void getLoand_loans() throws IOException {
    	mockRestServiceServer.expect(requestTo("/marketplace"))
    		.andExpect(method(HttpMethod.GET))
    		.andExpect(header(LoanServiceImpl.HEADER_ORDER, LoanServiceImpl.LOAN_DATE_PUBLISHED))
            .andRespond(withSuccess(loadJson("loans.json"), MediaType.APPLICATION_JSON));

        List<Loan> loans = loanService.getLoans();
        assertThat(loans).isNotNull();
        assertThat(loans).hasSize(20);
        
        mockRestServiceServer.verify();
    }
    
    @Test
    public void getLoand_incomeType_UNKNOWN() throws IOException {
    	mockRestServiceServer.expect(requestTo("/marketplace"))
    		.andExpect(method(HttpMethod.GET))
    		.andExpect(header(LoanServiceImpl.HEADER_ORDER, LoanServiceImpl.LOAN_DATE_PUBLISHED))
            .andRespond(withSuccess(loadJson("incomeType_unknown.json"), MediaType.APPLICATION_JSON));

        List<Loan> loans = loanService.getLoans();
        assertThat(loans).isNotNull();
        assertThat(loans).hasSize(3);
        assertThat(loans.get(0).getMainIncomeType()).isEqualTo(IncomeTypeEnum.UNKNOWN);
        
        mockRestServiceServer.verify();
    }
}