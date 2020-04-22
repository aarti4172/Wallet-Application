package wallet;

public class CheckException extends Exception {
	
	public CheckException(String string , Throwable err) {
		super(string,err);
	}

	public String getMessage() {
		return super.getMessage();
	}

}
