package com.shopAddress.modal;

/**
 * Modal class for Exception Message
 * 
 * @author grover_n
 *
 */
public class ExceptionMessage {

	private String code;
	private String message;
	private String messageDetail;

	public String getMessageDetail() {
		return messageDetail;
	}

	public void setMessageDetail(String messageDetail) {
		this.messageDetail = messageDetail;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
