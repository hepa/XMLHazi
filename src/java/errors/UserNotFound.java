package errors;

public class UserNotFound extends Exception {

    public UserNotFound() {
        super("Nincs ilyen felhasználó!");
    }    
    
    public UserNotFound(String string) {
        super(string);
    }
    
}
