package FightGame.equipment;

/**
 * Created by gang.qin on 2015/12/2.
 */
public class Armor {
    private String name;
    private int defenseVaule;

    public Armor(String name, int defenseVaule) {
        this.name = name;
        this.defenseVaule = defenseVaule;
    }

    public String useArmor () {
        return String.format( "用%s防御", name );
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
