package exceptions;

/**
 * The `QueueIsEmptyException` class is a custom exception that is thrown when a queue is empty.
 */
public class QueueIsEmptyException extends RuntimeException{
    private String message;

// The code snippet is defining a constructor for the `QueueIsEmptyException` class.
    public QueueIsEmptyException(String message){
        super(message);
        this.message = message;
    }

    @Override
// The `toString()` method is overriding the default `toString()` method of the `RuntimeException`
// class. It returns the `message` string, which is the custom message passed to the
// `QueueIsEmptyException` constructor. This allows you to get a string representation of the exception
// object when it is printed or used in a string concatenation.
    public String toString(){
        return message;
    }
}
