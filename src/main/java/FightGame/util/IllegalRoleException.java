package FightGame.util;

/**
 * Created by gang.qin on 2015/12/1.
 */
public class IllegalRoleException extends Exception{

    public IllegalRoleException (String msg) {
        super(msg);
    }

    public IllegalRoleException (String msg, Exception e) {
        super(msg, e);
    }
}
