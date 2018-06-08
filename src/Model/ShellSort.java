package Model;

import java.util.List;

public class ShellSort {
    private Randoming list;

    public ShellSort(int size) {
        list = new Randoming(size);
        listSort(list.getList());
    }

    public void sort() {
        List<Integer> arr = list.getList();
        listSort(arr);

        System.out.println("Итоговый 2 ");
        for (int el : arr) {
            System.out.print(el + " ");
        }
        System.out.println();
    }

    private void listSort(List<Integer> arr) {
        int k;
        for (int i = arr.size() / 2; i > 0; i /= 2) {
            for (int j = i; j < arr.size(); j++) {
                int tmp = arr.get(j);
                for (k = j; k >= i && arr.get(k - i) > tmp; k -= i) {
                    arr.set(k, arr.get(k - i));
                }
                arr.set(k, tmp);
            }
        }
    }
}



