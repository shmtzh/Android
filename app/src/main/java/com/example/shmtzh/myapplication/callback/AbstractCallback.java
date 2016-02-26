package com.example.shmtzh.myapplication.callback;

import com.example.shmtzh.myapplication.rest.ClientErrorRest;
import com.example.shmtzh.myapplication.rest.ErrorRest;
import com.example.shmtzh.myapplication.bus.EventBus;
import com.example.shmtzh.myapplication.event.InvalidCredentialsEvent;
import com.example.shmtzh.myapplication.event.ServerUnreachableEvent;
import com.example.shmtzh.myapplication.event.UnexpectedRestErrorEvent;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedInput;

/**
 * Created by shmtzh on 2/25/16.
 */
public abstract class AbstractCallback<T> implements Callback<T> {

    public abstract EventBus getBus();

    @Override
    public void failure(final RetrofitError e) {
        if (isNetworkError(e)) {
            System.out.println("Server unreachable post");
            getBus().post(ServerUnreachableEvent.getEvent(hasUserInteraction()));
        }
    }

    public String getBodyFromError(final RetrofitError e) {
        final Response response = e.getResponse();

        if (response == null) {
            return null;
        }

        final TypedInput body = response.getBody();
        if (body == null) {
            return null;
        }

        String errorJson = new String(((TypedByteArray) body).getBytes());
        return errorJson;
    }

    public boolean isNetworkError(final RetrofitError e) {
        final Response response = e.getResponse();

        if (response == null) {
            return true;
        }

        if (e.isNetworkError()) {
            return true;
        }

        final TypedInput body = response.getBody();
        if (body == null) {
            return true;
        }

        // TODO(JT) - Get error not by the string description
        if (e.toString().contains("504 Unsatisfiable Request")) {
            return true;
        }

        // TODO(JT) - Get error not by the string description
        if (e.toString().contains("502 Bad Gateway")) {
            return true;
        }

        // Technically this is a server error, not an network error
        ClientErrorRest clientError = parseError(e);
        return clientError == null;
    }

    public boolean validCredentials(final RetrofitError e) {
        if (e.getResponse().getStatus() == 401) {
            System.out.println("INVALID TOKEN");
            return false;
        }
        return true;
    }

    public void postInvalidCredentialsEvent() {
        getBus().post(InvalidCredentialsEvent.getEvent());
    }

    public void postUnexpectedRestError() {
        getBus().post(UnexpectedRestErrorEvent.getEvent(hasUserInteraction()));
    }


    public ErrorRest getErrorRest(RetrofitError error) {
        ClientErrorRest clientError = parseError(error);
        return clientError.getError();
    }

    public ClientErrorRest parseError(final RetrofitError retrofitError) {
        try {
            return (ClientErrorRest) retrofitError.getBodyAs(ClientErrorRest.class);
        } catch (RuntimeException e) {
            return null;
        }
    }

    public abstract boolean hasUserInteraction();
}
