package com.myretail.product.api.exception;

/**
 * Custom exception if resource is not found.
 *
 * @author Rahim Dhuka
 */
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4723373526937123422L;

	/**
	   * Creates exception with the provided <code>message</code> and <code>cause</code>.
	   *
	   * @param message The exception's detailed message.
	   * @param cause The exception's cause.
	   */
	  public ResourceNotFoundException(String message, Throwable cause) {
	    super(message, cause);
	  }

	  /**
	   * Creates exception with the provided <code>message</code>.
	   *
	   * @param message The exception's detailed message.
	   */
	  public ResourceNotFoundException(String message) {
	    super(message);
	  }

}
