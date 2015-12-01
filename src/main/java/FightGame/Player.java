package FightGame;

/**
 * Created by gang.qin on 2015/12/1.
 * 玩家信息
 */
public class Player {
    private int bloodValue; // 血值
    private int beHurtValue; // 上次收到的伤害值
    private int atk; // 攻击力 大于0
    private String name; // 名字
    private Role role = new Role(Constant.ROLE_CIVILIANS,  Constant.ROLE_TYPE_CIVILIANS); // 默认为普通人
    private Weapon weapon;
    private Defense defense;

    public Player(String name, int bloodValue, int atk) {
        this.bloodValue = bloodValue;
        this.atk = atk;
        this.name = name;
    }

    public int getBloodValue() {
        return bloodValue;
    }

    public void setBloodValue(int bloodValue) {
        this.bloodValue = bloodValue < 0 ? 0 : bloodValue;
    }

    public int getBeHurtValue() {
        return beHurtValue;
    }

    public void setBeHurtValue(int beHurtValue) {
        this.beHurtValue = beHurtValue;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) throws IllegalRoleException{
        if (role != null && role.getType() == Constant.ROLE_TYPE_SOLDIER) {
            this.weapon = weapon;
        } else {
            throw new IllegalRoleException("role is not sodier");
        }
    }

    public Defense getDefense() {
        return defense;
    }

    public void setDefense (Defense defense) throws IllegalRoleException{
        if (role != null && role.getType() == Constant.ROLE_TYPE_SOLDIER) {
            this.defense = defense;
        } else {
            throw new IllegalRoleException("role is not sodier");
        }
    }
}
