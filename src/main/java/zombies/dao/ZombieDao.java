package zombies.dao;

import zombies.domain.Zombie;
import zombies.domain.Zombie.Gender;
import zombies.domain.Zombie.ZombieType;

import java.util.List;
import java.util.Optional;

public interface ZombieDao {

    Zombie addZombie(Zombie zombie);

    List<Zombie> get();

    Zombie getZombie(int id);

    void delete(int id);

    Optional<Zombie> modify(int id, Gender gender, ZombieType zombieType);
}
