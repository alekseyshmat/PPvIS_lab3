package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Randoming {
    private List<Integer> list;

    public Randoming(int size) {
        Random random = new Random();
        list = new ArrayList<>(size);
        for(int i=0; i<size; i++){
            list.add(random.nextInt());
        }
    }
    public List<Integer> getList(){
        return list;
    }

    public int size(){
        return list.size();
    }

}
