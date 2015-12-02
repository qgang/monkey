package FightGame.role;

/**
 * Created by gang.qin on 2015/12/1.
 * 玩家
 */
public class Player {
    protected int bloodValue; // 血值
    protected int atk; // 攻击力 大于0
    protected String name; // 名字
    protected int beHurtValue; // 被伤害
    protected String role; // 角色

    public Player () {}
    public Player(String name, int bloodValue, int atk, String role) {
        this.bloodValue = bloodValue;
        this.atk = atk;
        this.name = name;
        this.role = role;
    }


    public void attack (Player victim) {
        victim.setBeHurtValue(atk);
        victim.setBloodValue(victim.getBloodValue() - atk);
    }

    public boolean isDead() {
        return bloodValue <= 0;
    }

    public String getFightStr (Player victim) {
        return String.format("%s攻击了%s,%s受到了%d点伤害，%s剩余生命：%d",
                name,victim.getName(),victim.getName(),atk,victim.getName());
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBeHurtValue() {
        return beHurtValue;
    }

    public void setBeHurtValue(int beHurtValue) {
        this.beHurtValue = beHurtValue;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
