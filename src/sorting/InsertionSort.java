package sorting;

import java.util.Comparator;
import java.util.List;

import type.SortDirection;

public class InsertionSort<T> extends AbstractSorter<T> {
    @Override
    public void sort(
            List<T> list,
            Comparator<T> comparator,
            SortDirection direction) {

        for (int i = 1; i < list.size(); i++) {

            T current = list.get(i);
            int j = i - 1;

            while (j >= 0 &&
                    compare(
                            list.get(j),
                            current,
                            comparator,
                            direction) > 0) {

                list.set(j + 1, list.get(j));
                j--;
            }

            list.set(j + 1, current);
        }
    }
}
