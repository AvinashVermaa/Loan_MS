package com.loan_ms.mapper;

import com.loan_ms.dto.LoansDto;
import com.loan_ms.entity.Loans;

public class LoansMapper {

	private LoansMapper() {
		//restrict the initialization of the class outside by others:
	}
	
	public static final LoansDto mapToLoansDto(Loans loans,LoansDto dto) {
		dto.setAmountPaid(loans.getAmountPaid());
		dto.setLoanNumber(loans.getLoanNumber());
		dto.setLoanType(loans.getLoanType());
		dto.setMobileNumber(loans.getMobileNumber());
		dto.setOutstandingAmount(loans.getOutstandingAmount());
		dto.setTotalLoan(loans.getTotalLoan());
		
		return dto;
	}
	
	public static final Loans mapToLoans(LoansDto dto,Loans loans) {
		loans.setAmountPaid(dto.getAmountPaid());
		loans.setLoanNumber(dto.getLoanNumber());
		loans.setLoanType(dto.getLoanType());
		loans.setMobileNumber(dto.getMobileNumber());
		loans.setOutstandingAmount(dto.getOutstandingAmount());
		loans.setTotalLoan(dto.getTotalLoan());
		
		return loans;
	}
	
}
