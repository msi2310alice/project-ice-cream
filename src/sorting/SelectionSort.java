package sorting;

import java.util.Comparator;
import java.util.List;

import type.SortDirection;

public class SelectionSort<T> extends AbstractSorter<T> {
    @Override
    public void sort(
            List<T> list,
            Comparator<T> comparator,
            SortDirection direction) {

        int size = list.size();

        for (int i = 0; i < size - 1; i++) {

            int selectedIndex = i;

            for (int j = i + 1; j < size; j++) {

                if (compare(
                        list.get(j),
                        list.get(selectedIndex),
                        comparator,
                        direction) < 0) {

                    selectedIndex = j;
                }
            }

            if (selectedIndex != i) {

                T temp = list.get(i);
                list.set(i, list.get(selectedIndex));
                list.set(selectedIndex, temp);
            }
        }
    }
}
