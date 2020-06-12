package com.example.valutacalculator.exchangeratesapiDAL;

import com.example.valutacalculator.Models.HttpResponseObserver;
import com.example.valutacalculator.Models.ObservableValutaRequester;
import com.example.valutacalculator.Models.Observer;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;


public class AsyncHttpClientValutaCommunicator extends JsonRatesMapper implements ObservableValutaRequester {

    private List<HttpResponseObserver> observers = new ArrayList<>();
    private List<String> baseName = new ArrayList<>();
    private Map<String, Double> valutas = new Hashtable<>();

    private List<String> addFieldsToList() {
        Field[] fields = Rates.class.getFields();
        for (Field field : fields)
            baseName.add(field.getName().toUpperCase());
        return baseName;
    }

    // Makes an asynchronous 'get' call to the webApi of exchangeratesapi.io
    public void getExchange(String base) {
        new AsyncHttpClient().get("https://api.exchangeratesapi.io/latest?base=" + base, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                setBaseValues(response, valutas, baseName);
                notifyObservers();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                for (HttpResponseObserver observer : observers)
                    observer.OnFailure();
            }
        });
    }

    @Override
    public List<String> getBaseNames() {
        return addFieldsToList();
    }

    @Override
    public Map<String, Double> getBaseValues() {
        return valutas;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add((HttpResponseObserver) observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove((HttpResponseObserver) observer);
    }

    @Override
    public void notifyObservers() {
        for (HttpResponseObserver observer : observers)
            observer.OnSuccess();
    }


}
