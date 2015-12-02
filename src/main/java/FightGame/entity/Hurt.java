package FightGame.entity;

/**
 * Created by gang.qin on 2015/12/1.
 */
public class Hurt {
    private int hurtBlood;
    private boolean unableAttack;
    private String hurtName;
    private String featurName;
    private int featurRemainder;

    public Hurt(int hurtBlood, String hurtName, String featurName, int featurRemainder) {
        this.hurtBlood = hurtBlood;
        this.hurtName = hurtName;
        this.featurName = featurName;
        this.featurRemainder = featurRemainder;
    }

    public int getHurtBlood() {
        return hurtBlood;
    }

    public void setHurtBlood(int hurtBlood) {
        this.hurtBlood = hurtBlood;
    }

    public boolean isUnableAttack() {
        return unableAttack;
    }

    public void setUnableAttack(boolean unableAttack) {
        this.unableAttack = unableAttack;
    }

    public String getHurtName() {
        return hurtName;
    }

    public void setHurtName(String hurtName) {
        this.hurtName = hurtName;
    }

    public String getFeaturName() {
        return featurName;
    }

    public void setFeaturName(String featurName) {
        this.featurName = featurName;
    }

    public int getFeaturRemainder() {
        return featurRemainder;
    }

    public void setFeaturRemainder(int featurRemainder) {
        this.featurRemainder = featurRemainder;
    }
}
