package generated;

import org.junit.jupiter.api.Test;
import sorting.BubbleSort;
import sorting.IFSortingStrategy;
import sorting.InsertionSort;
import sorting.SelectionSort;
import type.SortDirection;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SortingStrategyTest {

    @Test
    void bubbleSort_shouldSortBothDirections() {
        assertBothDirections(new BubbleSort<>());
    }

    @Test
    void insertionSort_shouldSortBothDirections() {
        assertBothDirections(new InsertionSort<>());
    }

    @Test
    void selectionSort_shouldSortBothDirections() {
        assertBothDirections(new SelectionSort<>());
    }

    private void assertBothDirections(IFSortingStrategy<Integer> sorter) {
        List<Integer> values = new ArrayList<>(List.of(4, 1, 3, 1, 2));

        sorter.sort(values, Integer::compareTo, SortDirection.ASCENDING);
        assertEquals(List.of(1, 1, 2, 3, 4), values);

        sorter.sort(values, Integer::compareTo, SortDirection.DESCENDING);
        assertEquals(List.of(4, 3, 2, 1, 1), values);
    }
}