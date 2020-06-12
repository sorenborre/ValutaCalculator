package com.example.valutacalculator.Presenters;

import com.example.valutacalculator.exchangeratesapiDAL.AsyncHttpClientValutaCommunicator;
import com.example.valutacalculator.Models.ValutaCalculatorObserver;
import com.example.valutacalculator.Models.ValutaHandler;

import java.util.Map;

public class MainActivityPresenter implements ValutaCalculatorObserver {

    private ValutaCalculatorView mainView;
    private ValutaHandler valutaHandler;

    public MainActivityPresenter(ValutaCalculatorView view) {

        valutaHandler = new ValutaHandler(new AsyncHttpClientValutaCommunicator());
        mainView = view;
        valutaHandler.register(this);
    }

    public void getBaseNames() {
        mainView.setBaseNames(valutaHandler.getBaseNames());
    }

    public void getExchange(String base) {
        valutaHandler.getValuta(base);
    }

    public void setMultiplier(double multiplier) {
        valutaHandler.setMultiplier(multiplier);
    }

    @Override
    public void OnCompletedCalculation(Map<String, Double> valuta) {
        mainView.populateValutaSpinner(valuta);
    }
}
