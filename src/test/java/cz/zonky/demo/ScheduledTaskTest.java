package cz.zonky.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyCollection;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.task.AsyncListenableTaskExecutor;

import cz.zonky.demo.model.Loan;
import cz.zonky.demo.service.LoanService;

@RunWith(MockitoJUnitRunner.class)
public class ScheduledTaskTest {

	@Mock
	private LoanService loanService;
	
	@Spy
	private List<Loan> loanCache = new ArrayList<>();
	
	@InjectMocks	
	private TaskScheduler scheduledTasks;
	
	private List<Loan> loans;

	private Loan loan1;
	private Loan loan2;
	private Loan loan3;
	
	@Before
	public void setup() {
		loan1 = new Loan.Builder(1L).build();
		loan2 = new Loan.Builder(2L).build();
		loan3 = new Loan.Builder(3L).build();
		loanCache.add(loan1);
		reset(loanCache);
		
		loans = Arrays.asList(loan1, loan2);
	}
	
	@Test
	public void reportNewLoans_firstCall() {
		loanCache.clear();
		when(loanService.getLoans()).thenReturn(loans);
		
		scheduledTasks.reportNewLoans();
		verify(loanService).getLoans();
		verify(loanCache).addAll(loans);
		
		assertThat(loanCache).containsAll(loans);
	}
	
	@Test
	public void reportNewLoans_noNewLoan() {
		when(loanService.getLoans()).thenReturn(loanCache);
		
		scheduledTasks.reportNewLoans();
		verify(loanService).getLoans();
		verify(loanCache, times(0)).addAll(anyCollection());
		
		assertThat(loanCache).contains(loan1);
	}
	
	@Test
	public void reportNewLoans_newLoan() {
		when(loanService.getLoans()).thenReturn(loans);
		
		scheduledTasks.reportNewLoans();
		verify(loanService).getLoans();
		verify(loanCache).addAll(Arrays.asList(loan2));
		
		assertThat(loanCache).containsAll(loans);
	}
	
	@Test
	public void reportNewLoans_exception() {
		when(loanService.getLoans()).thenThrow(IllegalStateException.class);
		
		scheduledTasks.reportNewLoans();
		verify(loanService).getLoans();
		verifyNoMoreInteractions(loanCache, loanService);
	}
}
