package msalah.mal.com.themovieapp.controllers.exception;


import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ExceptionUtil {

    private static ExceptionUtil sharedInstance;

    private final static String EXCEPTION_TAG = "Exception";

    private Map handlers;

    private Activity currentActivity;

    private ExceptionUtil() {
        handlers = new HashMap <String, OnExceptionHandle>();
    }

    public static ExceptionUtil getSharedInstance() {

        if (sharedInstance == null) {
            sharedInstance = new ExceptionUtil();
        }
        return sharedInstance;
    }

    public void registerCurrentActivity(AppCompatActivity activity) {
        sharedInstance.currentActivity = activity;
    }

    public void registerHandlerForException(OnExceptionHandle handler, String ExceptionClassName) {
        sharedInstance.handlers.put(ExceptionClassName , handler);
    }

    public void throwException(Class exceptionClass)  {

        Constructor[] ctors = exceptionClass.getDeclaredConstructors();
        Constructor constructor = ctors [0];
        CFBException exception = null;
        try {
            exception = (CFBException) constructor.newInstance();
        } catch (InstantiationException e) {
            Log.e(EXCEPTION_TAG, e.toString());
        } catch (IllegalAccessException e) {
            Log.e(EXCEPTION_TAG, e.toString());
        } catch (InvocationTargetException e) {
            Log.e(EXCEPTION_TAG, e.toString());
        }
        exception.handleException();

        if (handlers.containsKey(exceptionClass.getSimpleName())) {
            OnExceptionHandle handle = (OnExceptionHandle) handlers.get(exceptionClass.getSimpleName());
            handle.onExceptionOccurred(exception);
        }
    }


}
