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
            if (isDead(a)) {
                return a;
            }
            a.setBloodValue(a.getBloodValue() - b.getAtk()); // b 攻击 a
            if (isDead(b)) {
                return b;
            }
        }
    }

    public static boolean isDead(Player p) {
        return p.getBloodValue() <= 0;
    }
}
