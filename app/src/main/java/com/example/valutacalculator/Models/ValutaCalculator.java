package com.example.valutacalculator.Models;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class ValutaCalculator implements Observable {

   private List<ValutaCalculatorObserver> observers = new ArrayList<>();
   private Map<String, Double> map;

    public void calculateValuta(Map<String, Double> exchangeRate, Double amount) {

        map = new Hashtable<>();
        double value;

        for (Map.Entry<String, Double> m : exchangeRate.entrySet()) {
            value = Math.round(((Double) m.getValue() * amount) * 100) / 100.0;
            map.put((String) m.getKey(), value);
        }
        notifyObservers();
    }

    //if no httpResponse
    public void noDataRecieved() {
        map.put("no data", 0.0);
        notifyObservers();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add((ValutaCalculatorObserver) observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove((ValutaCalculatorObserver) observer);
    }

    @Override
    public void notifyObservers() {
        for (ValutaCalculatorObserver observer : observers)
            observer.OnCompletedCalculation(map);
    }
}
