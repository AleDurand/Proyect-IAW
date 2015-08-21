package proyect.exceptions;

@SuppressWarnings("serial")
public class EntityNotFoundException extends Exception {

	public EntityNotFoundException() {
		super("User not found.");
	}
	
}