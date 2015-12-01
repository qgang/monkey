package FightGame;

/**
 * Created by gang.qin on 2015/12/1.
 */
public class Weapon {
    private String name;
    private int atk;

    public Weapon(String name, int atk) {
        this.name = name;
        this.atk = atk;
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
}
