import java.util.Vector;

public class Id {
    private static final String keyword[]={ "int","short","long","double","float","String","private","final",
            "protect","public","class","static","boolean","if","else","switch","case","break","continue",
            "byte","extends","implements","interface","void","return","super","this","try","catch","throw",
            "throws","while","new"};
    public static boolean isKeyword(String s){
        for (String string:keyword) {
            if (s.equals(string))
                return true;
        }
        return false;
    }

}
