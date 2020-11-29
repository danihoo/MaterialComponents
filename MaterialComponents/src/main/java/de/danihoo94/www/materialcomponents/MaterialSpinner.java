package de.danihoo94.www.materialcomponents;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;

abstract class MaterialSpinner extends RelativeLayout {
    private static final int ANIMATION_DURATION_FADE = 200;
    private TextInputLayout layout;
    private MaterialAutoCompleteTextView spinner;
    private ImageView dropIcon;
    @SuppressWarnings("rawtypes")
    private MaterialSpinnerAdapter adapter;

    /**
     * super class constructor
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

        inflate(getContext(), getLayout(), this);
        spinner = findViewById(R.id.view_material_spinner_autocomplete);
        layout = findViewById(R.id.view_material_spinner_layout);
        dropIcon = findViewById(R.id.view_material_spinner_icon);
        View anchor = findViewById(R.id.view_material_dropdown_anchor);

        spinner.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showDropDown();
            }
        });

        spinner.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showDropDown();
                } else {
                    dismissDropDown();
                }
            }
        });

        spinner.setOnDismissListener(new AutoCompleteTextView.OnDismissListener() {
            @Override
            public void onDismiss() {
                animateDropDown(false);
            }
        });

        // setup anchor with unique view
        int id = View.generateViewId();
        anchor.setId(id);
        spinner.setDropDownAnchor(id);

        if (attrs != null) {
            TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.MaterialSpinner, defStyleAttr, 0);

            try {
                String hint = a.getString(R.styleable.MaterialSpinner_android_hint);
                setHint(hint);

                String error = a.getString(R.styleable.MaterialSpinner_error);
                setError(error);

                boolean errorEnabled = a.getBoolean(R.styleable.MaterialSpinner_errorEnabled, false);
                setErrorEnabled(errorEnabled);

                String helperText = a.getString(R.styleable.MaterialSpinner_helperText);
                setHelperText(helperText);

                boolean helperTextEnabled = a.getBoolean(R.styleable.MaterialSpinner_helperTextEnabled, false);
                setHelperTextEnabled(helperTextEnabled);

                ColorStateList textColor = a.getColorStateList(R.styleable.MaterialSpinner_android_textColor);
                if (textColor != null) {
                    spinner.setTextColor(textColor);
                } else {
                    spinner.setTextColor(ContextCompat.getColor(getContext(), android.R.color.black));
                }

                ColorStateList backgroundTint = a.getColorStateList(R.styleable.MaterialSpinner_android_backgroundTint);
                if (backgroundTint != null) {
                    setBackgroundTintList(backgroundTint);
                } else {
                    setBackgroundTintList(ContextCompat.getColorStateList(getContext(), android.R.color.transparent));
                }

                ColorStateList textColorHint = a.getColorStateList(R.styleable.MaterialSpinner_android_textColorHint);
                if (textColorHint != null) {
                    setHintTextColor(textColorHint);
                }

                ColorStateList boxStrokeColor = a.getColorStateList(R.styleable.MaterialSpinner_boxStrokeColor);
                if (boxStrokeColor != null) {
                    setBoxStrokeColor(boxStrokeColor);
                }

                ColorStateList colorControlNormal = a.getColorStateList(R.styleable.MaterialSpinner_android_colorControlNormal);
                if (colorControlNormal != null) {
                    setColorControlNormal(colorControlNormal);
                }

                ColorStateList counterTextColor = a.getColorStateList(R.styleable.MaterialSpinner_counterTextColor);
                if (counterTextColor != null) {
                    setCounterTextColor(counterTextColor);
                }

                ColorStateList helperTextTextColor = a.getColorStateList(R.styleable.MaterialSpinner_helperTextTextColor);
                if (helperTextTextColor != null) {
                    setHelperTextColor(helperTextTextColor);
                }

                ColorStateList boxStrokeErrorColor = a.getColorStateList(R.styleable.MaterialSpinner_boxStrokeErrorColor);
                if (boxStrokeErrorColor != null) {
                    setBoxStrokeErrorColor(boxStrokeErrorColor);
                }

                ColorStateList errorTextColor = a.getColorStateList(R.styleable.MaterialSpinner_errorTextColor);
                if (errorTextColor != null) {
                    setErrorTextColor(errorTextColor);
                }

                ColorStateList errorIconColor = a.getColorStateList(R.styleable.MaterialSpinner_errorIconColor);
                if (errorIconColor != null) {
                    setErrorIconColor(errorIconColor);
                }

                ColorStateList counterOverflowTextColor = a.getColorStateList(R.styleable.MaterialSpinner_counterOverflowTextColor);
                if (counterOverflowTextColor != null) {
                    setCounterOverflowTextColor(counterOverflowTextColor);
                }

            } finally {
                a.recycle();
            }
        }
    }

    /**
     * Animate the drop down icon rotation
     *
     * @param openAfterAnimation true if animate to open state, otherwise false
     */
    private void animateDropDown(boolean openAfterAnimation) {
        if (getError() == null) {
            float rotationStart = openAfterAnimation ? 0f : 180f;
            float rotationEnd = openAfterAnimation ? 180f : 0f;

            RotateAnimation rotate = new RotateAnimation(rotationStart, rotationEnd,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            rotate.setInterpolator(new AccelerateDecelerateInterpolator());
            rotate.setDuration(2 * ANIMATION_DURATION_FADE);
            rotate.setFillAfter(true);
            dropIcon.startAnimation(rotate);
        }
    }

    /**
     * Returns the layout id of the layout to use
     *
     * @return layout id
     */
    protected abstract int getLayout();

    /**
     * (De-)activate the error text below the TextInputLayout
     *
     * @param enabled true if enabled
     */
    public void setErrorEnabled(boolean enabled) {
        layout.setErrorEnabled(enabled);
    }

    /**
     * (De-)activate the helper text below the TextInputLayout
     *
     * @param enabled true if enabled
     */
    public void setHelperTextEnabled(boolean enabled) {
        layout.setHelperTextEnabled(enabled);
    }

    /**
     * Assigns the hint text color of the layout hint
     *
     * @param color colors to assign
     */
    public void setHintTextColor(ColorStateList color) {
        layout.setDefaultHintTextColor(color);
    }

    /**
     * Returns the currently selected object of the adapter
     *
     * @return object of type t or null, if none is selected
     */
    public Object getSelectedObject() {
        return adapter.getSelectedObject();
    }

    /**
     * Assigns the box stroke color
     *
     * @param color colors to assign
     */
    public void setBoxStrokeColor(ColorStateList color) {
        layout.setBoxStrokeColorStateList(color);
    }

    /**
     * Assigns the control color
     *
     * @param color colors to assign
     */
    public void setColorControlNormal(ColorStateList color) {
        dropIcon.setImageTintList(color);
    }

    /**
     * Assigns the counter text color
     *
     * @param color colors to assign
     */
    public void setCounterTextColor(ColorStateList color) {
        layout.setCounterTextColor(color);
    }

    /**
     * Assigns the helper text color
     *
     * @param color colors to assign
     */
    public void setHelperTextColor(ColorStateList color) {
        layout.setHelperTextColor(color);
    }

    /**
     * Assigns the box stroke color on error
     *
     * @param color colors to assign
     */
    public void setBoxStrokeErrorColor(ColorStateList color) {
        layout.setBoxStrokeErrorColor(color);
    }

    /**
     * Assigns the error text color
     *
     * @param color colors to assign
     */
    public void setErrorTextColor(ColorStateList color) {
        layout.setErrorTextColor(color);
    }

    /**
     * Assigns the error text color
     *
     * @param color colors to assign
     */
    public void setErrorIconColor(ColorStateList color) {
        layout.setErrorIconTintList(color);
    }

    /**
     * Assigns the counter overflow text color
     *
     * @param color colors to assign
     */
    public void setCounterOverflowTextColor(ColorStateList color) {
        layout.setCounterOverflowTextColor(color);
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
        animateDropDown(true);
        spinner.showDropDown();
    }

    /**
     * Dismiss dropdown menu
     */
    public void dismissDropDown() {
        spinner.dismissDropDown();
    }

    /**
     * Return if popup is currently showing
     *
     * @return is dropdown showing
     */
    public boolean isDropDownShowing() {
        return spinner.isPopupShowing();
    }

    /**
     * Get the currently set error message
     *
     * @return error char sequence
     */
    public CharSequence getError() {
        return layout.getError();
    }

    /**
     * Set an error message at the TextInputLayout
     *
     * @param errorText error text to be assigned, null to clear
     */
    public void setError(@Nullable CharSequence errorText) {
        if (errorText == null) {
            dropIcon.setVisibility(View.VISIBLE);
        } else {
            dropIcon.clearAnimation(); // to prevent a running animation making the icon visible
            dropIcon.setVisibility(View.GONE);
        }
        layout.setError(errorText);
    }

    /**
     * Set a helper text at the TextInputLayout
     *
     * @param errorText error text to be assigned, null to clear
     */
    public void setHelperText(@Nullable CharSequence errorText) {
        layout.setHelperText(errorText);
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
    public CharSequence getSpinnerHint() {
        return spinner.getHint();
    }

    /**
     * Get current hint text from TextInputLayout
     *
     * @return hint as text
     */
    public CharSequence getHint() {
        return layout.getHint();
    }

    /**
     * Set a hint message at the TextInputLayout
     *
     * @param hintText hint text to be assigned, null to clear
     */
    public void setHint(@Nullable CharSequence hintText) {
        layout.setHint(hintText);
    }

    /**
     * Sets the adapter that stores the objects that should be displayed in the dropdown menu
     *
     * @param adapter The adapter to set
     */
    @SuppressWarnings("rawtypes")
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

    /**
     * Select the item with the specified index
     *
     * @param index index of the item to select
     */
    public void setSelection(int index) {
        spinner.setSelection(index);
    }

    /**
     * Select the specified object
     *
     * @param o object to select
     * @return true if object was found and selected
     */
    public boolean setSelection(Object o) {
        for (int i = 0; i < adapter.getCount(); i++) {
            if (adapter.getItem(i) == o) {
                spinner.setSelection(i);
                return true;
            }
        }
        return false;
    }
}
