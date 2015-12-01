package FightGame;

/**
 * Created by gang.qin on 2015/12/1.
 */
public class GameMain {

    public static void main (String[] args) {
        Player a = new Player("李四", 100, 10, 0);
        Player b = new Player("张三", 120, 12, 0);
        Player dead = fight(a, b);
        System.out.println(dead.getName() + "被打败了");
    }

    public static Player fight(Player a, Player b) {
        while (true) {
            b.setBloodValue(b.getBloodValue() - a.getAtk()); // a 攻击 b
            printFight(a, b);
            if (isDead(b)) {
                return b;
            }
            a.setBloodValue(a.getBloodValue() - b.getAtk()); // b 攻击 a
            printFight(b, a);
            if (isDead(a)) {
                return a;
            }
        }
    }

    public static boolean isDead(Player p) {
        return p.getBloodValue() <= 0;
    }

    public static void printFight (Player attacker, Player victim) {
        System.out.println(attacker.getName() + "攻击了" + victim.getName() + ","
                         + victim.getName() + "受到了" + attacker.getAtk() + "点伤害" + ","
                         + victim.getName() + "剩余生命：" + victim.getBloodValue());
    }
}
