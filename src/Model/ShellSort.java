package Model;

import java.util.List;

public class ShellSort {
    private Randoming list;

    public ShellSort(int size) {
        list = new Randoming(size);
        sort(list.getList());
    }

    private void sort(List<Integer> arr) {
        int increment = arr.size() / 2;
        while (increment >= 1) {
            for (int startIndex = 0; startIndex < increment; startIndex++) {
                insertionSort(arr, startIndex, increment);
            }
            increment = increment / 2;
        }

        System.out.println("Итоговый 2 ");
        for (int el : arr) {
            System.out.print(el + " ");
        }
    }

    private void insertionSort(List<Integer> arr, int startIndex, int increment) {
        for (int i = startIndex; i < arr.size() - 1; i = i + increment) {
            for (int j = Math.min(i + increment, arr.size() - 1); j - increment >= 0; j = j - increment) {
                if (arr.get(j - increment) > arr.get(j)) {
                    int tmp = arr.get(j);
                    arr.set(j, arr.get(j - increment));
                    arr.set(j - increment, tmp);
                } else {
                    break;
                }
            }
        }
    }
}


