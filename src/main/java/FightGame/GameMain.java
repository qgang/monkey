package FightGame;

import FightGame.entity.Defense;
import FightGame.entity.Player;
import FightGame.entity.Role;
import FightGame.entity.Weapon;
import FightGame.util.Constant;

/**
 * Created by gang.qin on 2015/12/1.
 */
public class GameMain {

    public static void main (String[] args) throws Exception{
        Player a = new Player("李四", 100, 10);
        Player b = new Player("张三", 120, 12);

        a.setRole(new Role(Constant.ROLE_SOLDIER, Constant.ROLE_TYPE_SOLDIER));
        a.setWeapon(new Weapon(Constant.WEAPON_STICKS, 5));
        a.setDefense(new Defense(Constant.DEFENSE_ARMOR, 2));

        GameMain game = new GameMain();
        Player dead = game.fight(a, b);
        System.out.println(dead.getName() + "被打败了");
    }

    public Player fight(Player a, Player b) {
        while (true) {
            attack(a, b); // a 攻击 b
            printFight(a, b);
            if (isDead(b)) {
                return b;
            }
            attack(b, a); // b 攻击 a
            printFight(b, a);
            if (isDead(a)) {
                return a;
            }
        }
    }

    public void attack (Player attacker, Player victim) {
        int hurt = 0;
        hurt += attacker.getAtk(); // 加攻击
        hurt += attacker.getWeapon() != null ? attacker.getWeapon().getAtk(): 0; // 加武器
        hurt -= victim.getDefense() != null ? victim.getDefense().getDefenseVaule() : 0;// 减防御
        victim.setBeHurtValue(hurt);
        victim.setBloodValue(victim.getBloodValue() - hurt);
    }

    public boolean isDead(Player p) {
        return p.getBloodValue() <= 0;
    }

    public void printFight (Player attacker, Player victim) {
        StringBuffer sb = new StringBuffer();
        sb.append(attacker.getRole().getName() + attacker.getName());
        sb.append(attacker.getWeapon() == null ? "" : ("用" + attacker.getWeapon().getName()));
        sb.append("攻击了" + victim.getRole().getName() + victim.getName() + ",");

        sb.append(victim.getDefense() == null? "" : (victim.getName() + "用" + victim.getDefense().getName() + "防御了" + attacker.getName() + ","));

        sb.append(victim.getName() + "受到了" + attacker.getBeHurtValue() + "点伤害" + ",");
        sb.append(victim.getName() + "剩余生命：" + victim.getBloodValue());

        System.out.println(sb.toString());
    }
}
