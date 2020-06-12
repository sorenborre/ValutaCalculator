package com.example.valutacalculator.exchangeratesapiDAL;

import com.google.gson.Gson;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public abstract class JsonRatesMapper {

    //Populates the valutas with a generic method that iterates through Rates' getMethods to retrieve the data AsyncHttpClientValutaHandler has populated Rates with
    void setBaseValues(String response,  Map<String, Double> valutas, List<String> baseName) {
        Rate rate = new Gson().fromJson(response, Rate.class);
        try {
            for (int i = 0; i < baseName.size(); i++) {
                Method method = rate.getRates().getClass().getDeclaredMethod("get" + baseName.get(i));
                Double result = (Double) method.invoke(rate.getRates());
                valutas.put(baseName.get(i), result);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
