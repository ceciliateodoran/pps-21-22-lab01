import lab01.tdd.CircularList;
import lab01.tdd.SimpleCircularList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

    private CircularList list;

    @BeforeEach
    public void setUp(){
        this.list = new SimpleCircularList();
    }

    @Test
    public void testInitialSize(){
        assertEquals(0, this.list.size());
    }

    @Test
    public void testEmptiness(){
        assertTrue(this.list.isEmpty());
    }

    @Test
    public void testAdd(){
        final int newElement = 2;
        this.list.add(newElement);
        assertEquals(1, this.list.size());
        assertFalse(this.list.isEmpty());
    }

    @Test
    public void testSize(){
        final int firstNewElement = 1;
        final int secondNewElement = 2;
        assertEquals(0, this.list.size());
        this.list.add(firstNewElement);
        this.list.add(secondNewElement);
        assertEquals(2, this.list.size());
    }

    @Test
    public void testNext(){
        final int firstNewElement = 1;
        final int secondNewElement = 2;
        final int thirdNewElement = 3;
        assertEquals(Optional.empty(), this.list.next());
        this.list.add(firstNewElement);
        this.list.add(secondNewElement);
        this.list.add(thirdNewElement);
        assertEquals(Optional.of(firstNewElement), this.list.next());
        assertEquals(Optional.of(secondNewElement), this.list.next());
        assertEquals(Optional.of(thirdNewElement), this.list.next());
        assertEquals(Optional.of(firstNewElement), this.list.next());
    }

    @Test
    public void testPrevious(){
        final int firstNewElement = 1;
        final int secondNewElement = 2;
        final int thirdNewElement = 3;
        assertEquals(Optional.empty(), this.list.next());
        this.list.add(firstNewElement);
        this.list.add(secondNewElement);
        this.list.add(thirdNewElement);
        assertEquals(Optional.of(firstNewElement), this.list.previous());
        assertEquals(Optional.of(thirdNewElement), this.list.previous());
        assertEquals(Optional.of(secondNewElement), this.list.previous());
        assertEquals(Optional.of(firstNewElement), this.list.previous());
        assertEquals(Optional.of(thirdNewElement), this.list.previous());
    }

    @Test
    public void testReset(){
        final int firstNewElement = 1;
        final int secondNewElement = 2;
        final int thirdNewElement = 3;
        this.list.add(firstNewElement);
        this.list.add(secondNewElement);
        this.list.add(thirdNewElement);
        this.list.next();
        this.list.next();
        assertEquals(Optional.of(thirdNewElement), this.list.next());
        this.list.reset();
        assertEquals(Optional.of(firstNewElement), this.list.next());
    }
}
