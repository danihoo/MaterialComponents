package de.danihoo94.www.materialcomponents;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputLayout;

public class MaterialSpinner extends TextInputLayout {
    private static final int ELLIPSIZE_END = 3;
    private AutoCompleteTextView spinner;
    private MaterialSpinnerAdapter adapter;

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
        spinner.setText(text, type);
    }

    /**
     * Assign a text to the TextInputEditText
     *
     * @param resId if od the string to be assigned
     * @param type  buffer type
     */
    public void setText(int resId, TextView.BufferType type) {
        spinner.setText(resId, type);
    }

    /**
     * Set text appearance of the TextInputEditText
     *
     * @param context context of the view
     * @param resId   appearance resource id
     */
    public void setTextAppearance(Context context, int resId) {
        spinner.setTextAppearance(context, resId);
    }

    /**
     * Set text appearance of the TextInputEditText
     *
     * @param resId appearance resource id
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setTextAppearance(int resId) {
        spinner.setTextAppearance(resId);
    }

    /**
     * Setup custom view
     *
     * @param attrs        attribute set
     * @param defStyleAttr style attribute
     */
    private void setupView(@Nullable AttributeSet attrs, int defStyleAttr) {

        inflate(getContext(), R.layout.view_material_spinner, this);
        spinner = findViewById(R.id.view_material_spinner_autocomplete);

        spinner.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showDropDown();
            }
        });

        spinner.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                showDropDown();
            }
        });


        if (attrs != null) {
            TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.MaterialSpinner, defStyleAttr, 0);

            try {
                ColorStateList textColor = a.getColorStateList(R.styleable.MaterialSpinner_android_textColor);
                if (textColor != null) {
                    spinner.setTextColor(textColor);
                } else {
                    spinner.setTextColor(ContextCompat.getColor(getContext(), android.R.color.black));
                }

                ColorStateList backgroundTint = a.getColorStateList(R.styleable.MaterialSpinner_android_backgroundTint);
                if (textColor != null) {
                    setBackgroundTintList(backgroundTint);
                } else {
                    setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.white));
                }

                boolean filled = a.getBoolean(R.styleable.MaterialSpinner_filled, false);
                setFilled(filled);
            } finally {
                a.recycle();
            }
        }
    }

    /**
     * Sets if the spinner currently uses the filled theme (and not the outlined one)
     *
     * @param filled true, or, if outlined theme, false
     */
    public void setFilled(boolean filled) {
        float paddingTop, paddingBottom;

        if (filled) {
            paddingTop = getResources().getDimension(R.dimen.material_spinner_padding_top_filled);
            paddingBottom = getResources().getDimension(R.dimen.material_spinner_padding_bottom_filled);
        } else {
            paddingTop = getResources().getDimension(R.dimen.material_spinner_padding_top_outlined);
            paddingBottom = getResources().getDimension(R.dimen.material_spinner_padding_bottom_outlined);
        }

        spinner.setPadding(spinner.getPaddingLeft(), (int) paddingTop, spinner.getPaddingRight(), (int) paddingBottom);
    }

    /**
     * Set the maximum line count of the displayed element
     *
     * @param lines count of lines
     */
    public void setMaxLines(int lines) {
        spinner.setMaxLines(lines);
    }

    /**
     * Set the background color of the AutoCompleteTextView object
     *
     * @param color color as int
     */
    @Override
    public void setBackgroundColor(int color) {
        spinner.setBackgroundColor(color);
    }

    /**
     * Set the background tint of the AutoCompleteTextView object
     *
     * @param tint color state list
     */
    @Override
    public void setBackgroundTintList(@Nullable ColorStateList tint) {
        spinner.setBackgroundTintList(tint);
    }

    /**
     * Show dropdown menu
     */
    public void showDropDown() {
        spinner.showDropDown();
    }

    /**
     * Set an error message at the TextInputEditText
     *
     * @param errorText error text to be assigned, null to clear
     */
    public void setEditTextError(@Nullable CharSequence errorText) {
        spinner.setError(errorText);
    }

    /**
     * Get the text of the TextInputEditText
     *
     * @return text
     */
    public Editable getText() {
        return spinner.getText();
    }

    /**
     * Assign a text to the TextInputEditText
     *
     * @param text text to be assigned
     */
    public void setText(CharSequence text) {
        spinner.setText(text);
    }

    /**
     * Assign a text to the TextInputEditText
     *
     * @param resId if od the string to be assigned
     */
    public void setText(int resId) {
        spinner.setText(resId);
    }

    /**
     * Get current hint text from TextInputEditText
     *
     * @return hint as text
     */
    public CharSequence getEditTextHint() {
        return spinner.getHint();
    }

    /**
     * Set a hint message at the TextInputEditText
     *
     * @param hintText hint text to be assigned, null to clear
     */
    public void setEditTextHint(@Nullable CharSequence hintText) {
        spinner.setHint(hintText);
    }

    /**
     * Sets the adapter that stores the objects that should be displayed in the dropdown menu
     *
     * @param adapter The adapter to set
     */
    public void setAdapter(MaterialSpinnerAdapter adapter) {
        this.adapter = adapter;
        spinner.setAdapter(adapter);
        adapter.setSpinner(spinner);
    }

    /**
     * Sets the listener that will be notified when the user clicks an item in the drop down list
     * and attaches the adapter to the listener, so that it stays informed about the current
     * selection
     *
     * @param l the item click listener
     */
    public void setOnItemClickListener(@Nullable AdapterView.OnItemClickListener l) {
        spinner.setOnItemClickListener(l);
        adapter.attachToOnItemClickListener();
    }
}
