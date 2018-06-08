package br.com.lm.shop.exception;

public class SoldOutProductException extends CartBusinessException {

	private static final long serialVersionUID = 1L;

	public SoldOutProductException() {
		super();
	}

	public SoldOutProductException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SoldOutProductException(String message, Throwable cause) {
		super(message, cause);
	}

	public SoldOutProductException(String message) {
		super(message);
	}

	public SoldOutProductException(Throwable cause) {
		super(cause);
	}
	
	

}
