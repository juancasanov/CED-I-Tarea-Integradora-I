package exceptions;

public class StackIsEmptyException extends RuntimeException{
    private String message;

    public StackIsEmptyException(String message){
        super(message);
        this.message = message;
    }

    @Override
    public String toString(){
        return message;
    }
}
