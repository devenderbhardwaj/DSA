package string;


public class StringBuilder implements CharSequence {
    private char[] charArray;
    private int length = 0;
    private final int DEFAULT_INITIAL_CAPACITY = 20;
    private final byte CAPACITY_INCREASE_FACTOR = 2;

    public StringBuilder() {
        charArray = new char[DEFAULT_INITIAL_CAPACITY];
    }

    public StringBuilder(char[] characterArray) {
        charArray = new char[characterArray.length];
        for (int i = 0; i < characterArray.length; i++) {
            charArray[i] = characterArray[i];
        }
        this.length = charArray.length;
    }

    public StringBuilder(CharSequence sequence) {
        charArray = new char[sequence.length()];
        for (int i = 0; i < sequence.length(); i++) {
            charArray[i] = sequence.charAt(i);
        }
        this.length = sequence.length();
    }

    public void append( CharSequence sequence) {       
        ensureCapacity(this.length + sequence.length());
        for (int i = this.length, j = 0; j < sequence.length(); i++, j++) {
            charArray[i] = sequence.charAt(j);
        }
        this.length += sequence.length();
    } 

    public void append(char[] characterArray) {
        ensureCapacity(this.length + characterArray.length);
        for (int i = this.length, j = 0; j < characterArray.length; i++, j++) {
            charArray[i] = characterArray[j];
        }
        this.length += characterArray.length;

    }
    public int getCapacity() {
        return this.charArray.length;
    }
    public void ensureCapacity(int capacity) {
        if (this.charArray.length >= capacity) {
            return ;
        }
        int newCapacity = this.charArray.length * CAPACITY_INCREASE_FACTOR;
        while (newCapacity < capacity) {
            newCapacity *= CAPACITY_INCREASE_FACTOR;
        }
        this.setCapacity(newCapacity);

    }
    private void setCapacity(int capacity) {
        if (capacity < this.length) {
            throw new IllegalArgumentException("Cannot decrese capacity less the current length of array");
        }
        char[] newArray = new char[capacity];

        for (int i = 0; i < this.length; ++i) {
            newArray[i] = charArray[i];
        }

        this.charArray = newArray;
    }

    public void appendChar(char value) {
        if (this.length == this.charArray.length) {
            this.increaseCapacity();
        }
        this.charArray[this.length] = value;
        ++this.length;
    }

    public void setCharAt(int index, char value) {
        if (index >= this.length || index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bound for length " + this.length);
        }
        this.charArray[index] = value;
    }

    public int getChar(int index) {
        if (index >= this.length || index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bound for length " + this.length);
        }
        return this.charArray[index];
    }

    public void insertCharAt(int index, char value) throws Exception {
        if (index > this.length || index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bound for length " + this.length);
        }
        if (this.length == this.charArray.length) {
            this.increaseCapacity();
        }
        char bubble = charArray[index];
        charArray[index] = value;
        for (int i = index + 1; i <= this.length; ++i) {
            char temp = charArray[i];
            charArray[i] = bubble;
            bubble = temp;
        }
        ++this.length;
    }

    public void insert(int index, CharSequence sequence) {
        if (index > this.length || index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bound for length " + this.length);
        }
        int seqLen = sequence.length();
        ensureCapacity(this.length + sequence.length());

        //Shifting existing charaters
        for (int i = this.length  - 1; i >= index; i--) {
            charArray[i+seqLen] = charArray[i];
        }
        for (int i = index, j = 0; i < index + seqLen; i++, j++) {
            charArray[i] = sequence.charAt(j);
        }
        this.length += seqLen;
    }
    public void deleteCharAt(int index)  {
        if (index >= this.length || index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bound for length " + this.length);
        }
        if (this.length != 0) {
            for (int i = index; i < this.length - 1; ++i) {
                charArray[i] = charArray[i + 1];
            }
            --this.length;
        }
        if (this.charArray.length > this.length * CAPACITY_INCREASE_FACTOR) {
            this.capacitySetToFit();
        }
    }

    public void delete(int start, int end) {
        if (start < 0 || start >= this.length) {
            throw new IndexOutOfBoundsException("Index " + start + " out of bound for lenght " + this.length);
        } else if (end < 0 || end > this.length) {
            throw new IndexOutOfBoundsException("Index " + end + " out of bound for lenght " + this.length);
        } else if (start > end) {
            throw new IllegalArgumentException("Start " + start + " is more then end " + end);
        }
        int delLen = end - start;
        for (int i = start; i  < this.length  - delLen; i++) {
            charArray[i] = charArray[i+delLen];
        }
        this.length -= delLen;
    }
    public void capacitySetToFit() {
        this.setCapacity(this.length);
    }

    private void increaseCapacity() {
        try {
            this.setCapacity(this.length * CAPACITY_INCREASE_FACTOR);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isEmpty() {
        return this.length == 0;
    }

    public MyString toMyString() {
        return new MyString(this.charArray);
    }

    @Override
    public String toString() {
        char[] temp = new char[this.length];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = charArray[i];
        }
        return new String(temp);
    }    

    @Override
    public int length() {
        return this.length;
    }

    @Override
    public char charAt(int index) {
        if (index >= this.length || index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bound for length " + this.length);
        }
        return charArray[index];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        if (start < 0 || start >= this.length) {
            throw new IndexOutOfBoundsException("Index " + start + " out of bound for lenght " + this.length);
        } else if (end < 0 || end > this.length) {
            throw new IndexOutOfBoundsException("Index " + end + " out of bound for lenght " + this.length);
        } else if (start > end) {
            throw new IllegalArgumentException("Start " + start + " is more then end " + end);
        }
        char[] temp = new char[end - start];
        for (int i = start; i < end; i++) {
            temp[i] = charArray[i];
        }
        return new StringBuilder(temp);
    }
}
