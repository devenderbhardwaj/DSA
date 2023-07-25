package array;

import java.util.Iterator;

public class DynamicArray implements Iterable<Integer> {
    private int[] integers;
    private int capacity;
    private int length = 0;
    private final int CAPACITY_INCREASE_STEP = 10;

    public DynamicArray(int capacity) {
        integers = new int[capacity];
        this.capacity = capacity;
    }

    public DynamicArray() {
        this(10);
    }

    public int getCapacity() {
        return capacity;
    }

    private void setCapacity(int capacity) {
        if (this.capacity < this.length) {
            throw new IllegalArgumentException("Cannot decrese capacity less the current size of array");
        }
        int[] newArray = new int[capacity];

        for (int i = 0; i < this.length; ++i) {
            newArray[i] = integers[i];
        }

        this.capacity = capacity;
        this.integers = newArray;
    }

    public void add(int value) {
        if (this.length == this.capacity) {
            this.increaseCapacity();
        }
        this.integers[this.length] = value;
        ++this.length;
    }

    public void update(int value, int index){
        if (index >= this.length || index < 0) {
            throw new IndexOutOfBoundsException("Index "+index+" is out of bound for length "+this.length);
        }
        this.integers[index] = value;
    }

    public int get(int index) {
        if (index >= this.length || index < 0) {
            throw new IndexOutOfBoundsException("Index "+index+" is out of bound for length "+this.length);
        }
        return this.integers[index];
    }

    public void shiftInsert(int value, int index) throws Exception {
        if (index > this.length || index < 0) {
            throw new IndexOutOfBoundsException("Index "+index+" is out of bound for length "+this.length);
        }
        if (this.length == this.capacity) {
            this.increaseCapacity();
        }
        int bubble = integers[index];
        integers[index] = value;
        for (int i = index + 1; i <= this.length; ++i) {
            int temp = integers[i];
            integers[i] = bubble;
            bubble = temp;
        }
        ++this.length;
    }

    public void delete(int index) throws Exception {
        if (index >= this.length || index < 0) {
            throw new IndexOutOfBoundsException("Index "+index+" is out of bound for length "+this.length);
        }
        if (this.length != 0) {
            for (int i = index; i < this.length - 1; ++i) {
                integers[i] = integers[i + 1];
            }
            --this.length;
            if (this.capacity - this.length > CAPACITY_INCREASE_STEP) {
                this.decreaseCapacity();
            }
        }
    }

    private void decreaseCapacity() {
        this.setCapacity(this.length);
    }

    private void increaseCapacity() {
        try {
            this.setCapacity(this.length + CAPACITY_INCREASE_STEP);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Same as Arrays.toString()
    public String arrayString() {
        if (this.length == 0) {
            return "[]";
        }
        String str = "[";
        for (int i = 0; i < this.length - 1; ++i) {
            str += integers[i] + ",";
        }
        str += integers[this.length - 1] + "]";
        return str;
    }

    public int search(int e) {
        for (int i = 0; i < integers.length; i++) {
            if (integers[i] == e) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return "DynamicArray2 [integers=" + this.arrayString() + ", capacity=" + capacity + ", length=" + length
                + "]";
    }

    public void display() {
        System.out.println(this);
    }

    public boolean isEmpty() {
        return this.length == 0;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < length;
            }

            @Override
            public Integer next() {
                int temp = integers[index];
                ++index;
                return temp;
            }

        };
    }
}
