package com.loan_ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.loan_ms.constants.LoansConstants;
import com.loan_ms.dto.ErrorResponseDto;
import com.loan_ms.dto.LoansDto;
import com.loan_ms.dto.ResponseDto;
import com.loan_ms.service.ILoanService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;


@Controller
@RequestMapping(value = {"/api"},produces=MediaType.APPLICATION_JSON_VALUE)
@Validated
@Tag(
		name = "CRUD REST APIs for Loans in EazyBank",
		description = "CRUD REST APIs in EazyBank to CREATE, UPDATE, FETCH AND DELETE loan details"
	)
public class LoansController {

	private ILoanService loansService;
	
	@Autowired
	public LoansController(ILoanService loansService) {
		this.loansService = loansService;
		System.out.println("Inside LoansController constructor");
	}
	
	
	@Operation(
				summary = "reate Loan REST API",
				description = "REST API to create new loan inside EazyBank"
			
			)
	
	@ApiResponse(
				responseCode = "201",
				description = "HTTP Status CREATED"
			)
	
	@ApiResponse(
				responseCode = "500",
				description = "HTTP Status Internal Server Error",
				content = @Content(
							schema = @Schema(implementation = ErrorResponseDto.class)
						)
			)
	
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createLoan(@RequestParam("mobileNumber") 
	@Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
	@Valid
	String mobileNumber){
		loansService.createLoan(mobileNumber);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));
		
	}
	
	@Operation(
				summary = "Fetch Loan Details REST API",
				description = "REST API to fetch loan details based on a mobile number"
			
			)
	
	@ApiResponse(
				responseCode = "200",
				description = "HTTP Status OK"
			)
	
	@ApiResponse(
				responseCode = "500",
				description = "HTTP Status Internal Server Error",
				content = @Content(
							schema = @Schema(implementation = ErrorResponseDto.class)
						)
			)
	@GetMapping("/fetch")
	public ResponseEntity<LoansDto> fetchLoan(@RequestParam("mobileNumber") 
	@Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
	@Valid
	String mobileNumber){
		
		LoansDto loansDto = loansService.fetchLoan(mobileNumber);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(loansDto);
	}
	
	@Operation(
				summary = "Update Loan Details REST API",
				description = "REST API to update loan details based on a loan number"
			)
	
	@ApiResponse(
			 responseCode = "200",
			 description = "HTTP Status OK"
			)
	
	@ApiResponse(
				responseCode = "500",
				description = "Expectation Failed",
				content = @Content(
							schema = @Schema(implementation = ErrorResponseDto.class)
						)
			)
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> updateLoanDetails(@Valid @RequestBody LoansDto dto){
		boolean isUpdated = loansService.updateLoan(dto);
		if(isUpdated) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(LoansConstants.STATUS_200, "Loan Details was updated : loan number "+dto.getLoanNumber()));
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDto(LoansConstants.STATUS_500, "Something went wrong, Please contact to dev teams : loan number"+dto.getLoanNumber()));
		}
	}
	
	
	@Operation(
				summary = "Delete Loan Details REST API",
				description = "REST API to delete Loan details based on a mobile number"
			)
	@ApiResponse(
				responseCode = "200",
				description = "HTTP Status OK"
			)
	@ApiResponse(
				responseCode = "417",
				description = "Expectation Failed",
				content = @Content(
							schema = @Schema(implementation = ErrorResponseDto.class)
						)
			)
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteLoanDetails(
			@Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
			@Valid
			@RequestParam("mobileNumber") String mobileNumber){
		boolean isDeleted = loansService.deleteLoan(mobileNumber);
		if(isDeleted) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(LoansConstants.MESSAGE_200, "Loans Details was deleted successfully mobile Number : "+mobileNumber));
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDto(LoansConstants.STATUS_417,LoansConstants.MESSAGE_417_DELETE));
		}
	}
	
	
}
