package linkedList;

public interface LinkedList extends Iterable<Integer>{
    public void add(int value);

    public void addFirst(int value);

    public void addAt(int value, int index);

    public void delete(int index);

    public void deleteFirst();

    public void deleteLast();

    public void update(int value, int index);

    public int get(int index);

    public int getFirst() ;

    public int getLast() ;

    public void traverse();

    public int getSize();

    public void clear();

    public void reverse();

    public int search(int value);

}
