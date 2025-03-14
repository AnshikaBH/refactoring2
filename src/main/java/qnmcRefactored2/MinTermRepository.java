package qnmcRefactored2;

import java.util.Set;
import java.util.TreeSet;

public class MinTermRepository {
    private static final Set<String> set = new TreeSet<>();

    private static final MinTermRepository instance = new MinTermRepository();

    private MinTermRepository() {}

    public static MinTermRepository getInstance() {
        return instance;
    }
    public void setMinList(String minterm){
        set.add(minterm);
    }
    public static Set<String> getMin(){
        return set;
    }
}