package zombies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zombies.dao.SightDao;
import zombies.dao.ZombieDao;
import zombies.domain.Sight;
import zombies.domain.Zombie;

import java.util.List;

@Controller
@RequestMapping("api/zombieservice")
public class ZombieController {

    private ZombieDao zombieDao;
    private SightDao sightDao;

    @Autowired
    public ZombieController(ZombieDao zombieDao, SightDao sightDao) {
        this.zombieDao = zombieDao;
        this.sightDao = sightDao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public List<Zombie> getZombiesAll() {

        return zombieDao.get();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Zombie getZombie(@PathVariable("id") int id)
    {
            return zombieDao.getZombie(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Zombie> createZombie(@RequestParam("type")Zombie.ZombieType zombieType, @RequestParam("gender")Zombie.Gender gender) {
        Zombie zombie = new Zombie(zombieType, gender);
        Zombie createdZombie = zombieDao.addZombie(zombie);
        return new ResponseEntity<Zombie>(createdZombie, new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> modifyZombie(@PathVariable("id") int id, @RequestParam("type")Zombie.ZombieType zombieType, @RequestParam("gender")Zombie.Gender gender) {
        zombieDao.modify(id, gender, zombieType);
        return new ResponseEntity<>("", new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> deleteZombie(@PathVariable("id") int id) {
        //delete sights keep zombie
        sightDao.delete(id);
        return new ResponseEntity<>("", new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/sighting/", method = RequestMethod.GET)
    @ResponseBody
    public List<Sight> getSightingAll(){
        return sightDao.get();
    }

    @RequestMapping(value = "/sighting/", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Sight> createSight(@RequestBody Sight sight) {

        Sight createdSight;

        if (!(sight.getZombie().getId() > 0)) {
            Zombie createdZombie = zombieDao.addZombie(sight.getZombie());
            sight.setZombie(createdZombie);
        }

        createdSight = sightDao.addSight(sight);

        return new ResponseEntity<>(createdSight, new HttpHeaders(), HttpStatus.CREATED);
    }
}
