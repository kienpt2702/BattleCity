import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class test{
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            Integer num = iterator.next();
            System.out.println(num);
            iterator.remove();
        }
        Stack stack = new Stack();
        stack.pop();
    }

}
