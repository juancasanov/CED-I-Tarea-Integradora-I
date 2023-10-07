package exceptions;

public class NonExistentKeyException extends RuntimeException{

    private String msg;

    public NonExistentKeyException(String message){
        super(message);
        this.msg = message;
    }

    @Override
    public String toString(){
        return msg;
    }
}
