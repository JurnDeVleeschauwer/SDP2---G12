package exceptions;

public class MvoGoalException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public MvoGoalException(String errorMessage) {
	        super(errorMessage);
	    }

}
