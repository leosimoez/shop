package br.com.lm.shop.exception;

public class CartBusinessException extends Exception {

	private static final long serialVersionUID = 1L;

	public CartBusinessException() {
		super();
	}

	public CartBusinessException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CartBusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public CartBusinessException(String message) {
		super(message);
	}

	public CartBusinessException(Throwable cause) {
		super(cause);
	}

}
