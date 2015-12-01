package FightGame;

/**
 * Created by gang.qin on 2015/12/1.
 * 玩家信息
 */
public class Player {
    private int bloodValue; // 血值 不能为负值
    private int atk; // 攻击力 大于0
    private int defense; // 防御力
    private String name; // 名字

    public Player(String name, int bloodValue, int atk, int defense) {
        this.bloodValue = bloodValue;
        this.atk = atk;
        this.defense = defense;
        this.name = name;
    }

    public int getBloodValue() {
        return bloodValue;
    }

    public void setBloodValue(int bloodValue) {
        this.bloodValue = bloodValue < 0 ? 0 : bloodValue;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk < 0 ? 1 : atk;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
