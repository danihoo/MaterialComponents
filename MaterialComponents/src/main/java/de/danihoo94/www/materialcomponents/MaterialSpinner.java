package de.danihoo94.www.materialcomponents;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.Editable;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MaterialSpinner extends TextInputLayout {
    private TextInputEditText editText;

    /**
     * super class constructor with custom layout inflation
     *
     * @param context context
     */
    public MaterialSpinner(@NonNull Context context) {
        super(context);
        setupView(null, 0);
    }

    /**
     * super class constructor with custom layout inflation
     *
     * @param context context
     * @param attrs   attribute set
     */
    public MaterialSpinner(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setupView(attrs, 0);
    }

    /**
     * super class constructor with custom layout inflation
     *
     * @param context      context
     * @param attrs        attribute set
     * @param defStyleAttr style
     */
    public MaterialSpinner(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupView(attrs, defStyleAttr);
    }

    /**
     * Assign a text to the TextInputEditText
     *
     * @param text text to be assigned
     * @param type buffer type
     */
    public void setText(CharSequence text, TextView.BufferType type) {
        editText.setText(text, type);
    }

    /**
     * Assign a text to the TextInputEditText
     *
     * @param resId if od the string to be assigned
     * @param type  buffer type
     */
    public void setText(int resId, TextView.BufferType type) {
        editText.setText(resId, type);
    }

    /**
     * Set text appearance of the TextInputEditText
     *
     * @param context context of the view
     * @param resId   appearance resource id
     */
    public void setTextAppearance(Context context, int resId) {
        editText.setTextAppearance(context, resId);
    }

    /**
     * Set text appearance of the TextInputEditText
     *
     * @param resId appearance resource id
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setTextAppearance(int resId) {
        editText.setTextAppearance(resId);
    }

    /**
     * Setup custom view
     *
     * @param attrs        attribute set
     * @param defStyleAttr style attribute
     */
    private void setupView(@Nullable AttributeSet attrs, int defStyleAttr) {

        inflate(getContext(), R.layout.view_material_spinner, this);
        editText = findViewById(R.id.view_material_edit_text);

        if (attrs != null) {
            TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.MaterialSpinner, defStyleAttr, 0);

            try {
                //TODO
                //String editTextHint = a.getString(R.styleable.MaterialEditTextView_editTextHint);
                //editText.setHint(editTextHint);

            } finally {
                a.recycle();
            }
        }
    }

    /**
     * Set an error message at the TextInputEditText
     *
     * @param errorText error text to be assigned, null to clear
     */
    public void setEditTextError(@Nullable CharSequence errorText) {
        editText.setError(errorText);
    }

    /**
     * Get the text of the TextInputEditText
     *
     * @return text
     */
    public Editable getText() {
        return editText.getText();
    }

    /**
     * Assign a text to the TextInputEditText
     *
     * @param text text to be assigned
     */
    public void setText(CharSequence text) {
        editText.setText(text);
    }

    /**
     * Assign a text to the TextInputEditText
     *
     * @param resId if od the string to be assigned
     */
    public void setText(int resId) {
        editText.setText(resId);
    }

    /**
     * Get current hint text from TextInputEditText
     *
     * @return hint as text
     */
    public CharSequence getEditTextHint() {
        return editText.getHint();
    }

    /**
     * Set a hint message at the TextInputEditText
     *
     * @param hintText hint text to be assigned, null to clear
     */
    public void setEditTextHint(@Nullable CharSequence hintText) {
        editText.setHint(hintText);
    }

}
