package br.com.lm.shop.exception;

public class InvalidCartStateException extends CartBusinessException {

	private static final long serialVersionUID = 1L;

	public InvalidCartStateException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvalidCartStateException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public InvalidCartStateException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidCartStateException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidCartStateException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
