package exceptions;

/**
 * The `StackIsEmptyException` class is a custom exception class that extends the `RuntimeException`
 * class and represents an exception when a stack is empty.
 */
public class StackIsEmptyException extends RuntimeException{
    private String message;

// The code `public StackIsEmptyException(String message)` is a constructor for the
// `StackIsEmptyException` class. It takes a `message` parameter and calls the constructor of the
// `RuntimeException` class with the `message` parameter using the `super` keyword. It also assigns the
// `message` parameter to the `message` instance variable of the `StackIsEmptyException` class.
    public StackIsEmptyException(String message){
        super(message);
        this.message = message;
    }

/**
 * The toString() function returns the message as a string.
 * 
 * @return The method is returning a String value.
 */
    @Override
    public String toString(){
        return message;
    }
}
