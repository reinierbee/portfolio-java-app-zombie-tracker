package zombies.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import zombies.domain.Sight;
import zombies.domain.Zombie;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class SightImplTest {
    Zombie zombie = new Zombie(Zombie.ZombieType.CONTAMINATED, Zombie.Gender.TRANSGENDER);
    ZombieDao zombielist = new ZombieImpl();


    @Before
    public void setUp() throws Exception {
        zombielist.addZombie(zombie);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void canSpotZombie() throws Exception {

        SightDao sightList = new SightImpl();

        Sight sight = new Sight(1,1, "Zombie met rood haar gevonden, of toch een ginger?", new Date(),zombie);

        sightList.addSight(sight);
        assertEquals(sightList.get().size(),1);
    }
}