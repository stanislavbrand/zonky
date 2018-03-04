package cz.zonky.demo.service;

import java.util.List;

import cz.zonky.demo.model.Loan;

public interface LoanService {

	/**
	 * Get all loans using REST API. 
	 * 
	 * @return list of all loans sorted by the publishedDate.
	 * @throws IllegalStateException in case of any problem with the server side. 
	 */
	List<Loan> getLoans();
}
