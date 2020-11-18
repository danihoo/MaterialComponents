package de.danihoo94.www.spinnertest;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

import de.danihoo94.www.materialcomponents.MaterialSpinner;

public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        MaterialSpinner spinnerOutlined = findViewById(R.id.outlined);
        TestAdapter adapter = new TestAdapter(this);
        adapter.add(new Item("Test1"));
        adapter.add(new Item("Test2"));
        spinnerOutlined.setAdapter(adapter);

        MaterialSpinner spinnerFilled = findViewById(R.id.filled);
        spinnerFilled.setAdapter(adapter);

        spinnerOutlined.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(adapter.getSelectedObject());
            }
        });
    }
}