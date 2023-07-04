package string;

public final class MyString implements CharSequence {
    private final char[] charArray;

    public MyString(CharSequence charSequence) {
        charArray = new char[charSequence.length()];
        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = charSequence.charAt(i);
        }
    }

    public MyString(char[] array) {
        charArray = array.clone();
    }

    public char[] toCharArray() {
        return charArray.clone();
    }

    public MyString toLowerCase() {
        char[] temp = new char[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (c >= 65 && c <= 90) {
                temp[i] = (char) (c + 32);
            } else {
                temp[i] = c;
            }
        }
        return new MyString(temp);
    }

    public MyString toUpperCase() {
        char[] temp = new char[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (c >= 97 && c <= 122) {
                temp[i] = (char) (c - 32);
            } else {
                temp[i] = c;
            }
        }
        return new MyString(temp);
    }

    public MyString subString(int start, int end) {
        return new MyString(this.subSequence(start, end));
    }

    public MyString concat(MyString str) {
        int myLen = charArray.length;
        char[] temp = new char[str.length() + myLen];

        for (int i = 0; i < myLen; i++) {
            temp[i] = charArray[i];
        }
        for (int i = myLen; i < temp.length; i++) {
            temp[i] = str.charArray[i - myLen];
        }
        return new MyString(temp);
    }

    
    public int compareTo(MyString str) {
        char[] smaller, bigger;
        if (this.length() > str.length()) {
            bigger = this.charArray;
            smaller = str.charArray;
        } else {
            bigger = str.charArray;
            smaller = this.charArray;
        }
        for (int i = 0; i < smaller.length; i++) {
            if (bigger[i] > smaller[i]) {
                return 1;
            } else if (bigger[i] < smaller[i]) {
                return -1;
            }
        }
        return bigger.length - smaller.length;
    }

    public boolean contains(CharSequence str) {
        for (int i = 0; i < charArray.length; i++) {

            boolean found = true;
            for (int j = 0; j < str.length(); j++) {
                if (charArray[i + j] != str.charAt(j)) {
                    found = false;
                    break;
                }
            }
            if (found) {
                return found;
            }

        }
        return false;
    }
    
    public boolean contains(CharSequence sequence, boolean caseSensitive) {
        if (caseSensitive) {
            return this.contains(sequence);
        }
        MyString ms = new MyString(sequence).toLowerCase();
        for (int i = 0; i < charArray.length; i++) {

            boolean found = true;
            for (int j = 0; j < ms.length(); j++) {
                if (charArray[i + j] != ms.charAt(j)) {
                    found = false;
                    break;
                }
            }
            if (found) {
                return found;
            }

        }
        return false;
    }

    @Override
    public char charAt(int index) {
        if (index < 0 || index >= charArray.length) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bound for lenght " + charArray.length);
        }
        return charArray[index];
    }

    @Override
    public int length() {
        return charArray.length;
    }

    @Override
    public String toString() {
        return new String(charArray);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        if (start < 0 || start >= charArray.length) {
            throw new IndexOutOfBoundsException("Index " + start + " out of bound for lenght " + charArray.length);
        } else if (end < 0 || end > charArray.length) {
            throw new IndexOutOfBoundsException("Index " + end + " out of bound for lenght " + charArray.length);
        } else if (start > end) {
            throw new IllegalArgumentException("Start " + start + " is more then end " + end);
        }
        char[] temp = new char[end - start];
        for (int i = 0; i < end - start; i++) {
            temp[i] = charArray[i + start];
        }
        return new MyString(temp);
    }

}
