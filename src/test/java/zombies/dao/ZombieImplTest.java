package zombies.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import zombies.domain.Zombie;

import java.util.Optional;

import static org.junit.Assert.*;

public class ZombieImplTest {
    Zombie zombie;
    ZombieDao zombielist = new ZombieImpl();


    @Before
    public void setUp() throws Exception {
        zombie = zombielist.addZombie(new Zombie(Zombie.ZombieType.CONTAMINATED, Zombie.Gender.TRANSGENDER));
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void canCreateZombie() throws Exception {
        assertEquals(zombielist.get().size(),1);
    }

    @Test
    public void canDeleteZombie() throws Exception{
        zombielist.delete(zombie.getId());
        assertEquals(zombielist.get().size(),0);
    }

    @Test
    public void canModifyZombie() throws Exception{
        Optional<Zombie> newzombie = zombielist.modify(zombie.getId(), Zombie.Gender.FEMALE, Zombie.ZombieType.CRAWLER);
        assertEquals(zombielist.get().size(),1);
        assertEquals(newzombie.get().getGender(), Zombie.Gender.FEMALE);
        assertEquals(newzombie.get().getType(), Zombie.ZombieType.CRAWLER);
    }
}