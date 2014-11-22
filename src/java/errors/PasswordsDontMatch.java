package errors;

public class PasswordsDontMatch extends Exception {

    public PasswordsDontMatch() {
        super("Az azonosítóhoz tartozó jelszó helytelen!");
    }

    public PasswordsDontMatch(String string) {
        super(string);
    }        
    
}
