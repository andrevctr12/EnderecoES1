package unioeste.geral.exception;

@SuppressWarnings("serial")
public class EnderecoException extends Exception{
    public EnderecoException() {
    }
	public EnderecoException(String message){
		super(message);
	}
    public EnderecoException(Throwable cause) {
        super(cause);
    }
    public EnderecoException(String message, Throwable throwable) {
        super(message, throwable);
    }
    public EnderecoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
