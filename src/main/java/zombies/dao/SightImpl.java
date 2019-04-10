package zombies.dao;


import zombies.domain.Sight;

import java.util.ArrayList;
import java.util.List;

public class SightImpl implements SightDao {

    private List<Sight> sightList = new ArrayList<Sight>();
    private int nextId = 0;

    @Override
    public Sight addSight(Sight sight) {
        sightList.add(nextId,sight);
        nextId++;
        return sight;
    }

    @Override
    public List<Sight> get() {
        return sightList;
    }

    @Override
    public void delete(int id) {
        sightList.remove(id);

    }

}
