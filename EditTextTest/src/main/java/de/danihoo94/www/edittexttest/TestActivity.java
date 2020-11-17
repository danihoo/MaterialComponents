package de.danihoo94.www.edittexttest;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.appcompat.app.AppCompatActivity;

import de.danihoo94.www.materialcomponents.MaterialEditText;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        MaterialEditText outlinedTextWatcher = findViewById(R.id.outlined_textwatcher);
        MaterialEditText filledTextWatcher = findViewById(R.id.filled_textwatcher);

        outlinedTextWatcher.addTextChangedListener(new TestTextWatcher(outlinedTextWatcher));
        filledTextWatcher.addTextChangedListener(new TestTextWatcher(filledTextWatcher));
    }

    private static class TestTextWatcher implements TextWatcher {

        private final MaterialEditText editText;

        public TestTextWatcher(MaterialEditText editText) {
            this.editText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editText.getText().toString().length() > editText.getCounterMaxLength()) {
                editText.setError("Tet is too long");
            } else {
                editText.setError(null);
            }
        }
    }
}