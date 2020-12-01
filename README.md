# Material Components Library

## Table of Contents
1. [Overview](#overview)
1. [Quick Start](#quick-start)
1. [Documentation](#documentation)
    1. [MaterialEditText](#materialedittext)
    1. [MaterialSpinner](#materialspinner-exposed-dropdown-menu)
    1. [FloatingActionButton](#floatingactionbutton-wip)
    1. [FullscreenDialog](#fullscreendialog-wip)
1. [License](#license)
1. [About](#about)


## Overview

This lightweight library offers you easy access to material components which are not implemented in the standard Android Libraries.

If you are missing some functionality, please submit an issue to give me the chance to improve this library.


As a first step the following components will be available

* <b>MaterialEditText</b> - Outlined and filled Material text fields
* (coming soon) <b>MaterialSpinner</b> - Use a spinner and material design similar to the standard spinner in android
* (coming soon) <b>FloatingActionButton</b> - Material fabs with custom colours and design 
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

** Outlined text field **
![alt text](https://github.com/danihoo/MaterialComponents/blob/master/screenshots/EditTextOutlined.PNG?raw=true)

** Outlined text field (on error) **
![alt text](https://github.com/danihoo/MaterialComponents/blob/master/screenshots/EditTextOutlinedError.PNG?raw=true)

** Filled text field **
![alt text](https://github.com/danihoo/MaterialComponents/blob/master/screenshots/EditTextFilled.PNG?raw=true)

** Filled text field (on error) **
![alt text](https://github.com/danihoo/MaterialComponents/blob/master/screenshots/EditTextFilledError.PNG?raw=true)


#### Use in XML

To decide whether to use the Filled or Outlined text field you need to define a theme in your styles.xml (see [Theming](#text-field-theming)). In xml you can go like the following example. For an overview of the attributes you can use see [Attributes](#text-field-attributes).

```
<de.danihoo94.www.materialcomponents.MaterialEditText
            style="@style/OutlinedEditTextTheme"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Hint"
            android:paddingBottom="12dp"
            android:text="Error"
            app:counterEnabled="true"
            app:counterMaxLength="20"
            app:errorEnabled="true" />
```

#### Text field attributes

The following list contains special attributes you can use. The MaterialEditText class directly inherits from TextInputLayout in the material library. Therefore [all attributes of this class](https://developer.android.com/reference/com/google/android/material/textfield/TextInputLayout#xml-attributes) can be used. Those attributes are not mentioned here.

| Attributes      | Methods         | Description |
|-----------------|-----------------|-------------|
|android:text     |||
|android:textColor|||
|android:digits   |||
|android:inputType|||
|app:error        |||
|app:hintTextColor|||
|app:editTextHint |||
|app:editTextError|||


#### Text field theming


### MaterialSpinner (Exposed Dropdown Menu)


#### Attributes

#### Theming

### FloatingActionButton (WIP)


### FullscreenDialog (WIP)


## License

<a rel="license" href="http://creativecommons.org/licenses/by/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by/4.0/88x31.png" /></a><br /><span xmlns:dct="http://purl.org/dc/terms/" property="dct:title">Material Components Library</span> by <span xmlns:cc="http://creativecommons.org/ns#" property="cc:attributionName">Daniel Hoogen</span> is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by/4.0/">Creative Commons Attribution 4.0 International License</a>.


## About

This library uses the following works of other authors.

* Several Material icons kindly provided by Google Inc.
* The Material Library for Android kindly provided by Google Inc.
