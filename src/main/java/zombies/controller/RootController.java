package zombies.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.annotations.ApiIgnore;
import zombies.domain.Sight;
import zombies.domain.Zombie;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class RootController {

    ZombieController zombieController;

    RootController(ZombieController zombieController){
        this.zombieController = zombieController;
    }

    @ApiIgnore
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "index";
    }

    @ApiIgnore
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(ModelMap model) {
        Zombie zombie = new Zombie();

        model.addAttribute("zombie", zombie);
        return "register";
    }

    @ApiIgnore
    @RequestMapping(value = "/registersubmit", method = RequestMethod.POST)
    public String registersubmit(HttpServletRequest request, ModelMap  model) {

        try {

            String desc = request.getParameter("desc");
            String type = request.getParameter("type");
            String gender =  request.getParameter("gender");
            String lat = request.getParameter("latitude");
            String lng = request.getParameter("longitude");

            Sight sight = new Sight();
            sight.setLat(Float.parseFloat(lat));
            sight.setLng(Float.parseFloat(lng));
            sight.setTime(new Date());
            sight.setDescription(desc);
            sight.setZombie(new Zombie(Zombie.ZombieType.valueOf(type), Zombie.Gender.valueOf(gender)));

            zombieController.createSight(sight);

            model.addAttribute("message", "Zombie spotted ... You made the world a little saver <a href=\"/sighting\"> Go and see more zombies here </a>");

        } catch (Exception e) {
            model.addAttribute("message", "Something went wrong <a href=\"/register\"> go back </a> Error: "  + e.getMessage());
        }


        return "registersubmit";
    }

    @ApiIgnore
    @RequestMapping(value = "/sighting", method = RequestMethod.GET)
    public String showZombies(ModelMap model){

        List<Sight> sightingAll = zombieController.getSightingAll();


        model.addAttribute("sightings",sightingAll);
        return "sighting";
    }

    @ApiIgnore
    @RequestMapping(value = "/d", method = RequestMethod.GET)
    public String special() {
        return "special";
    }
}