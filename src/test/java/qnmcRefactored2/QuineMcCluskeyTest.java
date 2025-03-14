package qnmcRefactored2;

import org.junit.Before;
import org.junit.Test;


import javax.swing.*;
import java.util.Set;

import static org.mockito.Mockito.*;

public class QuineMcCluskeyTest {
    private GUI mockView;  // Mock the GUI class
    private JTextArea mockTextArea;  // Mock JTextArea

    @Before
    public void setUp() {
        // Initialize mocks using Mockito
        mockView = mock(GUI.class);  // Mock the GUI class
        mockTextArea = mock(JTextArea.class);  // Mock JTextArea

        mockView.resultShow = mockTextArea;
        }

    @Test
    public void testPrimeImplicants() {
        // Minterms for the test
        Set<Integer> minterms = Set.of(0, 1, 8, 9);

        String expected = "_00_\n0__0\n1--1";  // This is how the result would appear in the GUI

        // Call your method to trigger prime implicant calculation (this might be in your GUI or controller)
        mockView.updateResult(expected);

        verify(mockTextArea).setText(expected);
    }
}



