package de.danihoo94.www.materialcomponents;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class MaterialSpinnerFilled extends MaterialSpinner {
    /**
     * super class constructor
     *
     * @param context context
     */
    public MaterialSpinnerFilled(@NonNull Context context) {
        super(context);
    }

    /**
     * super class constructor with custom layout inflation
     *
     * @param context context
     * @param attrs   attribute set
     */
    public MaterialSpinnerFilled(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * super class constructor with custom layout inflation
     *
     * @param context      context
     * @param attrs        attribute set
     * @param defStyleAttr style
     */
    public MaterialSpinnerFilled(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Returns the layout id of the layout to use
     *
     * @return layout id
     */
    @Override
    final protected int getLayout() {
        return R.layout.view_material_spinner_filled;
    }
}
