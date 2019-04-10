package zombies.dao;


import zombies.domain.Zombie;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ZombieImpl implements ZombieDao {

    private List<Zombie> zombieList = new ArrayList<Zombie>();
    private Zombie zombie1 = new Zombie();
    private int nextId = 0;

    @Override
    public Zombie addZombie(Zombie zombie) {
        Zombie z = new Zombie(nextId, zombie.getType(), zombie.getGender());
        zombieList.add(z);
        nextId++;
        return z;
    }

    @Override
    public List<Zombie> get() {
        return zombieList;
    }

    @Override
    public Zombie getZombie(int id) {
        return zombie1;
    }

    @Override
    public void delete(int id) {
        zombieList.remove(id);
    }

    @Override
    public Optional<Zombie> modify(int id, Zombie.Gender gender, Zombie.ZombieType zombieType) {
        Optional<Zombie> oldzombie = zombieList.stream().filter(z -> z.getId() == id).findAny();

        Zombie zombie = new Zombie(oldzombie.get().getId(), zombieType, gender);
        return oldzombie.map(z -> {
            int i = zombieList.indexOf(z);
            zombieList.set(i, zombie);
            return zombie;
        });
    }

}
