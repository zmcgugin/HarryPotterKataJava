package kata;

import org.junit.Test;

import java.util.Collections;

import static java.util.Arrays.asList;
import static java.util.Collections.*;
import static kata.HarryPotterBookStore.checkout;
import static kata.HarryPotterBookStore.checkoutJava8Streams;
import static org.junit.Assert.*;

public class HarryPotterBookStoreTest {

    @Test
    public void testBasics() {
        assertEquals(0, checkout(emptyList()), 2);
        assertEquals(8, checkout(singletonList(0)), 2);
        assertEquals(8, checkout(singletonList(1)), 2);
        assertEquals(8, checkout(singletonList(2)), 2);
        assertEquals(8, checkout(singletonList(3)), 2);
        assertEquals(8, checkout(singletonList(4)), 2);
        assertEquals(8 * 2, checkout(asList(0, 0)), 2);
        assertEquals(8 * 3, checkout(asList(1, 1, 1)), 2);
    }

    @Test
    public void testSimpleDiscounts() {
        assertEquals(8 * 2 * 0.95, checkout(asList(0, 1)), 2);
        assertEquals(8 * 3 * 0.9, checkout(asList(0, 2, 4)), 2);
        assertEquals(8 * 4 * 0.8, checkout(asList(0, 1, 2, 4)), 2);
        assertEquals(8 * 5 * 0.75, checkout(asList(0, 1, 2, 3, 4)), 2);
    }

    @Test
    public void testSeveralDiscounts() {
        assertEquals(8 + (8 * 2 * 0.95), checkout(asList(0, 0, 1)), 2);
        assertEquals(2 * (8 * 2 * 0.95), checkout(asList(0, 0, 1, 1)), 2);
        assertEquals((8 * 4 * 0.8) + (8 * 2 * 0.95), checkout(asList(0, 0, 1, 2, 2, 3)), 2);
        assertEquals(8 + (8 * 5 * 0.75), checkout(asList(0, 1, 1, 2, 3, 4)), 2);
    }

    @Test
    public void testEdgeCases() {
        assertEquals(2 * (8 * 4 * 0.8), checkout(asList(0, 0, 1, 1, 2, 2, 3, 4)), 2);
        assertEquals(3 * (8 * 5 * 0.75) + 2 * (8 * 4 * 0.8),
                checkout(asList(0, 0, 0, 0, 0,
                1, 1, 1, 1, 1,
                2, 2, 2, 2,
                3, 3, 3, 3, 3,
                4, 4, 4, 4)), 2);
    }

    @Test
    public void testBasicsJava8Streams() {
        assertEquals(0, checkoutJava8Streams(emptyList()), 2);
        assertEquals(8, checkoutJava8Streams(singletonList(0)), 2);
        assertEquals(8, checkoutJava8Streams(singletonList(1)), 2);
        assertEquals(8, checkoutJava8Streams(singletonList(2)), 2);
        assertEquals(8, checkoutJava8Streams(singletonList(3)), 2);
        assertEquals(8, checkoutJava8Streams(singletonList(4)), 2);
        assertEquals(8 * 2, checkoutJava8Streams(asList(0, 0)), 2);
        assertEquals(8 * 3, checkoutJava8Streams(asList(1, 1, 1)), 2);
    }

    @Test
    public void testSimpleDiscountsJava8Streams() {
        assertEquals(8 * 2 * 0.95, checkoutJava8Streams(asList(0, 1)), 2);
        assertEquals(8 * 3 * 0.9, checkoutJava8Streams(asList(0, 2, 4)), 2);
        assertEquals(8 * 4 * 0.8, checkoutJava8Streams(asList(0, 1, 2, 4)), 2);
        assertEquals(8 * 5 * 0.75, checkoutJava8Streams(asList(0, 1, 2, 3, 4)), 2);
    }

    @Test
    public void testSeveralDiscountsJava8Streams() {
        assertEquals(8 + (8 * 2 * 0.95), checkoutJava8Streams(asList(0, 0, 1)), 2);
        assertEquals(2 * (8 * 2 * 0.95), checkoutJava8Streams(asList(0, 0, 1, 1)), 2);
        assertEquals((8 * 4 * 0.8) + (8 * 2 * 0.95), checkoutJava8Streams(asList(0, 0, 1, 2, 2, 3)), 2);
        assertEquals(8 + (8 * 5 * 0.75), checkoutJava8Streams(asList(0, 1, 1, 2, 3, 4)), 2);
    }

    @Test
    public void testEdgeCasesJava8Streams() {
        assertEquals(2 * (8 * 4 * 0.8), checkoutJava8Streams(asList(0, 0, 1, 1, 2, 2, 3, 4)), 2);
        assertEquals(3 * (8 * 5 * 0.75) + 2 * (8 * 4 * 0.8),
                checkoutJava8Streams(asList(0, 0, 0, 0, 0,
                        1, 1, 1, 1, 1,
                        2, 2, 2, 2,
                        3, 3, 3, 3, 3,
                        4, 4, 4, 4)), 2);
    }
}