package FightGame;

/**
 * Created by gang.qin on 2015/12/1.
 */
public class Defense {
    private String name;
    private int defenseVaule;

    public Defense(String name, int defenseVaule) {
        this.name = name;
        this.defenseVaule = defenseVaule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDefenseVaule() {
        return defenseVaule;
    }

    public void setDefenseVaule(int defenseVaule) {
        this.defenseVaule = defenseVaule;
    }
}
