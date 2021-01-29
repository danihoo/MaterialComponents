package de.danihoo94.www.materialcomponents;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;

import androidx.annotation.MenuRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * ExpandableFloatingActionButton - A FloatingActionButton that can be expanded to show several
 * options given by an inflated Menu resource
 */
@SuppressWarnings({"unused", "RedundantSuppression"})
public class ExpandableFloatingActionButton extends RelativeLayout implements View.OnClickListener {

    private static final int INFLATE_ANIM_DURATION = 300;
    private static final int CONTAINER_PADDING_BOTTOM_DP = 74;

    private FloatingActionButton main;
    private ViewGroup itemContainer;
    private MenuItem.OnMenuItemClickListener listener;

    private ColorStateList secondaryIconTint;
    private ColorStateList secondaryBackgroundTint;

    private boolean showLabels;
    private boolean expanded;
    private PopupMenu menu;

    public ExpandableFloatingActionButton(@NonNull Context context) {
        super(context);
        setupView(null, 0);
    }

    public ExpandableFloatingActionButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setupView(attrs, 0);
    }

    public ExpandableFloatingActionButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupView(attrs, defStyleAttr);
    }

    /**
     * Setup the view with the given attribute set and style
     *
     * @param attrs        attributes to apply
     * @param defStyleAttr style to apply
     */
    private void setupView(AttributeSet attrs, int defStyleAttr) {
        View v = inflate(getContext(), R.layout.expandable_fab, this);
        main = v.findViewById(R.id.expandable_fab_main);
        itemContainer = v.findViewById(R.id.expandable_fab_item_container);

        if (attrs != null) {
            TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.ExpandableFloatingActionButton, defStyleAttr, 0);

            try {
                ColorStateList mainIconTint = a.getColorStateList(R.styleable.ExpandableFloatingActionButton_mainIconTint);
                setMainIconTint(mainIconTint);

                ColorStateList secondaryIconTint = a.getColorStateList(R.styleable.ExpandableFloatingActionButton_secondaryIconTint);
                setSecondaryIconTint(secondaryIconTint);

                ColorStateList mainBackgroundTint = a.getColorStateList(R.styleable.ExpandableFloatingActionButton_mainBackgroundTint);
                setMainBackgroundTint(mainBackgroundTint);

                ColorStateList secondaryBackgroundTint = a.getColorStateList(R.styleable.ExpandableFloatingActionButton_secondaryBackgroundTint);
                setSecondaryBackgroundTint(secondaryBackgroundTint);

                showLabels = a.getBoolean(R.styleable.ExpandableFloatingActionButton_showLabels, false);

                @MenuRes int menuRes = a.getResourceId(R.styleable.ExpandableFloatingActionButton_menu, 0);
                if (menuRes != 0) {
                    inflateMenu(menuRes);
                }
            } finally {
                a.recycle();
            }
        }
    }

    /**
     * Set icon tint of the main fab
     *
     * @param tint ColorStateList to apply
     */
    public void setMainIconTint(@Nullable ColorStateList tint) {
        main.setImageTintList(tint);
    }

    /**
     * Set background tint of the main fab
     *
     * @param tint ColorStateList to apply
     */
    public void setMainBackgroundTint(@Nullable ColorStateList tint) {
        main.setBackgroundTintList(tint);
    }

    /**
     * Set icon tint of the secondary fabs
     *
     * @param tint ColorStateList to apply
     */
    public void setSecondaryIconTint(@Nullable ColorStateList tint) {
        this.secondaryIconTint = tint;

        for (int i = 0; i < itemContainer.getChildCount(); i++) {
            View view = itemContainer.getChildAt(i);
            if (view instanceof MenuItemView) {
                ((MenuItemView) view).fab.setImageTintList(tint);
            }
        }
    }

    /**
     * Set background tint of the secondary fabs
     *
     * @param tint ColorStateList to apply
     */
    public void setSecondaryBackgroundTint(@Nullable ColorStateList tint) {
        this.secondaryBackgroundTint = tint;

        for (int i = 0; i < itemContainer.getChildCount(); i++) {
            View view = itemContainer.getChildAt(i);
            if (view instanceof MenuItemView) {
                ((MenuItemView) view).fab.setBackgroundTintList(tint);
            }
        }
    }

    /**
     * Expand the fab or collapse it, if it is already open
     *
     * @param listener actions to perform after animation has finished
     */
    public void toggle(OnAnimationEndListener listener) {
        if (expanded) {
            collapse(listener);
        } else {
            expand(listener);
        }
    }

    /**
     * Perform shrink animation to disappear
     *
     * @param listener actions to perform after animation has finished
     */
    public void animateIn(@Nullable final OnAnimationEndListener listener) {
        Animation in = AnimationUtils.loadAnimation(getContext(), R.anim.fab_in);
        setVisibility(View.VISIBLE);
        startAnimation(in);
    }

    /**
     * Perform grow animation to appear
     *
     * @param listener actions to perform after animation has finished
     */
    public void animateOut(@Nullable final OnAnimationEndListener listener) {
        if (expanded) {
            collapse(new OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(Animation animation) {
                    performOutAnimation(listener);
                }
            });
        } else {
            performOutAnimation(listener);
        }
    }

    /**
     * Inflate a menu resource
     *
     * @param menuRes menu resource to inflate
     */
    public void inflateMenu(int menuRes) {
        menu = new PopupMenu(getContext(), null);
        MenuInflater inflater = menu.getMenuInflater();
        inflater.inflate(menuRes, menu.getMenu());

        wipeMenuItems();

        if (menu.getMenu().size() > 1) {

            main.setOnClickListener(this);

            Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.round_add_24);
            main.setImageDrawable(drawable);
            main.setContentDescription(getContext().getString(R.string.expand));

            for (int i = 0; i < menu.getMenu().size(); i++) {
                MenuItemView fab = createMenuView(menu.getMenu().getItem(i));
                itemContainer.addView(fab);
                fab.setGravity(Gravity.END);
            }

            View space = new Space(getContext());
            space.setMinimumHeight((int) (CONTAINER_PADDING_BOTTOM_DP * getResources().getDisplayMetrics().density));
            itemContainer.addView(space);
        } else {
            final MenuItem item = menu.getMenu().getItem(0);
            main.setId(item.getItemId());

            main.setImageDrawable(item.getIcon());
            main.setContentDescription(item.getTitle());

            if (listener != null) {
                main.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onMenuItemClick(item);
                    }
                });
            }
        }
    }

    /**
     * Create a sub view for a single menu item
     *
     * @param item menu item to create a view for
     * @return the created view
     */
    private MenuItemView createMenuView(final MenuItem item) {
        MenuItemView view = new MenuItemView(getContext());
        view.setVisibility(GONE);
        view.setId(item.getItemId());

        if (showLabels) {
            view.text.setText(item.getTitle());
        }

        view.fab.setBackgroundTintList(secondaryBackgroundTint);
        view.fab.setImageDrawable(item.getIcon());
        view.fab.setImageTintList(secondaryIconTint);
        view.fab.setContentDescription(item.getTitle());

        if (listener != null) {
            view.fab.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onMenuItemClick(item);
                }
            });
        }

        return view;
    }

    /**
     * Expand the view
     *
     * @param listener actions to perform after animation has finished
     */
    private void expand(final OnAnimationEndListener listener) {

        if (menu.getMenu().size() > 1) {

            Animation rotate = AnimationUtils.loadAnimation(getContext(), R.anim.fab_rotate_expand);
            main.startAnimation(rotate);

            final int childCount = itemContainer.getChildCount();

            for (int i = 0; i < childCount; i++) {
                if (itemContainer.getChildAt(i) instanceof MenuItemView) {
                    final MenuItemView child = (MenuItemView) itemContainer.getChildAt(i);
                    child.setVisibility(VISIBLE);
                    child.card.setVisibility(INVISIBLE);

                    TranslateAnimation containerAnim = createTranslateAnimation(childCount - i - 1, true);

                    final int finalI = i;
                    containerAnim.setAnimationListener(new OnAnimationEndListener() {
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            AlphaAnimation cardAnim = new AlphaAnimation(0f, 1f);
                            cardAnim.setDuration(INFLATE_ANIM_DURATION / 3);
                            cardAnim.setFillAfter(true);
                            child.card.startAnimation(cardAnim);

                            Animation shake = AnimationUtils.loadAnimation(getContext(), R.anim.fab_shake);
                            shake.setDuration(INFLATE_ANIM_DURATION * 2 / 3);
                            child.fab.startAnimation(shake);

                            if (finalI == childCount - 2 && listener != null) {
                                listener.onAnimationEnd(animation);
                            }
                        }
                    });

                    child.startAnimation(containerAnim);
                }
            }

            expanded = true;
        }
    }

    /**
     * Collapse the view
     *
     * @param listener actions to perform after animation has finished
     */
    private void collapse(@Nullable final OnAnimationEndListener listener) {

        if (menu.getMenu().size() > 1) {

            Animation rotate = AnimationUtils.loadAnimation(getContext(), R.anim.fab_rotate_collapse);
            main.startAnimation(rotate);

            final int childCount = itemContainer.getChildCount();

            for (int i = 0; i < childCount; i++) {
                if (itemContainer.getChildAt(i) instanceof MenuItemView) {
                    final MenuItemView child = (MenuItemView) itemContainer.getChildAt(i);

                    AlphaAnimation cardAnim = new AlphaAnimation(1f, 0f);
                    cardAnim.setDuration(INFLATE_ANIM_DURATION / 3);
                    cardAnim.setFillAfter(true);

                    final int targetY = childCount - i - 1;
                    final int finalI = i;
                    cardAnim.setAnimationListener(new OnAnimationEndListener() {
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            TranslateAnimation containerAnim = createTranslateAnimation(targetY, false);
                            containerAnim.setAnimationListener(new OnAnimationEndListener() {
                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    child.setVisibility(GONE);

                                    //last animation triggers the given listener
                                    if (finalI == (childCount - 2) && listener != null) {
                                        listener.onAnimationEnd(animation);
                                    }
                                }
                            });
                            child.startAnimation(containerAnim);
                        }
                    });

                    child.card.startAnimation(cardAnim);
                }
            }

            expanded = false;
        }
    }

    private TranslateAnimation createTranslateAnimation(int targetY, boolean in) {
        TranslateAnimation containerAnim = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF,
                0f,
                Animation.RELATIVE_TO_SELF,
                0f,
                Animation.RELATIVE_TO_SELF,
                in ? targetY : 0f,
                Animation.RELATIVE_TO_SELF,
                in ? 0f : targetY);

        containerAnim.setDuration(INFLATE_ANIM_DURATION * 2 / 3);
        containerAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        containerAnim.setFillAfter(true);

        return containerAnim;
    }

    /**
     * Shake the view. This can be used to indicate, that the inflated menu / the possible actions
     * have changed
     *
     * @param listener actions to perform after animation has finished
     */
    public void shake(@Nullable final OnAnimationEndListener listener) {
        if (expanded) {
            collapse(new OnAnimationEndListener() {
                @Override
                public void onAnimationEnd(Animation animation) {
                    performShakeAnimation(listener);
                }
            });
        } else {
            performShakeAnimation(listener);
        }
    }

    /**
     * Perform a shake animation
     *
     * @param listener actions to perform after animation has finished
     */
    private void performShakeAnimation(@Nullable OnAnimationEndListener listener) {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.fab_shake);
        anim.setAnimationListener(listener);
        main.startAnimation(anim);
    }

    /**
     * Expand the view
     *
     * @param listener actions to perform after animation has finished
     */
    private void performOutAnimation(@Nullable OnAnimationEndListener listener) {
        Animation out = AnimationUtils.loadAnimation(getContext(), R.anim.fab_out);
        out.setAnimationListener(new ExpandableFloatingActionButton.OnAnimationEndListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                setVisibility(View.GONE);
            }
        });
        startAnimation(out);
    }

    /**
     * Clear item container view
     */
    private void wipeMenuItems() {
        itemContainer.removeAllViews();
    }

    /**
     * Toggle the ExpandableFloatingActionButton when the main fab is clicked
     *
     * @param v the view that was clicked
     */
    @Override
    public void onClick(View v) {
        if (v == main) {
            toggle(null);
        }
    }

    /**
     * Apply a MenuItem.OnMenuItemClickListener to all secondary fabs. You can read the action ID
     * from the MenuItem to decide, what shall happen
     *
     * @param listener to apply to all secondary fabs
     */
    public void setMenuListener(final MenuItem.OnMenuItemClickListener listener) {
        this.listener = listener;

        for (int i = 0; i < itemContainer.getChildCount(); i++) {
            final View view = itemContainer.getChildAt(i);
            if (view instanceof MenuItemView) {
                ((MenuItemView) view).fab.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            for (int i = 0; i < menu.getMenu().size(); i++) {
                                MenuItem item = menu.getMenu().getItem(i);
                                if (item.getItemId() == view.getId()) {
                                    listener.onMenuItemClick(item);
                                    break;
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    /**
     * Set the view to show labels next to the menu items
     *
     * @param showLabels true, if labels shall be shown
     */
    public void setShowLabels(boolean showLabels) {
        this.showLabels = showLabels;
    }

    /**
     * @return true, if the view is expanded
     */
    public boolean isExpanded() {
        return expanded;
    }

    /**
     * Simplified Animation.AnimationListener
     */
    public static abstract class OnAnimationEndListener implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    /**
     * View representing a single menu item
     */
    private static class MenuItemView extends LinearLayout {

        private FloatingActionButton fab;
        private CardView card;
        private TextView text;

        public MenuItemView(Context context) {
            super(context);
            setupView(null, 0);
        }

        public MenuItemView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
            setupView(attrs, 0);
        }

        public MenuItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            setupView(attrs, defStyleAttr);
        }

        /**
         * Setup the view with the given attribute set and style
         *
         * @param attrs        attributes to apply
         * @param defStyleAttr style to apply
         */
        private void setupView(AttributeSet attrs, int defStyleAttr) {
            View v = inflate(getContext(), R.layout.expandable_fab_item, this);

            fab = v.findViewById(R.id.expandable_fab_item_button);
            card = v.findViewById(R.id.expandable_fab_item_card);
            text = v.findViewById(R.id.expandable_fab_item_text);
        }
    }
}
