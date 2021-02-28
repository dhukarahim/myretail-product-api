package com.myretail.product.api.exception;

/**
 * Common Exception for all Service failures
 *
 *
 * @author Rahim Dhuka
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 7228735624345696560L;
	
	/**
	   * Creates exception with the provided <code>message</code> and <code>cause</code>.
	   *
	   * @param message The exception's detailed message.
	   * @param cause The exception's cause.
	   */
	  public ServiceException(String message, Throwable cause) {
	    super(message, cause);
	  }

	  /**
	   * Creates exception with the provided <code>message</code>.
	   *
	   * @param message The exception's detailed message.
	   */
	  public ServiceException(String message) {
	    super(message);
	  }

}
