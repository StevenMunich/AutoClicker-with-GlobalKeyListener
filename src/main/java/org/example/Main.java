package org.example;


class Cat{
    public void meow(){
        System.out.println("meow");
    }
    public int age;
    public String name;
}//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.



public class Main {
    private static void passCat(Cat cat1){
        Cat cat = new Cat();
        cat.age = cat1.age;
        cat.name = "newName";
        cat.meow();
    } private static void finalPassCat(final Cat cat){
        cat.age = 10;
        cat.name = "newName";
        cat.meow();
    }

    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        double s = 3.14;
        test(s);
        System.out.println(s);
        test2(s);
        System.out.println(s);

        Cat seagull = new Cat();
        seagull.age = 5;
        seagull.name = "seagull";
        passCat(seagull);
        System.out.println("after modifcation: " + seagull.name);
        finalPassCat(seagull);
        System.out.println("after modifcation: " + seagull.name);

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }//end loop
    }//end main()

    private static void test(double s) {
        s= 3.33;
        System.out.println(s);
    }
    private static void test2(final double s) {
       // s = 2.22;
        System.out.println(s);
    }
}//End Class