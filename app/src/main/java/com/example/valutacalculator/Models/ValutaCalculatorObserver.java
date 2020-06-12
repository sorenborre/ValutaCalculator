package com.example.valutacalculator.Models;

import java.util.Map;

public interface ValutaCalculatorObserver extends Observer {
    void OnCompletedCalculation(Map<String, Double> valuta);
}
