package FightGame.equipment;

/**
 * Created by gang.qin on 2015/12/2.
 */
public class NoWeapon extends Weapon{
    private static NoWeapon Instance = new NoWeapon("", 0);

    private NoWeapon (String name, int damage) {
        super(name, damage);
    }

    public static NoWeapon getInstance() {
        return Instance;
    }

    public String useWeapon() {
        return "";
    }
}
