package javaTester;

public class Topic_02_Primitive_Reference {
    int x;

    public static void main(String[] args) {
        int x = 42;
        int y = x;
        System.out.println("X = " + x);
        System.out.println("Y = " + y);

        x = 50;
        System.out.println("X = " + x);
        System.out.println("Y = " + y);

        //class
        // Instance 1
        Topic_02_Primitive_Reference c = new Topic_02_Primitive_Reference();
        c.x = 42;
        System.out.println(c.x);

        // Instance 2
        Topic_02_Primitive_Reference d = c;
        System.out.println(d.x);

        System.out.println(c.x);
        System.out.println(d.x);
    }
}
