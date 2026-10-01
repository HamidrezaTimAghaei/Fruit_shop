package exception;


public class FruitNotFoundException extends RuntimeException {
    public FruitNotFoundException(String massage){
        super(massage);
    }
}
