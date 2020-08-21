public class Demo1 {
    public static void main(String[] args) {
        int a = 1;
        double c = 0.4;
        double b = 0.1;
        System.out.println(c - b);
        System.out.println(b + c);


        String str = "fuck you";
        String newStr = str.replace("fuck", "***");
        System.out.println(newStr);

        StringBuilder abc = new StringBuilder("abc");
        abc.append(new Demo1());
        System.out.println(abc.toString());


    }
}
