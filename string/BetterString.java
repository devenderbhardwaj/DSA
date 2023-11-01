package string;

import java.util.Arrays;

/**
 * <p>It is implementation of String data structure </p> </br>
 * <p>It is named BetterString because it is better then another class MyString in this package
 * which is also a implementation of string data structure. </p>
 * 
 * How is it better?
 * <p>It is better because it saves memory while creating substrings or subsequence
 * it does not create new char[] array and reuse array of BetterString object from
 * which we creating substring </p>
 * <p>Is is better then java.lang.String ? </p>
 * <p>No I don't think so; java.lang.String have richer interface and better support at
 * language level </p>
 * 
*/
public final class BetterString implements CharSequence, Comparable<BetterString>{
    private int offset;
    private int length;
    private final char[] charArray;

    public BetterString(CharSequence charSequence) {
        charArray = new char[charSequence.length()];
        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = charSequence.charAt(i);
        }
        this.offset = 0;
        this.length = charSequence.length();
    }

    public BetterString(char[] array) {
        charArray = Arrays.copyOf(array, array.length);
        this.offset = 0;
        this.length = array.length;
    }

    private BetterString(char[] array, int offset, int length) {
        this.charArray = array;
        this.offset = offset;
        this.length = length;
    }

    public char[] toCharArray() {
        return Arrays.copyOfRange(charArray, this.offset, this.offset + this.length);
    }

    public BetterString toLowerCase() {
        char[] temp = new char[length];
        for (int i = 0; i < length; i++) {
            char c = charArray[i + offset];
            if (c >= 65 && c <= 90) {
                temp[i] = (char) (c + 32);
            } else {
                temp[i] = c;
            }
        }
        return new BetterString(temp);
    }

    public BetterString toUpperCase() {
        char[] temp = new char[length];
        for (int i = 0; i < length; i++) {
            char c = charArray[i + offset];
            if (c >= 97 && c <= 122) {
                temp[i] = (char) (c - 32);
            } else {
                temp[i] = c;
            }
        }
        return new BetterString(temp);
    }

    public BetterString subString(int start, int end) {
        if (start < 0 || start >= this.length) {
            throw new IndexOutOfBoundsException("Index " + start + " out of bound for lenght " + charArray.length);
        } else if (end < 0 || end > this.length) {
            throw new IndexOutOfBoundsException("Index " + end + " out of bound for lenght " + charArray.length);
        } else if (start > end) {
            throw new IllegalArgumentException("Start " + start + " is more then end " + end);
        }
        return new BetterString(this.charArray, offset + start, end - start);
    }

    public BetterString concat(BetterString str) {
        int myLen = this.length;
        char[] temp = new char[str.length() + myLen];

        for (int i = offset, j = 0; j < myLen; i++, j++) {
            temp[j] = charArray[i];
        }
        for (int i = myLen; i < temp.length; i++) {
            temp[i] = str.charArray[i - myLen];
        }
        return new BetterString(temp);
    }
    
    public BetterString concat(CharSequence sequence) {
        int myLen = this.length;
        char[] temp = new char[sequence.length() + myLen];

        for (int i = offset, j = 0; j < myLen; i++, j++) {
            temp[j] = charArray[i];
        }
        for (int i = myLen; i < temp.length; i++) {
            temp[i] = sequence.charAt(i-myLen);
        }

        return new BetterString(temp);
    }
    
    @Override
    public int compareTo(BetterString str) {
        for (int i = this.offset, j = 0; i < this.length + this.offset && j < str.length + str.offset; i++, j++) {
            if (this.charArray[i] > str.charArray[j]) {
                return 1;
            } else if (this.charArray[i] < str.charArray[j]) {
                return -1;
            }
        }      
        return this.length - str.length;
    }

    public int indexOf(CharSequence str) {
        for (int i = offset; i < offset + length; i++) {

            int found = i;
            for (int j = 0; j < str.length(); j++) {
                if (charArray[i + j] != str.charAt(j)) {
                    found = -1;
                    break;
                }
            }
            if (found != -1) {
                return found;
            }

        }
        return -1;
    }
    
    public int indexOf(CharSequence sequence, boolean caseSensitive) {
        if (caseSensitive) {
            return this.indexOf(sequence);
        }
        BetterString ms = new BetterString(sequence).toLowerCase();
        BetterString ns = new BetterString(this).toLowerCase();
        for (int i = offset; i < offset + length; i++) {

            int found = i;
            for (int j = 0; j < ms.length; j++) {
                if (ns.charArray[i + j] != ms.charArray[j]) {
                    found = -1;
                    break;
                }
            }
            if (found != -1) {
                return found;
            }

        }
        return -1;
    }

    @Override
    public char charAt(int index) {
        if (index < 0 || index >= offset + length) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bound for length " + length);
        }
        return charArray[offset + index];
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public String toString() {
        return new String(Arrays.copyOfRange(this.charArray, this.offset, this.offset + this.length));
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return this.subString(start, end);
    }

}
