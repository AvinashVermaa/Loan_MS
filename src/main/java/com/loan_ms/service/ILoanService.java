package com.loan_ms.service;

import com.loan_ms.dto.LoansDto;

public interface ILoanService {

	public void createLoan(String mobileNumber);
	
	public LoansDto fetchLoan(String mobileNumber);
	
	public boolean updateLoan(LoansDto dto);
	
	public boolean deleteLoan(String mobileNumber);
}
