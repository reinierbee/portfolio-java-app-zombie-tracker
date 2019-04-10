package zombies.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Zombie {
    ZombieType type;
    int id;
    Gender gender;

    public enum Gender {
        MALE, FEMALE, TRANSGENDER
    }

    public enum ZombieType {
        RUNNER,CRAWLER,CONTAMINATED,STALKER,PUKER,SPITTER,EXPLODER,TANK,CLICKER;
    }

    public Zombie() {
    }

    public Zombie(int id) {
        this.id = id;
    }

    public Zombie(ZombieType type, Gender gender) {
        this.type = type;
        this.gender = gender;
    }

    public Zombie(int id, ZombieType type, Gender gender) {
        this.id = id;
        this.type = type;
        this.gender = gender;
    }

    public ZombieType getType() {
        return type;
    }

    public List<ZombieType> getTypeAsList(){
        return new ArrayList<ZombieType>(Arrays.asList(ZombieType.values()));
    }

    public void setType(ZombieType type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public Gender getGender() {
        return gender;
    }

    public List<Gender> getGenderAsList(){
        return new ArrayList<Gender>(Arrays.asList(Gender.values()));
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
