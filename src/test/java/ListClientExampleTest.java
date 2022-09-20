import org.junit.jupiter.api.Test;
import websearch.ListClientExample;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ListClientExampleTest {

    @Test
    void testInstanceOfList() {
        ListClientExample lce = new ListClientExample();
        List list = lce.getList();

        assertThat(list).isInstanceOf(ArrayList.class);
    }
}