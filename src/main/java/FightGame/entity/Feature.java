package FightGame.entity;

/**
 * Created by gang.qin on 2015/12/1.
 * 特性
 */
public class Feature {
    private String name;
    private int type;
    private int total;
    private int remainder;
    private boolean canHurt;
    private boolean visibleRemindTime;

    public Feature(String name, int type, int total, int remainder, boolean canHurt, boolean visibleRemindTime) {
        this.name = name;
        this.type = type;
        this.total = total;
        this.remainder = remainder;
        this.canHurt = canHurt;
        this.visibleRemindTime = visibleRemindTime;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getRemainder() {
        return remainder;
    }

    public void setRemainder(int remainder) {
        this.remainder = remainder;
    }

    public boolean isCanHurt() {
        return canHurt;
    }

    public void setCanHurt(boolean canHurt) {
        this.canHurt = canHurt;
    }

    public boolean isVisibleRemindTime() {
        return visibleRemindTime;
    }

    public void setVisibleRemindTime(boolean visibleRemindTime) {
        this.visibleRemindTime = visibleRemindTime;
    }
}
