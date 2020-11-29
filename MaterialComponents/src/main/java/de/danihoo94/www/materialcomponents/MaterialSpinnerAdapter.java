package de.danihoo94.www.materialcomponents;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

public class MaterialSpinnerAdapter<T> extends ArrayAdapter<T> {

    private T selectedObject;
    private AutoCompleteTextView spinner;
    private int selectedPosition;

    /**
     * Super constructor
     *
     * @param activity activity
     */
    public MaterialSpinnerAdapter(@NonNull FragmentActivity activity) {
        super(activity, R.layout.list_item_material_spinner);
    }

    /**
     * Super constructor with custom item layout
     *
     * @param activity activity
     * @param resource custom layout id
     */
    public MaterialSpinnerAdapter(@NonNull FragmentActivity activity, int resource) {
        super(activity, resource);
    }

    /**
     * Overridden remove function that ensures, that the the selection attribute stays
     *
     * @param object object to remove
     */
    @Override
    public void remove(@Nullable T object) {
        // if the removed item is before selection, the selection value needs to be corrected
        if (object == selectedObject) {
            selectedObject = null;
            selectedPosition = -1;
        }

        super.remove(object);
    }

    /**
     * Override super method to color the selected item
     *
     * @param position    item position
     * @param convertView convert view
     * @param parent      parent view
     * @return dropdown view
     */
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = super.getDropDownView(position, null, parent);

        if (position == selectedPosition) {
            v.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.list_item_background_selected));
        } else {
            v.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.list_item_background_standard));
        }
        return v;
    }

    /**
     * Return spinner object
     *
     * @return spinner
     */
    public AutoCompleteTextView getSpinner() {
        return spinner;
    }

    /**
     * Save spinner object and attach to its listener
     *
     * @param spinner spinner to save and attach to
     */
    public void setSpinner(final AutoCompleteTextView spinner) {
        this.spinner = spinner;
        attachToOnItemClickListener();
    }

    /**
     * Returns the currently selected object
     *
     * @return object of type t or null, if none is selected
     */
    T getSelectedObject() {
        return selectedObject;
    }

    /**
     * Extend the existing listener on item clicks, to catch the currently selected item
     */
    void attachToOnItemClickListener() {
        final AdapterView.OnItemClickListener listener = spinner.getOnItemClickListener();
        this.spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedObject = getItem(position);
                selectedPosition = position;

                if (listener != null) {
                    listener.onItemClick(parent, view, position, id);
                }
            }
        });
    }
}