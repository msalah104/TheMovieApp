package msalah.mal.com.themovieapp.controllers.exception;

/**
 * Created by user on 8/5/16.
 */

public class NetworkException extends CFBException {

    private static final String MESSAGE = "NETWORK EXCEPTION";

    @Override
    protected ExceptionType getExceptionType() {
        return ExceptionType.Log;
    }

    @Override
    protected String getExceptionMessage() {
        return MESSAGE;
    }
}
