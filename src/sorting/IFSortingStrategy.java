package sorting;

import java.util.Comparator;
import java.util.List;

import type.SortDirection;

public interface IFSortingStrategy<T> {
    void sort(
        List<T> list,
        Comparator<T> comparator,
        SortDirection direction
    );
    //Comparator.naturalOrder() => sort theo comparable (theo ID)
}
