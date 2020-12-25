package bd.edu.seu.ums.Helper;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) throws ParseException {
//       String s =  TimeConverter.convert24h("01:30 PM");
//        System.out.println(s);
//        String s1 =  TimeConverter.convert12h("13:30:00");
//        System.out.println(s1);

        List<String> s = Arrays.asList("ab","aa","ac");
        List<String> s1 = Arrays.asList("");

        long a = s.stream().filter(s1::contains).count();
        System.out.println(a);
    }
}
