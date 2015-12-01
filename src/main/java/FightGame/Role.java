package FightGame;

/**
 * Created by gang.qin on 2015/12/1.
 */
public class Role {
    private String name;
    private int type;

    public Role (String name, int type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
