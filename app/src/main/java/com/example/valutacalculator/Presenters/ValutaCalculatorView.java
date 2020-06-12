package com.example.valutacalculator.Presenters;

import java.util.List;
import java.util.Map;

public interface ValutaCalculatorView {
    void setBaseNames(List<String> baseNames);
    void populateValutaSpinner(Map<String,Double> map);
}
