package FightGame.equipment;

import FightGame.entity.Feature;

/**
 * Created by gang.qin on 2015/12/1.
 * 武器
 */
public class Weapon {
    private String name;
    private int atk;
    private Feature feature;

    public Weapon(String name, int atk) {
        this.name = name;
        this.atk = atk;
    }

    public String useWeapon() {
        return "用" + name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }
}
