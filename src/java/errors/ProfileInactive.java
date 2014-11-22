package errors;

public class ProfileInactive extends Exception {

    public ProfileInactive() {
        super("A fiók inaktív!");
    }

    public ProfileInactive(String string) {
        super(string);
    }
        
}
