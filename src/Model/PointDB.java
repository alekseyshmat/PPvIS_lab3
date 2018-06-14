package Model;

import java.util.ArrayList;
import java.util.List;

public class PointDB {
    private List<PointCoor> pointsList;

    public PointDB() {
        pointsList = new ArrayList<>();
    }

    public void add(PointCoor record) {
        pointsList.add(record);
    }

    public List<PointCoor> getRecordOfPoints() {
        return pointsList;
    }

    public void deleteRecords() {
        pointsList.clear();
    }

    public int getSize() {
        return pointsList.size();
    }
}
