import lab01.tdd.CircularList;
import lab01.tdd.SelectStrategyFactory;
import lab01.tdd.SimpleCircularList;
import lab01.tdd.SimpleSelectStrategyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

    private CircularList list;
    private SelectStrategyFactory strategyFactory;

    @BeforeEach
    public void setUp(){
        this.list = new SimpleCircularList();
        this.strategyFactory = new SimpleSelectStrategyFactory();
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
        final int firstElement = 1;
        final int secondElement = 2;
        assertEquals(0, this.list.size());
        this.list.add(firstElement);
        this.list.add(secondElement);
        assertEquals(2, this.list.size());
    }

    @Test
    public void testNext(){
        final int firstElement = 1;
        final int secondElement = 2;
        final int thirdElement = 3;
        assertEquals(Optional.empty(), this.list.next());
        this.list.add(firstElement);
        this.list.add(secondElement);
        this.list.add(thirdElement);
        assertEquals(Optional.of(firstElement), this.list.next());
        assertEquals(Optional.of(secondElement), this.list.next());
        assertEquals(Optional.of(thirdElement), this.list.next());
        assertEquals(Optional.of(firstElement), this.list.next());
    }

    @Test
    public void testPrevious(){
        final int firstElement = 1;
        final int secondElement = 2;
        final int thirdElement = 3;
        assertEquals(Optional.empty(), this.list.next());
        this.list.add(firstElement);
        this.list.add(secondElement);
        this.list.add(thirdElement);
        assertEquals(Optional.of(firstElement), this.list.previous());
        assertEquals(Optional.of(thirdElement), this.list.previous());
        assertEquals(Optional.of(secondElement), this.list.previous());
        assertEquals(Optional.of(firstElement), this.list.previous());
        assertEquals(Optional.of(thirdElement), this.list.previous());
    }

    @Test
    public void testReset(){
        final int firstElement = 1;
        final int secondElement = 2;
        final int thirdElement = 3;
        this.list.add(firstElement);
        this.list.add(secondElement);
        this.list.add(thirdElement);
        this.list.next();
        this.list.next();
        assertEquals(Optional.of(thirdElement), this.list.next());
        this.list.reset();
        assertEquals(Optional.of(firstElement), this.list.next());
    }

    @Test
    public void testNextEvenStrategy(){
        final int firstElement = 1;
        final int secondElement = 2;
        final int thirdElement = 3;
        this.list.add(firstElement);
        this.list.add(secondElement);
        this.list.add(thirdElement);
        assertNotEquals(Optional.of(firstElement), this.list.next(this.strategyFactory.createEvenStrategy()));
        assertEquals(Optional.of(secondElement), this.list.next(this.strategyFactory.createEvenStrategy()));
        assertNotEquals(Optional.of(thirdElement), this.list.next(this.strategyFactory.createEvenStrategy()));
    }

    @Test
    public void testNextMultipleStrategy(){
        final int firstElement = 1;
        final int secondElement = 2;
        final int thirdElement = 3;
        final int fourthElement = 4;
        this.list.add(firstElement);
        this.list.add(secondElement);
        this.list.add(thirdElement);
        this.list.add(fourthElement);
        assertEquals(Optional.of(thirdElement), this.list.next(this.strategyFactory.createMultipleOfStrategy(3)));
        assertEquals(Optional.of(fourthElement), this.list.next(this.strategyFactory.createMultipleOfStrategy(2)));
        assertNotEquals(Optional.of(thirdElement), this.list.next(this.strategyFactory.createMultipleOfStrategy(2)));
    }

    @Test
    public void testNextEqualsStrategy(){
        final int firstElement = 1;
        final int secondElement = 2;
        final int thirdElement = 3;
        this.list.add(firstElement);
        this.list.add(secondElement);
        this.list.add(thirdElement);
        assertEquals(Optional.of(firstElement), this.list.next(this.strategyFactory.createEqualsStrategy(firstElement)));
        assertEquals(Optional.of(secondElement), this.list.next(this.strategyFactory.createEqualsStrategy(secondElement)));
        assertEquals(Optional.of(thirdElement), this.list.next(this.strategyFactory.createEqualsStrategy(thirdElement)));
    }
}
