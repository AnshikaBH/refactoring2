package qnmcRefactored2;

import java.util.Set;
import java.util.TreeSet;

public class GetMintermList {
    private static final Set<String> set = new TreeSet<>(); // sorts strings, static = shared across all instances in this class

    public void setMinList(String x){
        set.add(x);
    }
    public static Set<String> getMin(){
        return set;
    }
}