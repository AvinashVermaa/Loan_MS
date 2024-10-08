package com.loan_ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
			name = "Response",
			description = "Schema to hold successful response information"
		)
public class ResponseDto {

	@Schema(
			 description = "Status code in the response",
			 example = "2xx"
			)
	private String statusCode;
	
	@Schema(
				description = "Status message in the response",
				example = "message...."
			)
	private String statusMsg;

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	@Override
	public String toString() {
		return "ResponseDto [statusCode=" + statusCode + ", statusMsg=" + statusMsg + "]";
	}

	public ResponseDto(String statusCode, String statusMsg) {
		super();
		this.statusCode = statusCode;
		this.statusMsg = statusMsg;
	}

	public ResponseDto() {
		super();
	}
	
	
}
