package de.danihoo94.www.spinnertest;

public class Item {
    private final String string;

    Item(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public String toString() {
        return string;
    }
}
