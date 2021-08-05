import java.lang.reflect.Array;
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
        ArrayList<String> list = new ArrayList<>();
        list.add("k");
        list.add("quang");
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(list);
    }
}
