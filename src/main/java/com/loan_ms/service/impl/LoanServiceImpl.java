package com.loan_ms.service.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loan_ms.constants.LoansConstants;
import com.loan_ms.dto.LoansDto;
import com.loan_ms.entity.Loans;
import com.loan_ms.exception.LoanAlreadyExistsException;
import com.loan_ms.exception.ResourceNotFoundException;
import com.loan_ms.mapper.LoansMapper;
import com.loan_ms.repository.LoansRepository;
import com.loan_ms.service.ILoanService;

@Service
public class LoanServiceImpl implements ILoanService{
	
	private LoansRepository loansRepo;
	
	@Autowired
	public LoanServiceImpl(LoansRepository loansRepo) {
		this.loansRepo = loansRepo;
		System.out.println("Inside LoansServiceImpl constructor");
	}

	@Override
	public void createLoan(String mobileNumber) {
		Optional<Loans> optional = loansRepo.findByMobileNumber(mobileNumber);
		if(optional.isPresent()) {
			throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber : "+mobileNumber);
		}
		else {
			loansRepo.save(createNewLoan(mobileNumber));
		}
		
	}
	
	private Loans createNewLoan(String mobileNumber) {
		Loans loans = new Loans();
		long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
		loans.setLoanNumber(Long.toString(randomLoanNumber));
		loans.setLoanType(LoansConstants.HOME_LOAN);
		loans.setMobileNumber(mobileNumber);
		loans.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
		loans.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
		loans.setAmountPaid(0);
		return loans;
	}

	@Override
	public LoansDto fetchLoan(String mobileNumber) {
		Loans loans = loansRepo.findByMobileNumber(mobileNumber)
		.orElseThrow(()-> new ResourceNotFoundException("Loans", "mobileNumber", mobileNumber));
		
		return LoansMapper.mapToLoansDto(loans, new LoansDto());
	}

	@Override
	public boolean updateLoan(LoansDto dto) {
		Loans loans = loansRepo.findByLoanNumber(dto.getLoanNumber())
		.orElseThrow(()-> new ResourceNotFoundException("Loans", "loanNumber", dto.getLoanNumber()));
		
		Loans loans2 = LoansMapper.mapToLoans(dto, loans);
		
		loansRepo.save(loans2);
		
		return true;
	}

	@Override
	public boolean deleteLoan(String mobileNumber) {
		Loans loans = loansRepo.findByMobileNumber(mobileNumber)
		.orElseThrow(()-> new ResourceNotFoundException("Loans", "mobileNumber", mobileNumber));
		
		loansRepo.delete(loans);
		
		return true;
	}

}
