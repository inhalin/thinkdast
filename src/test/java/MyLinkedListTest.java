import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import websearch.MyLinkedList;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class MyLinkedListTest {

    protected List<Integer> mylist;
    protected List<Integer> list;


    @BeforeEach
    public void setUp() {
        list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        mylist = new MyLinkedList<>();
        mylist.addAll(list);
    }

    @Test
    void addIdxE() {
        mylist.add(1, 5);
        assertThat(mylist.get(1)).isEqualTo(5);
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
        assertThat(mylist.get(0)).isEqualTo(6);

        mylist.add(5, 7);
        assertThat(mylist.get(5)).isEqualTo(7);
    }

    @Test
    void removeIdx() {
        int val = mylist.remove(1);
        assertThat(val).isEqualTo(2);
        assertThat(mylist.size()).isEqualTo(2);
        assertThat(mylist.get(1)).isEqualTo(3);
    }

    @ParameterizedTest
    @CsvSource(value = {"1,0", "2,1", "3,2", "4,-1"}, delimiter = ',')
    void indexOf(int input, int expected) {
        assertThat(mylist.indexOf(input)).isEqualTo(expected);
    }
}
