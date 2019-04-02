package ex3;

public class Substring {
    public static int substring(String a, String b){
        if(a.equals("")) throw new IllegalArgumentException("String can't be empty");

        StringBuilder stringBuilder = new StringBuilder(a);
        for(int i = 1; true;i++, stringBuilder.append(a)){
            if(stringBuilder.toString().contains(b))
                return i;
            if(stringBuilder.length() > 2 * b.length())
                return -1;
        }
    }
}
