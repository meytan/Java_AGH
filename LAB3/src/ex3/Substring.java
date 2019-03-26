package ex3;

public class Substring {
    public static int substring(String a, String b){


        for(int i = 1; true;i++,a+=a){
            if(a.contains(b))
                return i;
            if(a.length() > 2 * b.length())
                return -1;
        }
    }
}
