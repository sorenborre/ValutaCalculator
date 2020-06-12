package com.example.valutacalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.valutacalculator.Presenters.MainActivityPresenter;
import com.example.valutacalculator.Presenters.ValutaCalculatorView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, ValutaCalculatorView {

    private MainActivityPresenter presenter;
    private Spinner topSpinner;
    private EditText multiplierText;
    private String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainActivityPresenter(this);
        topSpinner = findViewById(R.id.top_spinner);
        topSpinner.setOnItemSelectedListener(this);
        multiplierText = findViewById(R.id.multiplier_text);
        Button ButtonCalculate = findViewById(R.id.button_calculate);
        ButtonCalculate.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        presenter.getBaseNames();
        super.onStart();
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.button_calculate) {
            double multiplier = Double.parseDouble(multiplierText.getText().toString());
            presenter.getExchange(item);
            presenter.setMultiplier(multiplier);
        }
    }

    //extract selected spinner item
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    //Not my favorite solution
    public void populateValutaSpinner(Map<String, Double> map) {

        ArrayList<String> list = new ArrayList<>();

        for (Map.Entry<String, Double> m : map.entrySet())
            list.add(m.getKey() + ": " + m.getValue().toString());

        ListView listView = findViewById(R.id.listView2);
        ArrayAdapter<String> listViewArray = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(listViewArray);
        listViewArray.notifyDataSetChanged();
    }

    //populates the topSpinner with the baseNames/currency names
    @Override
    public void setBaseNames(List<String> baseNames) {
        ArrayAdapter<String> topSpinnerArray = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, baseNames);
        topSpinnerArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        topSpinner.setAdapter(topSpinnerArray);
    }
}
