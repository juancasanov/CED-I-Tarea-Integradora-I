package exceptions;

import java.io.IOException;

public class HashTableIsEmptyException extends RuntimeException {

    private String msg;

    public HashTableIsEmptyException(String message){
        super(message);
        this.msg = message;
    }

    @Override
    public String toString(){
        return msg;
    }
}

