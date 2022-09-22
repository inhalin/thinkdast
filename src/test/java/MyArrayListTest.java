import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import websearch.MyArrayList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {

    protected List<Integer> mylist;
    protected List<Integer> list;


    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    public void setUp() throws Exception {
        list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);

        mylist = new MyArrayList<Integer>();
        mylist.addAll(list);
    }

    /**
     * Test method for {@link MyArrayList#MyArrayList()}.
     */
    @Test
    public void testMyList() {
        assertThat(mylist.size()).isEqualTo(3);
    }

    /**
     * Test method for {@link MyArrayList#add(Object)}.
     */
    @Test
    public void testAddT() {
        for (int i = 4; i < 20; i++) {
            mylist.add(i);
        }
        //System.out.println(Arrays.toString(mal.toArray()));
        assertThat(mylist.get(18)).isEqualTo(new Integer(19));
    }

    /**
     * Test method for {@link MyArrayList#add(int, Object)}.
     */
    @Test
    public void testAddIntT() {
        mylist.add(1, 5);
        //System.out.println(Arrays.toString(mal.toArray()));
        assertThat(mylist.get(1)).isEqualTo(new Integer(5));
        assertThat(mylist.size()).isEqualTo(4);

        try {
            mylist.set(-1, 0);
            fail();
        } catch (IndexOutOfBoundsException e) {} // good

        try {
            mylist.set(4, 0);
            fail();
        } catch (IndexOutOfBoundsException e) {} // good

        mylist.add(0, 6);
        //System.out.println(Arrays.toString(mal.toArray()));
        assertThat(mylist.get(0)).isEqualTo(6);

        mylist.add(5, 7);
        //System.out.println(Arrays.toString(mal.toArray()));
        assertThat(mylist.get(5)).isEqualTo(new Integer(7));
    }

    /**
     * Test method for {@link MyArrayList#addAll(java.util.Collection)}.
     */
    @Test
    public void testAddAllCollectionOfQextendsT() {
        mylist.addAll(list);
        mylist.addAll(list);
        mylist.addAll(list);
        assertThat(mylist.size()).isEqualTo(12);
        assertThat(mylist.get(5)).isEqualTo(new Integer(3));
    }

    /**
     * Test method for {@link MyArrayList#clear()}.
     */
    @Test
    public void testClear() {
        mylist.clear();
        assertThat(mylist.size()).isEqualTo(0);
    }

    /**
     * Test method for {@link MyArrayList#contains(Object)}.
     */
    @Test
    public void testContains() {
        assertThat(mylist.contains(1)).isTrue();
        assertThat(mylist.contains(4)).isFalse();
        assertThat(mylist.contains(null)).isFalse();
        mylist.add(null);
        assertThat(mylist.contains(null)).isTrue();
    }

    /**
     * Test method for {@link MyArrayList#containsAll(java.util.Collection)}.
     */
    @Test
    public void testContainsAll() {
        assertThat(mylist.containsAll(list)).isTrue();//, equalTo(true));
    }

    /**
     * Test method for {@link MyArrayList#get(int)}.
     */
    @Test
    public void testGet() {
        assertThat(mylist.get(1)).isEqualTo(2);//, is(new Integer(2)));
    }

    /**
     * Test method for {@link MyArrayList#indexOf(Object)}.
     */
    @Test
    public void testIndexOf() {
        assertThat(mylist.indexOf(1)).isEqualTo(0);
        assertThat(mylist.indexOf(2)).isEqualTo(1);//, is(1));
        assertThat(mylist.indexOf(3)).isEqualTo(2);//, is(2));
        assertThat(mylist.indexOf(4)).isEqualTo(-1);//, is(-1));
    }

    /**
     * Test method for {@link MyArrayList#indexOf(Object)}.
     */
    @Test
    public void testIndexOfNull() {
        assertThat(mylist.indexOf(null)).isEqualTo(-1);
        mylist.add(null);
        assertThat(mylist.indexOf(null)).isEqualTo(3);
    }

    /**
     * Test method for {@link MyArrayList#isEmpty()}.
     */
    @Test
    public void testIsEmpty() {
        assertThat(mylist.isEmpty()).isFalse();
        mylist.clear();
        assertThat(mylist.isEmpty()).isTrue();
    }

    /**
     * Test method for {@link MyArrayList#iterator()}.
     */
    @Test
    public void testIterator() {
        Iterator<Integer> iter = mylist.iterator();
        assertThat(iter.next()).isEqualTo(new Integer(1));
        assertThat(iter.next()).isEqualTo(new Integer(2));
        assertThat(iter.next()).isEqualTo(new Integer(3));
        assertThat(iter.hasNext()).isFalse();
    }

    /**
     * Test method for {@link MyArrayList#lastIndexOf(Object)}.
     */
    @Test
    public void testLastIndexOf() {
        mylist.add(2);
        assertThat(mylist.lastIndexOf(new Integer(2))).isEqualTo(3);
    }

    /**
     * Test method for {@link MyArrayList#remove(Object)}.
     */
    @Test
    public void testRemoveObject() {
        boolean flag = mylist.remove(new Integer(2));
        assertThat(flag).isTrue();
        assertThat(mylist.size()).isEqualTo(2);
        assertThat(mylist.get(1)).isEqualTo(new Integer(3));
        //System.out.println(Arrays.toString(mal.toArray()));

        flag = mylist.remove(new Integer(1));
        assertThat(flag).isTrue();
        assertThat(mylist.size()).isEqualTo(1);
        assertThat(mylist.get(0)).isEqualTo(new Integer(3));
        //System.out.println(Arrays.toString(mal.toArray()));

        flag = mylist.remove(new Integer(5));
        assertThat(flag).isFalse();
        assertThat(mylist.size()).isEqualTo(1);
        assertThat(mylist.get(0)).isEqualTo(new Integer(3));
        //System.out.println(Arrays.toString(mal.toArray()));

        flag = mylist.remove(new Integer(3));
        assertThat(flag).isTrue();
        assertThat(mylist.size()).isEqualTo(0);
        //System.out.println(Arrays.toString(mal.toArray()));
    }

    /**
     * Test method for {@link MyArrayList#remove(int)}.
     */
    @Test
    public void testRemoveInt() {
        Integer val = mylist.remove(1);
        assertThat(val).isEqualTo(new Integer(2));
        assertThat(mylist.size()).isEqualTo(2);
        assertThat(mylist.get(1)).isEqualTo(new Integer(3));
    }

    /**
     * Test method for {@link MyArrayList#removeAll(java.util.Collection)}.
     */
    @Test
    public void testRemoveAll() {
        mylist.removeAll(list);
        assertThat(mylist.size()).isEqualTo(0);
    }

    /**
     * Test method for {@link MyArrayList#set(int, Object)}.
     */
    @Test
    public void testSet() {
        Integer val = mylist.set(1, 5);
        assertThat(val).isEqualTo(new Integer(2));

        val = mylist.set(0, 6);
        assertThat(val).isEqualTo(new Integer(1));

        val = mylist.set(2, 7);
        assertThat(val).isEqualTo(new Integer(3));

        // return value should be 2
        // list should be [6, 5, 7]
        assertThat(mylist.get(0)).isEqualTo(new Integer(6));
        assertThat(mylist.get(1)).isEqualTo(new Integer(5));
        assertThat(mylist.get(2)).isEqualTo(new Integer(7));
        //System.out.println(Arrays.toString(mal.toArray()));

        try {
            mylist.set(-1, 0);
            fail();
        } catch (IndexOutOfBoundsException e) {} // good

        try {
            mylist.set(4, 0);
            fail();
        } catch (IndexOutOfBoundsException e) {} // good
    }

    /**
     * Test method for {@link MyArrayList#size()}.
     */
    @Test
    public void testSize() {
        assertThat(mylist.size()).isEqualTo(3);
    }

    /**
     * Test method for {@link MyArrayList#subList(int, int)}.
     */
    @Test
    public void testSubList() {
        mylist.addAll(list);
        List<Integer> sub = mylist.subList(1, 4);
        assertThat(sub.get(1)).isEqualTo(new Integer(3));
    }

    /**
     * Test method for {@link MyArrayList#toArray()}.
     */
    @Test
    public void testToArray() {
        Object[] array = mylist.toArray();
        assertThat((Integer)array[0]).isEqualTo(new Integer(1));
    }
}