package sorting;

import java.util.Comparator;

import type.SortDirection;

public abstract class AbstractSorter<T> implements IFSortingStrategy<T>{
    protected int compare(
        T object1, 
        T object2,
        Comparator<T> comparator,
        SortDirection direction
    ) {
        int result = comparator.compare(object1, object2);

        if (direction == SortDirection.ASCENDING) {
            return result;
        }

        return -result;
    }
}
