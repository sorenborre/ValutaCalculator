package com.example.valutacalculator.Models;

public interface HttpResponseObserver extends Observer {
     void OnSuccess();
     void OnFailure();
}
