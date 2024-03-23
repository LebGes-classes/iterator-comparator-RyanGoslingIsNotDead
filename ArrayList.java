package List.ArrayList;
import List.List;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Comparator;

public class ArrayList<T> implements List<T>{
    private Object[] list;
    private int size;
    private final int Default_Capacity = 10;

    public ArrayList(int capacity){
        list = new Object[capacity];
    }
    public ArrayList(){
        list = new Object[Default_Capacity];
    }
    public void add(T data){
        if (size == list.length){
            resizing();
        }
        list[size] = data;
        size++;
    }
    public void add(int index, T data){
        if (size == list.length){
            resizing();
        }
        for (int i=size;i>index;i--){
            list[i] = list[i-1];
        }
        list[index] = data;
        size++;
    }
    public void remove(int index){
        for (int i=index;i<size+1;i++) {
            list[i] = list[i + 1];
        }
    }
    public void removeByData(T data){
        remove(getIndex(data));
        size--;
    }
    public void removeBack(){
        list[size-1] = 0;
        size--;
    }
    public void clear(){
        for(int i =0; i<size;i++){
            list[i]=0;
        }
        size = 0;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public Object getData(int index){
        return list[index];
    }
    public int getIndex(T data){
        int index = 0;
        while (!list[index].equals(data)){
            index++;
        }
        return index;
    }
    public int getSize(){
        return size;
    }
    public void print(){
        for (Object elem: list){
            System.out.print(elem + " ");
        }
    }
    private void resizing(){
        Object[] newList = new Object[size*2];
        for(int i=0;i<list.length;i++){
            newList[i] = list[i];
        }
        list = newList;
    }
    public Iterator<T> iterator() {
        return new ArrayListIterator<>();
    }
    private class ArrayListIterator<T> implements Iterator<T>{
        private int cursor;
        private int lastRet = -1;
        ArrayListIterator(){}
        public boolean hasNext(){
            return cursor != size;
        }
        public T next(){
            int i = cursor;
            if (i>=size){
                throw new NoSuchElementException();
            }
            cursor = i + 1;
            return (T) list[lastRet = i];
        }
        public void remove(){
            ArrayList.this.remove(lastRet);
            cursor = lastRet;
            lastRet = -1;
        }
    }
    public static class IntegerComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer num1, Integer num2) {
            return num1 - num2;
        }
    }
}
