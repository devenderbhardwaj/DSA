package string;

public final class MyString implements CharSequence, Comparable<MyString> {
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

    @Override
    public int compareTo(MyString str) {
        for (int i = 0, j = 0; i < this.charArray.length && j < str.charArray.length; i++, j++) {
            if (this.charArray[i] > str.charArray[j]) {
                return 1;
            } else if (this.charArray[i] < str.charArray[j]) {
                return -1;
            }
        }
        return this.length() - str.length();
    }

    public int indexOf(CharSequence str) {
        for (int i = 0; i < charArray.length; i++) {

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
        MyString s = new MyString(sequence).toLowerCase();
        MyString ms = this.toLowerCase();
        for (int i = 0; i < ms.length(); i++) {

            int found = i;
            for (int j = 0; j < s.length(); j++) {
                if (ms.charAt(i+j) != s.charAt(j)) {
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
