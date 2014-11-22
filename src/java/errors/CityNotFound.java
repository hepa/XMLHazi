package errors;

public class CityNotFound extends Exception {

    public CityNotFound() {
        super("Nincs ilyen város!");
    }

    public CityNotFound(String string) {
        super(string);
    }   
        
}
