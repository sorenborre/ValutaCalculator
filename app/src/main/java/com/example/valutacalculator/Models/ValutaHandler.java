package com.example.valutacalculator.Models;

import java.util.List;

public class ValutaHandler implements HttpResponseObserver {

    private ObservableValutaRequester VR;
    private ValutaCalculator VC;
    private double multiplier = 1.0;
    private  String currentBase = "";

    public ValutaHandler(ObservableValutaRequester VR) {
        this.VR = VR;
        this.VR.addObserver(this);
        VC = new ValutaCalculator();
    }


    //If() checks if the same base/currency as the last calculation is selected, if it is then don't make an API call and use the current data.
    public void getValuta(String base) {
        if (!currentBase.equals(base)) {
            currentBase = base;
            VR.getExchange(base);
        } else
            VC.calculateValuta(VR.getBaseValues(), multiplier);
    }

    //retrieves the list of currency names
    public List<String> getBaseNames() {
        return VR.getBaseNames();
    }

    public void register(ValutaCalculatorObserver observer) {
        VC.addObserver(observer);
    }

    //sets the amount the valuta gets multiplied
    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public void OnSuccess() {
        VC.calculateValuta(VR.getBaseValues(), multiplier);
    }


    @Override
    public void OnFailure() {
        VC.noDataRecieved();
    }


}
