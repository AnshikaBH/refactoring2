package qnmcRefactored2;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class QuineTest {
    @Test
    public void testAddTerm() throws ExceptionQuine {
        Quine quine = new Quine();
        for (int i = 0; i < 255; i++) {
            quine.addTerm("111");
        }
        assertEquals(255, quine.count);

        Exception exception = assertThrows(ExceptionQuine.class, () -> {
            quine.addTerm("101");
        });

        assertEquals("Cannot add more terms, maximum limit reached", exception.getMessage());
    }

    @Test
    public void testToString() throws ExceptionQuine {
        Quine quine = new Quine();

        quine.addTerm("101");
        quine.addTerm("110");

        String expectedOutput = "101\n110\n";
        assertEquals(expectedOutput, quine.toString());
    }

    @Test
    public void testHasTerm() throws ExceptionQuine {
        Quine quine = new Quine();

        quine.addTerm("101");
        quine.addTerm("110");

        assertTrue(quine.hasTerm(new MinTerm("101")));
        assertTrue(quine.hasTerm(new MinTerm("110")));

        assertFalse(quine.hasTerm(new MinTerm("111")));
    }

    @Test
    public void testSimplify() throws ExceptionQuine {
        Quine quine = new Quine();
        quine.addTerm("101");
        quine.addTerm("111");
        quine.addTerm("001");
        quine.addTerm("011");

        quine.simplify();
        assertTrue(quine.count < 4);
    }
}