package FightGame;

import FightGame.entity.Defense;
import FightGame.equipment.Armor;
import FightGame.role.Player;
import FightGame.equipment.Weapon;
import FightGame.role.Soldier;
import FightGame.util.Constant;

/**
 * Created by gang.qin on 2015/12/1.
 */
public class GameMain {

    public static void main (String[] args) throws Exception{
        Soldier a = new Soldier("李四", 100, 10, Constant.ROLE_SOLDIER);
        Player b = new Player("张三", 120, 12, Constant.ROLE_CIVILIANS);

        a.setWeapon(new Weapon(Constant.WEAPON_STICKS, 5));
        a.setArmor(new Armor(Constant.ARMOR_ARMOR, 2));

        GameMain game = new GameMain();
        Player dead = game.fight(a, b);
        System.out.println(dead.getName() + "被打败了");
    }

    public Player fight(Player a, Player b) {
        while (true) {
            a.attack(b); // a 攻击 b
            System.out.println(a.getFightStr(b));
            if (b.isDead()) {
                return b;
            }
            b.attack(a); // b 攻击 a
            System.out.println(b.getFightStr(a));
            if (a.isDead()) {
                return a;
            }
        }
    }


}
