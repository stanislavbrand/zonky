package cz.zonky.demo;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cz.zonky.demo.model.Loan;
import cz.zonky.demo.service.LoanService;

@Component
public class TaskScheduler {

	@Autowired
	private LoanService loanService;
	
	@Autowired
	private List<Loan> loanCache;

    private static final Logger log = LoggerFactory.getLogger(TaskScheduler.class);

    @Scheduled(fixedRateString = "${fixedRate.in.milliseconds}")
    public void reportNewLoans() {
    	try {
	    	List<Loan> loans = loanService.getLoans();
	    	List<Loan> newLoans = loans.stream().filter(l -> !loanCache.contains(l)).collect(Collectors.toList());
	    	
	    	if(!newLoans.isEmpty()) {
		    	newLoans.stream().forEach(l -> log.info("Loan: {}, name: {}, number of photos: {}", l, l.getName(), l.getPhotos().size()));
		    	loanCache.addAll(newLoans);
	    	}
	
	    	if(log.isDebugEnabled()) {
	    		log.debug("new loans size {}", newLoans.size());
	    	}
    	} catch (IllegalStateException e) {
			log.warn("Unale to lad new loans.", e);
		}
    }
}
