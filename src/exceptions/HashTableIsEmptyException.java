package exceptions;

import java.io.IOException;

/**
 * The `HashTableIsEmptyException` class is a custom exception that is thrown when attempting to
 * perform an operation on an empty hash table.
 */
public class HashTableIsEmptyException extends RuntimeException {

    private String msg;

// The code snippet `public HashTableIsEmptyException(String message){ super(message); this.msg =
// message; }` is the constructor of the `HashTableIsEmptyException` class.
    public HashTableIsEmptyException(String message){
        super(message);
        this.msg = message;
    }

/**
 * The toString() function returns the value of the variable "msg" as a string.
 * 
 * @return The `toString()` method is returning the value of the `msg` variable.
 */
    @Override
    public String toString(){
        return msg;
    }
}

