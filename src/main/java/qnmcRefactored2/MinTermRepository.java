package qnmcRefactored2;

import java.util.Set;
import java.util.TreeSet;

public class MinTermRepository {
    private static final Set<String> set = new TreeSet<>(); // sorts strings, static = shared across all instances in this class

    // Private static instance of the MinTermRepository (Singleton)
    private static final MinTermRepository instance = new MinTermRepository();

    // Private constructor to prevent instantiation from other classes
    private MinTermRepository() {}

    // Public method to access the Singleton instance
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