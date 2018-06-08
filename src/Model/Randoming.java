package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Randoming {
    private List<Integer> list;

    public Randoming(){}

    public List randomize(int size) {
        Random random = new Random();
        list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(100));
        }
        viewList();
        return list;
    }

    public List getList() {
        return list;
    }

    public int size() {
        return list.size();
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    private void viewList() {
        System.out.println();
        for (int elementsOfList : list) {
            System.out.print(elementsOfList + " ");
        }
        System.out.println();
    }

}
