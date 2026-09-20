package sorting;

import java.util.Comparator;
import java.util.List;

import type.SortDirection;

public class BubbleSort<T> extends AbstractSorter<T>{
    @Override
    public void sort(
            List<T> list,
            Comparator<T> comparator,
            SortDirection direction) {

        int size = list.size();

        for (int i = 0; i < size - 1; i++) {

            for (int j = 0; j < size - 1 - i; j++) {

                if (compare(
                        list.get(j),
                        list.get(j + 1),
                        comparator,
                        direction) > 0) {

                    T temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }
}
