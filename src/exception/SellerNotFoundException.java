package exception;


public class SellerNotFoundException extends RuntimeException {
    public SellerNotFoundException(String massage){
        super(massage);
    }
}
