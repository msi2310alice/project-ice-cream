package util;

import sorting.IFSortingStrategy;
import type.SortDirection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortBenchmark {
    public static <T> long messure(List<T> originalList, 
                                    IFSortingStrategy<T> sortingStrategy,
                                    Comparator<T> comparator,
                                    SortDirection direction
    ) {
        List<T> testList = new ArrayList<>(originalList);

        long startTime = System.nanoTime();
        sortingStrategy.sort(testList, comparator, direction);
        long endTime = System.nanoTime();

        return endTime - startTime;
    }
}
