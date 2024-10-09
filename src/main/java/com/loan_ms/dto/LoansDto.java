package com.loan_ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Schema(
		name = "Loans",
		description = "Schema to hold Loan information"
		
		)
public class LoansDto {

	@NotEmpty(message = "Mobile Number can not be a null or empty")
	@Pattern(regexp = "(^$|[0-9]{10})" ,message = "Mobile Number must be 10 digits")
	@Schema(
				name = "mobileNumber",
				description = "Mobile Number of Customer",
				example = "4365327698"
			)
	private String mobileNumber;
	
	
	@NotEmpty(message = "Loan Number can not be a null or empty")
	@Pattern(regexp="(^$|[0-9]{12})",message = "LoanNumber must be 12 digits")
	@Schema(
			  name = "loanNumber",
			  description = "Loan Number of the customer",
			  example = "548732457654"
			)
	private String loanNumber;
	
	
	@NotEmpty(message="LoanType can not be a null or empty")
	@Schema(
			 name = "loanType",
			 description = "Type of the loan",
			 example = "HOME_LOAN"
			)
	private String loanType;
	
	@Positive(message = "Total loan amount should be greater than zero")
	@Schema(
				name = "totalLoan",
				description = "Total loan amount",
				example = "1000000"
			)
	private int totalLoan;
	
	@PositiveOrZero(message = "Total loan amount paid should be equal or greater than zero")
	@Schema(
			  name = "amountPaid",
			  description = "Total loan amount paid",
			  example = "1000"
			)
	private int amountPaid;
	
	@PositiveOrZero(message = "Total outstanding amount should be equal or greater than zero")
	@Schema(
				name = "outstandingAmount",
				description = "Total outstanding amount against a loan",
				example = "99000"
			)
	private int outstandingAmount;

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getLoanNumber() {
		return loanNumber;
	}

	public void setLoanNumber(String loanNumber) {
		this.loanNumber = loanNumber;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public int getTotalLoan() {
		return totalLoan;
	}

	public void setTotalLoan(int totalLoan) {
		this.totalLoan = totalLoan;
	}

	public int getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(int amountPaid) {
		this.amountPaid = amountPaid;
	}

	public int getOutstandingAmount() {
		return outstandingAmount;
	}

	public void setOutstandingAmount(int outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}

	@Override
	public String toString() {
		return "LoansDto [mobileNumber=" + mobileNumber + ", loanNumber=" + loanNumber + ", loanType=" + loanType
				+ ", totalLoan=" + totalLoan + ", amountPaid=" + amountPaid + ", outstandingAmount=" + outstandingAmount
				+ "]";
	}

	public LoansDto(String mobileNumber, String loanNumber, String loanType, int totalLoan, int amountPaid,
			int outstandingAmount) {
		super();
		this.mobileNumber = mobileNumber;
		this.loanNumber = loanNumber;
		this.loanType = loanType;
		this.totalLoan = totalLoan;
		this.amountPaid = amountPaid;
		this.outstandingAmount = outstandingAmount;
	}

	public LoansDto() {
		super();
	}
	
	
}
