package test;

/**
 * Created by gang.qin  on 2015/9/17.
 */
public class Child extends Pepole {
    Pepole father;

    public Child(String name) {
        System.out.print(3);
        this.name = name;
        father = new Pepole(name + ":F");
    }

    public  Child() {
        System.out.print(4);
    }
}
