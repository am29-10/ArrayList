package test;

import main.MyArrayList;
import main.MyList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class MyArrayListTest {
    MyList<Integer> arrays;
    List<Integer> arraysDemo;

    @BeforeEach
    void beforeEach() {
        arrays = new MyArrayList<>();
        arrays.add(1);
        arrays.add(2);
        arrays.add(3);
        arraysDemo = new ArrayList<>();
    }

    @Test
    void add() {
        Assertions.assertEquals(3, arrays.size());
    }

    @Test
    void get() {
        Assertions.assertEquals(1, arrays.get(0));
        Assertions.assertEquals(2, arrays.get(1));
        Assertions.assertEquals(3, arrays.get(2));
    }

    @Test
    void remove() {
        arrays.remove(1);
        Assertions.assertEquals(2, arrays.size());
        Assertions.assertEquals(1, arrays.get(0));
        Assertions.assertEquals(3, arrays.get(1));
    }

    @Test
    void clear() {
        arrays.clear();
        Assertions.assertEquals(0, arrays.size());
        Assertions.assertNull(arrays.get(0));
    }

    @Test
    void sort() {
        arrays.add(9);
        arrays.add(5);
        arrays.add(7);
        arrays.sort((o1, o2) -> o1 - o2);
        Assertions.assertEquals(9, arrays.get(5));
        Assertions.assertEquals(7, arrays.get(4));
        Assertions.assertEquals(5, arrays.get(3));
        Assertions.assertEquals(3, arrays.get(2));
        Assertions.assertEquals(2, arrays.get(1));
        Assertions.assertEquals(1, arrays.get(0));
        arrays.sort((o1, o2) -> o2 - o1);
        Assertions.assertEquals(1, arrays.get(5));
        Assertions.assertEquals(2, arrays.get(4));
        Assertions.assertEquals(3, arrays.get(3));
        Assertions.assertEquals(5, arrays.get(2));
        Assertions.assertEquals(7, arrays.get(1));
        Assertions.assertEquals(9, arrays.get(0));
    }

    @Test
    void testAdd() {
        arrays.add(1, 99);
        Assertions.assertEquals(1, arrays.get(0));
        Assertions.assertEquals(99, arrays.get(1));
        Assertions.assertEquals(2, arrays.get(2));
        Assertions.assertEquals(3, arrays.get(3));
    }

    @Test
    void size() {
        Assertions.assertEquals(3, arrays.size());
    }

    @Test
    void isEmpty() {
        arrays.clear();
        Assertions.assertTrue(arrays.isEmpty());
    }
}