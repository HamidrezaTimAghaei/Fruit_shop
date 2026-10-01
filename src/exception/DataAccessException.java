package exception;


public class DataAccessException extends RuntimeException {
    public DataAccessException(String massage,Throwable cause){
        super(massage,cause);
    }
}
