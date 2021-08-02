import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class test{
    String name;
    public test(String name){
        this.name = name;
    }
    static void swap(String s1, String s2){
        String temp = s1;
        s1=s2;
        s2=temp;
        System.out.println(temp);
        System.out.println(s1);
        System.out.println(s2);
    }
    public static void main(String[] args) {
        test t1 = new test("t1");
        test t2 = new test("t2");
        test temp = t1;
        t1 = t2;
        t2 = temp;
        System.out.println(temp.name);
        System.out.println(t1.name);
        System.out.println(t2.name);
        String s1 = "Hello";
        String s2 = "Hello";
        String s3 = new String("Hello");
        System.out.println(s1==s3);
        System.out.println(s1== s3.intern());
        System.out.println(s3 == s1);
    }
}
