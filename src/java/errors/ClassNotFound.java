package errors;

public class ClassNotFound extends Exception {

    public ClassNotFound() {
        super("Nincs ilyen osztály!");
    }

    public ClassNotFound(String string) {
        super(string);
    }
    
}
