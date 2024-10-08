package com.loan_ms.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
			name = "",
			description = ""
		)
public class ErrorResponseDto {

	@Schema(
			 description = "API path invoked by client"
			)
	private String apiPath;
	
	@Schema(
				description = "Error code representing the error happened",
				example = "5xx"
			)
	private HttpStatus errorCode;
	
	@Schema(
				description = "Error message representing the error happened",
				example = "message..."
			)
	private String errorMsg;
	
	@Schema(
			 description = "Time representing when the error happened"
			)
	private LocalDateTime errorTime;

	public String getApiPath() {
		return apiPath;
	}

	public void setApiPath(String apiPath) {
		this.apiPath = apiPath;
	}

	public HttpStatus getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(HttpStatus errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public LocalDateTime getErrorTime() {
		return errorTime;
	}

	public void setErrorTime(LocalDateTime errorTime) {
		this.errorTime = errorTime;
	}

	@Override
	public String toString() {
		return "ErrorResponseDto [apiPath=" + apiPath + ", errorCode=" + errorCode + ", errorMsg=" + errorMsg
				+ ", errorTime=" + errorTime + "]";
	}

	public ErrorResponseDto(String apiPath, HttpStatus errorCode, String errorMsg, LocalDateTime errorTime) {
		super();
		this.apiPath = apiPath;
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.errorTime = errorTime;
	}

	public ErrorResponseDto() {
		super();
	}
	
	
	
	
}
