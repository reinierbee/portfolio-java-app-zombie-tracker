package zombies.domain;

import java.util.Date;

public class Sight {
    float lat;
    float lng;
    String description;
    Date time;
    Zombie zombie;

    public Sight(){

    }

    public Sight(float lat, float lng, String description, Date time, Zombie zombie) {
        this.lat = lat;
        this.lng = lng;
        this.description = description;
        this.time = time;
        this.zombie = zombie;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Zombie getZombie() {
        return zombie;
    }

    public void setZombie(Zombie zombie) {
        this.zombie = zombie;
    }
}
