# Material Components Library

## Table of Contents
1. [Overview](#overview)
1. [Quick Start](#quick-start)
1. [Documentation](#documentation)
    1. [MaterialEditText](#materialedittext)
        1. [Use in XML](#use-text-field-in-xml)
        1. [Attributes](#text-field-attributes)
        1. [Theming](#text-field-theming)
    1. [MaterialSpinner](#materialspinner-exposed-dropdown-menu)
        1. [Use in XML](#use-spinner-in-xml)
        1. [Attributes](#spinner-attributes)
        1. [Theming](#spinner-theming)
    1. [ExpandableFloatingActionButton](#expandablefloatingactionbutton)
        1. [Use in XML](#use-expandablefloatingactionbutton-in-xml)
        1. [Attributes](#expandablefloatingactionbutton-attributes)
        1. [Theming](#expandablefloatingactionbutton-theming)
    1. [FullscreenDialog](#fullscreendialog)
1. [License](#license)
1. [About](#about)


## Overview

This lightweight library offers you easy access to material components which are not implemented in the standard Android Libraries.

If you are missing some functionality, please submit an issue to give me the chance to improve this library.


As a first step the following components will be available

* <b>MaterialEditText</b> - Outlined and filled Material text fields
* <b>MaterialSpinner</b> - Use a spinner with material design similar to the standard spinner in Android
* <b>ExpandableFloatingActionButton</b> - Expandable fab that uses menu resources to show multiple options  
* (coming soon) <b>FullscreenDialog</b> - A customizable Fullscreen Dialog with toolbar and animations


## Quick Start

Add the following repository in your project's build.gradle file:

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
        ...
    }
}
```

Add the following dependency in your app's build.gradle file:

```
dependencies {
    ...
    implementation 'com.github.danihoo:MaterialComponents:MaterialComponents:[version]'
    ...
}
```

## Documentation

### MaterialEditText

The MaterialEditText is designed following the [Google Material Guidelines for text fields](https://material.io/develop/android/components/text-fields). It uses classes from the Material Library for Android.

<b>Outlined text field</b>

![alt text](https://github.com/danihoo/MaterialComponents/blob/master/screenshots/EditTextOutlined.PNG?raw=true)


<b>Outlined text field (on error)</b>

![alt text](https://github.com/danihoo/MaterialComponents/blob/master/screenshots/EditTextOutlinedError.PNG?raw=true)


<b>Filled text field</b>

![alt text](https://github.com/danihoo/MaterialComponents/blob/master/screenshots/EditTextFilled.PNG?raw=true)


<b>Filled text field (on error)</b>

![alt text](https://github.com/danihoo/MaterialComponents/blob/master/screenshots/EditTextFilledError.png?raw=true)


#### Use text field in XML

To decide whether to use the Filled or Outlined text field you need to define a theme in your styles.xml (see [Theming](#text-field-theming)). In xml you can go like the following example. For an overview of the attributes you can use see [Attributes](#text-field-attributes).

```
<de.danihoo94.www.materialcomponents.MaterialEditText
            style="@style/OutlinedEditTextTheme"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Hint"
            android:text="Text"
            app:counterEnabled="true"
            app:counterMaxLength="20"
            app:errorEnabled="true" />
```


#### Text field attributes

The following list contains special attributes you can use. The MaterialEditText class directly inherits from TextInputLayout in the material library. Therefore [all attributes of this class](https://developer.android.com/reference/com/google/android/material/textfield/TextInputLayout#xml-attributes) can be used, too. Those attributes are not mentioned here.

| Attributes      |  Description                                         |
|-----------------|------------------------------------------------------|
|android:text     | Text shown in the text field                         |
|android:textColor| Text color of the text shown in the text field       |
|android:digits   | Digits that are accepted as input (like in EditText) |
|android:inputType| Input type as used in EditText                       |
|app:error        | Error text to apply to the layout                    |


#### Text field theming

You can define a custom theme. It is required to inherit from either Widget.MaterialComponents.TextInputLayout.OutlinedBox or Widget.MaterialComponents.TextInputLayout.FilledBox to get the corresponding style.

```
<style name="OutlinedEditTextTheme" parent="Widget.MaterialComponents.TextInputLayout.OutlinedBox">
    <item name="android:textColor">@color/...</item>
    <item name="android:textSize">16sp</item>

    <item name="android:textColorHint">@color/...</item>
    <item name="boxStrokeColor">@color/...</item>

    <item name="android:colorControlNormal">@color/.../item>
    <item name="counterTextColor">@color/...</item>

    <item name="helperTextTextColor">@color/...</item>

    <item name="boxStrokeErrorColor">@color/...</item>
    <item name="errorTextColor">@color/...</item>
    <item name="errorIconColor">@color/text_dark_negative</item>
    <item name="counterOverflowTextColor">@color/...</item>
</style>
```


I recommend setting up at least the attributes <i>android:textColorHint</i>, <i>hintTextColor</i> and <i>boxStrokeColor</i> with a color selector, e.g.

```
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:color="@color/text_dark_accent" android:state_enabled="true" />
    <item android:color="@color/theme_primary_light" android:state_focused="true" />
    <item android:color="@color/text_light_disabled" android:state_enabled="false" />
</selector>
```


### MaterialSpinner (Exposed Dropdown Menu)

The MaterialSpinner classes are designed following the [Google Material Guidelines for exposed dropdown menus](https://material.io/components/menus#exposed-dropdown-menu). It uses classes from the Material Library for Android.

<b>Outlined spinner</b>

![alt text](https://github.com/danihoo/MaterialComponents/blob/master/screenshots/SpinnerOutlined.PNG?raw=true)


<b>Outlined spinner (on error)</b>

![alt text](https://github.com/danihoo/MaterialComponents/blob/master/screenshots/SpinnerOutlinedError.PNG?raw=true)


<b>Filled spinner</b>

![alt text](https://github.com/danihoo/MaterialComponents/blob/master/screenshots/SpinnerFilled.PNG?raw=true)


<b>Filled spinner (on error)</b>

![alt text](https://github.com/danihoo/MaterialComponents/blob/master/screenshots/SpinnerFilledError.PNG?raw=true)


#### Use spinner in XML

To decide whether to use the Filled or Outlined text field you need to define a theme in your styles.xml (see [Theming](#spinner-theming)). In xml you can go like the following examples. For an overview of the attributes you can use see [Attributes](#spinner-attributes).

```
    <de.danihoo94.www.materialcomponents.MaterialSpinnerOutlined
        style="@style/MaterialSpinnerTheme"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="..."
        app:errorEnabled="true"/>

    <de.danihoo94.www.materialcomponents.MaterialSpinnerFilled
        style="@style/MaterialSpinnerTheme"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="..."
        app:errorEnabled="true"/>
```


#### Spinner attributes

The following list contains special attributes you can use. The MaterialSpinner classes inherit from RelativeLayout. Therefore [all attributes of this class](https://developer.android.com/reference/android/widget/RelativeLayout) can be used, too. Those attributes are not mentioned here.

| Attributes                  |  Description                                          |
|-----------------------------|-------------------------------------------------------|
|android:text                 | Text shown in the spinner field.                      |
|android:textColor            | Text color of the text shown in the spinner field.    |
|android:backgroundTint       | Background color of the view.                         |
|android:textColorHint        | Hint text color of the hint                           |
|android:colorControlNormal   | Color of the dropdown arrow                           |
|android:hint                 | Hint text shown in the spinner field.                 |
|android:digits               | Digits that are accepted as input (like in EditText). |
|android:inputType            | Input type as used in EditText.                       |
|app:error                    | Error text to apply to the layout.                    |
|app:errorEnabled             | Define if error text is used. This extends the size.  |
|app:helperText               | Helper text to apply to the layout.                   |
|app:helperTextEnabled        | Define if helper text is used. This extends the size. |
|app:hintTextColor            | Color of the hint text (in text field or above).      |
|app:boxStrokeColor           | Color of the layout stroke                            |
|app:counterTextColor         | Color of the counter text                             |
|app:errorTextColor           | Color of the text when an error is set                |
|app:errorIconColor           | Color of the error icon                               |
|app:boxStrokeErrorColor      | Color of the box stroke on error                      |
|app:counterOverflowTextColor | Color of the text if the counter length is exceeded   |


#### Spinner theming

You can define a custom theme. The theme does not need a parent. Anyway, if you also use MaterialEditText you can use the same theme that uses a parent like mentioned above.

```
<style name="MaterialSpinnerTheme">
    <item name="android:textColor">@color/...</item>
    <item name="android:textSize">16sp</item>

    <item name="android:textColorHint">@color/...</item>
    <item name="boxStrokeColor">@color/...</item>

    <item name="android:colorControlNormal">@color/.../item>
    <item name="counterTextColor">@color/...</item>

    <item name="helperTextTextColor">@color/...</item>

    <item name="boxStrokeErrorColor">@color/...</item>
    <item name="errorTextColor">@color/...</item>
    <item name="errorIconColor">@color/text_dark_negative</item>
    <item name="counterOverflowTextColor">@color/...</item>
</style>
```


I recommend setting up at least the attributes <i>android:textColorHint</i>, <i>hintTextColor</i> and <i>boxStrokeColor</i> with a color selector, e.g.

```
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:color="@color/text_dark_accent" android:state_enabled="true" />
    <item android:color="@color/theme_primary_light" android:state_focused="true" />
    <item android:color="@color/text_light_disabled" android:state_enabled="false" />
</selector>
```


### ExpandableFloatingActionButton

The ExpandableFloatingActionButton is an alternative for using a set of several FloatingActionButtons. It uses a menu resource to define the actions, that can be issued.

<b>Example</b>

![alt text](https://github.com/danihoo/MaterialComponents/blob/master/screenshots/ExpandableFab.gif?raw=true)



#### Use ExpandableFloatingActionButton in XML

To decide whether to use the Filled or Outlined text field you need to define a theme in your styles.xml (see [Theming](#text-field-theming)). In xml you can go like the following example. For an overview of the attributes you can use see [Attributes](#text-field-attributes).

```
<de.danihoo94.www.materialcomponents.ExpandableFloatingActionButton
        style="@style/ExpandableFabTheme"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:menu="@menu/..."
        app:mainBackgroundTint="@color/..."
        app:mainIconTint="@color/..."
        app:secondaryBackgroundTint="@color/..."
        app:secondaryIconTint="@color/..."
        app:showLabels="true" />
```


#### ExpandableFloatingActionButton attributes

The following list contains special attributes you can use. The ExpandableFloatingActionButton class directly inherits from LinearLayout. Therefore [all attributes of this class](https://developer.android.com/guide/topics/ui/layout/linear) can be used, too. Those attributes are not mentioned here.

| Attributes                 | Description                                          |
|----------------------------|------------------------------------------------------|
|app:mainBackgroundTint      | Background color of the main fab                     |
|app:mainIconTint            | Icon color of the main fab                           |
|app:secondaryBackgroundTint | Background color of the secondary fab                |
|app:secondaryIconTint       | Icon color of the secondary fab                      |
|app:showLabels              | Set if text labels shall be shown next to menu items |
|app:menu                    | Menu resource to inflate                             |


#### ExpandableFloatingActionButton theming

You can define a custom theme in the case that you use the ExpandableFloatingActionButton multiple times in your app. I recommend setting at least the four tint colors in a theme.
  

### FullscreenDialog

Coming soon...


## License

<a rel="license" href="http://creativecommons.org/licenses/by/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by/4.0/88x31.png" /></a><br /><span xmlns:dct="http://purl.org/dc/terms/" property="dct:title">Material Components Library</span> by <span xmlns:cc="http://creativecommons.org/ns#" property="cc:attributionName">Daniel Hoogen</span> is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by/4.0/">Creative Commons Attribution 4.0 International License</a>.


## About

This library uses the following works of other authors.

* Several Material icons kindly provided by Google Inc.
* The Material Library for Android kindly provided by Google Inc.
