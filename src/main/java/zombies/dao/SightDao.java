package zombies.dao;

import zombies.domain.Sight;

import java.util.List;

public interface SightDao {

    Sight addSight(Sight sight);

    List<Sight> get();

    void delete(int id);

}
