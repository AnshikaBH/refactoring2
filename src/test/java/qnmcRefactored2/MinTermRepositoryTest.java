package qnmcRefactored2;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;
public class MinTermRepositoryTest {
    @Test
    public void testSingletonInstance() {
        MinTermRepository instance1 = MinTermRepository.getInstance();
        MinTermRepository instance2 = MinTermRepository.getInstance();

        assertSame(instance1, instance2);
    }

    @Test
    public void testSetMinList() {
        MinTermRepository repository = MinTermRepository.getInstance();

        repository.setMinList("101");
        repository.setMinList("110");

        Set<String> minterms = MinTermRepository.getMin();
        assertTrue(minterms.contains("101"));
        assertTrue(minterms.contains("110"));

        repository.setMinList("101");
        assertEquals(2, minterms.size()); // "101" should not be added again
    }

    @Test
    public void testGetMin() {
        MinTermRepository repository = MinTermRepository.getInstance();

        repository.setMinList("101");
        repository.setMinList("110");

        Set<String> minterms = MinTermRepository.getMin();

        assertTrue(minterms.contains("101"));
        assertTrue(minterms.contains("110"));
    }
}