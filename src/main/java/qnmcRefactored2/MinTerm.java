package qnmcRefactored2;

public class MinTerm {
    // input data representation
    public static final char NOT_CH = '0';
    public static final char SET_CH = '1';
    public static final char ANY_CH = '_';
    // internal data representation
    protected static final int NOT = 0;
    protected static final int SET = 1;
    protected static final int ANY = -1;
    // attribute
    protected int count;
    private final int[] term;

    // constructing & reading
    public MinTerm(String str) {
        term = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            switch (str.charAt(i)) {
                case NOT_CH:
                    term[count++] = NOT;
                    break;
                case SET_CH:
                    term[count++] = SET;
                    break;
                case ANY_CH:
                    term[count++] = ANY;
                    break;
            }
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder(count);
        for (int i = 0; i < count; i++) {
            switch (term[i]) {
                case NOT:
                    builder.append(NOT_CH);
                    break;
                case SET:
                    builder.append(SET_CH);
                    break;
                case ANY:
                    builder.append(ANY_CH);
                    break;
            }
        }
        return builder.toString();
    }

    public boolean isSame(MinTerm a) throws ExceptionQuine {
        if (count != a.count)
            throw new ExceptionQuine("MinTerm length mismatch in isSame()");
        for (int i = 0; i < count; i++) {
            if (term[i] != a.term[i])
                return false;
        }
        return true;
    }

    // number of the difference
    public int resolutionCount(MinTerm a) throws ExceptionQuine {
        if (count != a.count)
            throw new ExceptionQuine("\"MinTerm length mismatch in resolutionCount()");
        int resCount = 0;
        for (int i = 0; i < count; i++) {
            if (term[i] != a.term[i])
                resCount++;
        }
        return resCount;
    }

    public static MinTerm combine(MinTerm a, MinTerm b) throws ExceptionQuine {
        if (a.count != b.count)
            throw new ExceptionQuine("Minterms must have the same length to combine.");
        StringBuilder builder = new StringBuilder(a.count);
        for (int i = 0; i < a.count; i++) {
            if (a.term[i] != b.term[i])
                builder.append(ANY_CH);
            else
                builder.append(a.toString().charAt(i));
        }
        return new MinTerm(builder.toString());
    }
}

