package com.example.valutacalculator.Models;

import java.util.List;
import java.util.Map;

public interface ObservableValutaRequester extends Observable {

    void getExchange(String base);

    Map<String, Double> getBaseValues();

    List<String> getBaseNames();

}
