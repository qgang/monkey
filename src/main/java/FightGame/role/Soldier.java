package FightGame.role;

import FightGame.equipment.Armor;
import FightGame.equipment.Weapon;

/**
 * Created by gang.qin on 2015/12/2.
 */
public class Soldier extends Player{
    private Weapon weapon;
    private Armor armor;

    public Soldier(String name, int bloodValue, int atk, String role) {
        super(name, bloodValue, atk, role);
    }

    public void attack (Player victim) {
        int hurt = 0;
        hurt += atk; // 加攻击
        hurt += weapon == null ? 0 : weapon.getAtk(); // 加攻击
        // 减防御
        if (victim instanceof Soldier) {
            Soldier s = (Soldier) victim;
            hurt -= s.getArmor() == null ? 0 : s.getArmor().getDefenseVaule();
        }
        victim.setBeHurtValue(hurt);
        victim.setBloodValue(victim.getBloodValue() - hurt);
    }

    public String getFightStr (Player victim) {
        StringBuffer sb = new StringBuffer();
        sb.append(role + name);
        sb.append(weapon == null ? "" : "用" + weapon.getName());
        sb.append(String.format("攻击了%s%s,", victim.role, victim.getName()));
        if (victim instanceof Soldier) {
            Soldier s = (Soldier)victim;
            if (s.getArmor() != null) {
                sb.append(String.format("%s用%s防御了%s,", s.getName(), s.getArmor().getName(), name));
            }
        }
        sb.append(String.format("%s受到了%d点伤害，%s剩余生命：%d", name, beHurtValue, victim.getName(),bloodValue));
        return sb.toString();
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }
}
