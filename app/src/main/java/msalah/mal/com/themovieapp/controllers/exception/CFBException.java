package msalah.mal.com.themovieapp.controllers.exception;


import android.util.Log;

public abstract class CFBException extends Exception {

    private OnExceptionHandle exceptionHandler;

    private final static String EXCEPTION_TAG = "Exception";

    public void handleException() {

        Log.i(EXCEPTION_TAG, getExceptionMessage());

        switch (getExceptionType()){
            case ShowWithToast:
                break;
            case ShowWithNotification:
                break;
        }

    }

    protected abstract ExceptionType getExceptionType ();

    protected abstract String getExceptionMessage();


}
