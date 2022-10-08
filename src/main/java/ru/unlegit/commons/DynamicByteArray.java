package ru.unlegit.commons;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class DynamicByteArray {

    @Getter
    private byte[] array;

    public DynamicByteArray() {}

    public void expand(int expander) {
        if (array == null) {
            array = new byte[expander];
        } else {
            int oldLength = array.length;
            System.arraycopy(array, 0, array = new byte[oldLength + expander], 0, oldLength);
        }
    }

    public void set(int index, byte value) {
        array[index] = value;
    }

    public byte get(int index) {
        return array[index];
    }

    public int length() {
        return array.length;
    }

    public boolean isEmpty() {
        return array.length == 0;
    }
}