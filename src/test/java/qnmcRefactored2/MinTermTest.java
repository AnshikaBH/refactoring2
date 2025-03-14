package qnmcRefactored2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MinTermTest {


    private MinTerm minTerm1;
    private MinTerm minTerm2;
    private MinTerm minTerm3;

    @Before
    public void setUp() {
        minTerm1 = new MinTerm("101");
        minTerm2 = new MinTerm("101");
        minTerm3 = new MinTerm("100");
    }

    @Test
    public void testToString() {
        assertEquals("101", minTerm1.toString());
    }

    @Test
    public void testIsSameEqual() throws ExceptionQuine {
        assertTrue(minTerm1.isSame(minTerm2));
    }

    @Test
    public void testIsSameNotEqual() throws ExceptionQuine {
        assertFalse(minTerm1.isSame(minTerm3));
    }

    @Test(expected = ExceptionQuine.class)
    public void testIsSameException() throws ExceptionQuine {
        MinTerm minTerm4 = new MinTerm("10101");
        minTerm1.isSame(minTerm4);
    }

    @Test
    public void testResolutionCount() throws ExceptionQuine {
        assertEquals(1, minTerm1.resolutionCount(minTerm3));
    }

    @Test(expected = ExceptionQuine.class)
    public void testResolutionCountException() throws ExceptionQuine {
        MinTerm minTerm4 = new MinTerm("10101");
        minTerm1.resolutionCount(minTerm4);
    }

    @Test
    public void testCombine() throws ExceptionQuine {
        MinTerm combined = MinTerm.combine(minTerm1, minTerm2);
        assertEquals("101", combined.toString());

        MinTerm combinedDifferent = MinTerm.combine(minTerm1, minTerm3);
        assertEquals("10_", combinedDifferent.toString());
    }

    @Test(expected = ExceptionQuine.class)
    public void testCombineException() throws ExceptionQuine {
        MinTerm minTerm4 = new MinTerm("10101");
        MinTerm.combine(minTerm1, minTerm4);
    }
}