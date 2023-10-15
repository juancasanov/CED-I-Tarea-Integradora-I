package exceptions;

/**
 * The `NonExistentKeyException` class is a custom exception that is thrown when a non-existent key is
 * accessed.
 */
public class NonExistentKeyException extends RuntimeException{

    private String msg;

// The code `public NonExistentKeyException(String message)` is a constructor for the
// `NonExistentKeyException` class. It takes a `String` parameter `message` and calls the constructor
// of the `RuntimeException` class with the `message` parameter using the `super` keyword.
    public NonExistentKeyException(String message){
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
