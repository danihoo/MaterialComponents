package de.danihoo94.www.spinnertest;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import de.danihoo94.www.materialcomponents.MaterialSpinner;
import de.danihoo94.www.materialcomponents.MaterialSpinnerAdapter;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        MaterialSpinner spinnerOutlined = findViewById(R.id.outlined);
        MaterialSpinnerAdapter<Item> adapter1 = new MaterialSpinnerAdapter<>(this);
        adapter1.add(new Item("Test1"));
        adapter1.add(new Item("Test2"));
        spinnerOutlined.setAdapter(adapter1);

        MaterialSpinner spinnerFilled = findViewById(R.id.filled);
        MaterialSpinnerAdapter<Item> adapter2 = new MaterialSpinnerAdapter<>(this);
        adapter2.add(new Item("No error"));
        adapter2.add(new Item("Error"));
        spinnerFilled.setAdapter(adapter2);

        MaterialSpinner spinnerFilled2 = findViewById(R.id.filled2);
        MaterialSpinnerAdapter<Item> adapter3 = new MaterialSpinnerAdapter<>(this);
        adapter3.add(new Item("Test3"));
        Item selection = new Item("Selection in code works");
        adapter3.add(selection);
        spinnerFilled2.setAdapter(adapter3);
        spinnerFilled2.setSelection(selection);

        spinnerOutlined.setOnSelectionChangedListener((adapter, index) -> System.out.println(adapter.getSelectedObject()));
        spinnerFilled.setOnSelectionChangedListener((adapter, index) -> {
            System.out.println(spinnerFilled.getSelectedObject());
            if (spinnerFilled.getSelectedObject().toString().equals("Error")) {
                spinnerFilled.setError("Fatal Error!");
            } else {
                spinnerFilled.setError(null);
            }
        });
    }
}