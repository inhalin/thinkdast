package websearch;

import java.util.*;

public class MyArrayList<T> implements List<T> {
    int size;
    private T[] array;

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        array = (T[]) new Object[10];
        size = 0;
    }

    public static void main(String[] args) {
        MyArrayList<Integer> myList = new MyArrayList<>();
        myList.add(1);
        myList.add(2);
        myList.add(3);

        System.out.println(Arrays.toString(myList.toArray()) + " size = " + myList.size);

        myList.remove(Integer.valueOf(2));
        System.out.println(Arrays.toString(myList.toArray()) + " size = " + myList.size);
    }

    @Override
    public boolean add(T element) {
        // TODO: add 현재 배열의 크기보다 더 많이 요소를 추가하려고 하면 배열 길이를 두배로 늘린다.
        /*
         배열에 빈 공간이 있는 경우, 즉 배열의 크기를 변경하지 않아도 되는 경우에는 실행 시간은 상수시간이다.
         배열에 빈 공간이 없어서 크기를 변경해야 하는 경우 실행 시간은 배열 크기에 비례한 선형 시간이 된다.
         요소 n번 추가시 요소 n개 저장, n-2개 복사한다.
         따라서 총 연산 횟수는 2n - 2 이고, 평균 연산 횟수는 (2n - 2) / n = 2 - (2 / n) 가 된다.
         n이 커지면 두번째 항은 매우 작아지므로 최종적으로는 2만 남아서 상수 시간이 된다.
         */
        if (size >= array.length) {
            T[] bigger = (T[]) new Object[array.length * 2];
            System.arraycopy(array, 0, bigger, 0, array.length); // O(n) 배열의 크기를 변경하는 경우 실행시간은 배열 크기에 비례한다.
            array = bigger;
        }
        array[size] = element; // O(1) 배열에 빈 공간이 있으면 상수 시간이다.
        size++;

        return true;
    }

    @Override
    public void add(int index, T element) { // 최종: O(n)
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        // add the element to get the resizing
        add(element); // O(1) 상수 시간

        // shift the elements
        for (int i = size - 1; i > index; i--) { // O(n)
            array[i] = array[i - 1]; // O(1)
        }
        // put the new one in the right place
        array[index] = element;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        boolean flag = true;
        for (T element : collection) {
            flag &= add(element);
        }
        return flag;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        // note: this version does not actually null out the references
        // in the array, so it might delay garbage collection.
        size = 0;
    }

    @Override
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object element : collection) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public T get(int index) { // 최종: O(1)
        if (index < 0 || index >= size) { // O(1)
            throw new IndexOutOfBoundsException(); // O(1)
        }
        return array[index];
    }

    @Override
    public int indexOf(Object target) { // 최종: O(n)
        // TODO: indexOf 해당 타깃이 배열에 있으면 타깃의 인덱스를 반환하고, 없으면 -1을 반환한다.
        for (int i = 0; i < size; i++) { // O(n)
            if (equals(target, array[i])) { // O(1)
                return i;
            }
        }
        return -1;
    }

    /**
     * Checks whether an element of the array is the target.
     * <p>
     * Handles the special case that the target is null.
     *
     * @param target
     * @param element
     */
    private boolean equals(Object target, Object element) {
        if (target == null) { // O(1)
            return element == null; // O(1)
        } else {
            return target.equals(element); // O(1)
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<T> iterator() {
        // make a copy of the array
        T[] copy = Arrays.copyOf(array, size);
        // make a list and return an iterator
        return Arrays.asList(copy).iterator();
    }

    @Override
    public int lastIndexOf(Object target) {
        // see notes on indexOf
        for (int i = size - 1; i >= 0; i--) {
            if (equals(target, array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        // make a copy of the array
        T[] copy = Arrays.copyOf(array, size);
        // make a list and return an iterator
        return Arrays.asList(copy).listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        // make a copy of the array
        T[] copy = Arrays.copyOf(array, size);
        // make a list and return an iterator
        return Arrays.asList(copy).listIterator(index);
    }

    @Override
    public boolean remove(Object obj) {
        int index = indexOf(obj);
        if (index == -1) {
            return false;
        }
        remove(index);
        return true;
    }

    @Override
    public T remove(int index) { // 최종: O(n)
        // TODO: remove 해당 인덱스에 위치한 요소를 제거하고 기존 요소를 반환한다.
        T removed = get(index); // O(1)
        for (int i = index; i < size - 1; i++) { // O(n)
            array[i] = array[i + 1]; // O(1)
        }
        size--;
        return removed;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean flag = true;
        for (Object obj : collection) { // collection 요소가 m개면 O(m)
            flag &= remove(obj); // 제거할 요소가 n개면 remove()가 선형이므로 O(n)
        } // => O(mn)
        return flag;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T set(int index, T element) { // 최종: O(1)
        // TODO: set 해당 인덱스 위치에 새로운 요소를 넣고 기존 요소를 반환한다.
        T old = get(index); // -> O(1)
        array[index] = element; // -> O(1)
        return old;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex >= size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }
        T[] copy = Arrays.copyOfRange(array, fromIndex, toIndex);
        return Arrays.asList(copy);
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, size);
    }

    @Override
    public <U> U[] toArray(U[] array) {
        throw new UnsupportedOperationException();
    }
}
