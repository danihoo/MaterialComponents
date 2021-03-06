package de.danihoo94.www.materialcomponents;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class MaterialEditText extends TextInputLayout {
    private TextInputEditText editText;

    /**
     * super class constructor with custom layout inflation
     *
     * @param context context
     */
    public MaterialEditText(@NonNull Context context) {
        super(context);
        setupView(null, 0);
    }

    /**
     * super class constructor with custom layout inflation
     *
     * @param context context
     * @param attrs   attribute set
     */
    public MaterialEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
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
    public MaterialEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        inflate(getContext(), R.layout.view_material_edit_text, this);
        editText = findViewById(R.id.view_material_edit_text);

        if (attrs != null) {
            TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.MaterialEditText, defStyleAttr, 0);

            try {
                String text = a.getString(R.styleable.MaterialEditText_android_text);
                editText.setText(text);

                String error = a.getString(R.styleable.MaterialEditText_error);
                setError(error);

                ColorStateList textColor = a.getColorStateList(R.styleable.MaterialEditText_android_textColor);
                if (textColor != null) {
                    editText.setTextColor(textColor);
                } else {
                    editText.setTextColor(Color.parseColor("#000000"));
                }

                ColorStateList hintColor = a.getColorStateList(R.styleable.MaterialEditText_android_textColorHint);
                if (hintColor != null) {
                    editText.setHintTextColor(hintColor);
                    setHintTextColor(hintColor);
                }

                ColorStateList errorIconColor = a.getColorStateList(R.styleable.MaterialEditText_errorIconColor);
                if (errorIconColor != null) {
                    setErrorIconColor(errorIconColor);
                }

                int inputType = a.getInt(R.styleable.MaterialEditText_android_inputType, 0);
                editText.setInputType(inputType);

                String digits = a.getString(R.styleable.MaterialEditText_android_digits);
                if (digits != null) {
                    setKeyListener(DigitsKeyListener.getInstance(digits));
                }
            } finally {
                a.recycle();
            }
        }
    }

    /**
     * Set key listener in TextInputEditText
     *
     * @param input KeyListener object to set
     */
    public void setKeyListener(KeyListener input) {
        editText.setKeyListener(input);
    }

    /**
     * Set key listener in TextInputEditText
     *
     * @param type input type as defined in android.text.InputType
     */
    public void setInputType(int type) {
        editText.setInputType(type);
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
     * Add text watcher to the TextInputEditText
     *
     * @param watcher watcher to add
     */
    public void addTextChangedListener(TextWatcher watcher) {
        editText.addTextChangedListener(watcher);
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
     * Set position of cursor in text field
     *
     * @param index index to set cursor to
     */
    public void setSelection(int index) {
        editText.setSelection(index);
    }

    /**
     * Get start index of selection in text field
     *
     * @return start index
     */
    public int getSelectionStart() {
        return editText.getSelectionStart();
    }

    /**
     * Get end index of selection in text field
     *
     * @return end index
     */
    public int getSelectionEnd() {
        return editText.getSelectionEnd();
    }

    /**
     * Assigns the error text color
     *
     * @param color colors to assign
     */
    public void setErrorIconColor(ColorStateList color) {
        setErrorIconTintList(color);
    }
}
