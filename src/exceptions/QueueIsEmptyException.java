package exceptions;

public class QueueIsEmptyException extends RuntimeException{
    private String message;

    public QueueIsEmptyException(String message){
        super(message);
        this.message = message;
    }

    @Override
    public String toString(){
        return message;
    }
}
