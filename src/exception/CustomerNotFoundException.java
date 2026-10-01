package exception;


public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String massage){
        super(massage);
    }
}
