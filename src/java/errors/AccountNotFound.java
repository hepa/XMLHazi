package errors;

public class AccountNotFound extends Exception {

    public AccountNotFound() {
        super("Nincs ilyen számla!");
    }

    public AccountNotFound(String string) {
        super(string);
    }
    
}
