package com.shopAddress.exception;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.shopAddress.constants.Constants;
import com.shopAddress.modal.ExceptionMessage;

/**
 * This Class handles all Exceptions
 * 
 * @author grover_n
 *
 */

@ControllerAdvice
public class ControllerExceptionHandler {

	final static Logger logger = Logger.getLogger(ControllerExceptionHandler.class);

	/**
	 * This Method handles Latitude Logitude Not Found Request
	 * 
	 * @param ex
	 * @return
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND) // 404
	@ExceptionHandler(LatLngNotFoundException.class)
	public ResponseEntity<ExceptionMessage> handleNotFound(LatLngNotFoundException ex) {
		logger.debug("Starting Method handleNotFound");
		ExceptionMessage excMsg = new ExceptionMessage();
		excMsg.setCode(HttpStatus.NOT_FOUND.toString());
		excMsg.setMessage(Constants.LAT_LNG_NOT_FOUND_MSG);
		excMsg.setMessageDetail(ex.getMessage());
		logger.debug("Ending Method handleNotFound");

		return new ResponseEntity<ExceptionMessage>(excMsg, HttpStatus.NOT_FOUND);
	}

	/**
	 * This Method handles Invalid Shop Request
	 * 
	 * @param ex
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST) // 400
	@ExceptionHandler(InvalidShopRequestException.class)
	public ResponseEntity<ExceptionMessage> handleBadRequest(InvalidShopRequestException ex) {
		logger.debug("Starting Method handleBadRequest");
		ExceptionMessage excMsg = new ExceptionMessage();
		excMsg.setCode(HttpStatus.BAD_REQUEST.toString());
		excMsg.setMessage(Constants.INVALID_SHOP_REQ_MSG);
		excMsg.setMessageDetail(ex.getMessage());
		logger.debug("Ending Method handleBadRequest");
		return new ResponseEntity<ExceptionMessage>(excMsg, HttpStatus.NOT_FOUND);
	}

	/**
	 * This Method handles Internal Server Error
	 * 
	 * @param ex
	 * @return
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionMessage> handleGeneralError(Exception ex) {
		logger.debug("Starting Method handleGeneralError");
		ExceptionMessage excMsg = new ExceptionMessage();
		excMsg.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		excMsg.setMessage(Constants.INTERNAL_SERVER_ERROR);
		excMsg.setMessageDetail(ex.getMessage());
		logger.debug("Starting Method handleGeneralError");
		return new ResponseEntity<ExceptionMessage>(excMsg, HttpStatus.NOT_FOUND);
	}

	/**
	 * This Method handles Exception Shop Details Not Found
	 * 
	 * @param ex
	 * @return
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND) // 404
	@ExceptionHandler(ShopDetailsNotFoundException.class)
	public ResponseEntity<ExceptionMessage> handleNotFound(ShopDetailsNotFoundException ex) {
		logger.debug("Starting Method handleNotFound");
		ExceptionMessage excMsg = new ExceptionMessage();
		excMsg.setCode(HttpStatus.NOT_FOUND.toString());
		excMsg.setMessage(Constants.SHOP_DETAILS_NOT_FOUND_MSG);
		excMsg.setMessageDetail(ex.getMessage());
		logger.debug("Ending Method handleNotFound");

		return new ResponseEntity<ExceptionMessage>(excMsg, HttpStatus.NOT_FOUND);
	}
}
