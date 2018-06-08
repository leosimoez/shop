package br.com.lm.shop.exception;

public class InvalidProductException extends CartBusinessException {

	private static final long serialVersionUID = 1L;

	public InvalidProductException() {
		super();
	}

	public InvalidProductException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InvalidProductException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidProductException(String message) {
		super(message);
	}

	public InvalidProductException(Throwable cause) {
		super(cause);
	}
	
	

}
