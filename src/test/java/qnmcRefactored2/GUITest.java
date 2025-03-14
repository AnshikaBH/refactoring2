package qnmcRefactored2;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import java.awt.event.ActionListener;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GUITest {
    private GUI mockView;
    private JButton mockNextButton;
    private JButton mockCalculateButton;
    private JTextArea mockTextArea;

    @Before
    public void setUp() {
        mockView = mock(GUI.class);
        mockNextButton = mock(JButton.class);
        mockCalculateButton = mock(JButton.class);
        mockTextArea = mock(JTextArea.class);
    }

    @Test
    public void testSetNextButtonActionListener() {
        ActionListener listener = mock(ActionListener.class);
        mockView.setNextButtonActionListener(listener);
        verify(mockNextButton).addActionListener(listener);
    }

    @Test
    public void testSetCalculateButtonActionListener() {
        ActionListener listener = mock(ActionListener.class);
        mockView.setCalculateButtonActionListener(listener);
        verify(mockCalculateButton).addActionListener(listener);
    }

    @Test
    public void testGetMintermInput() {
        when(mockView.getMintermInput()).thenReturn("5");
        String input = mockView.getMintermInput();
        assertEquals("5", input);
    }

    @Test
    public void testUpdateResult() {
        String result = "Simplified Boolean Expression";
        mockView.updateResult(result);
        verify(mockTextArea).setText(result);
    }

    @Test
    public void testValidateInput() {
        // Set up test data for the validateInput method
        when(mockView.getMintermInput()).thenReturn("5");

        // Call validateInput and check expected behavior
        mockView.validateInput();

        // Check that the input is validated (mock behavior for validation)
        verify(mockView).validateInput();
    }

    @Test
    public void testIsInputValid() {
        boolean invalidFor3Bits = mockView.isInputValid(3, 8);
        assertFalse(invalidFor3Bits);
    }

    @Test
    public void testDataThree() {
        // Test for the dataThree method
        String result = mockView.dataThree("5");

        // Assert the expected result
        assertEquals("101", result);  // Assuming 5 -> 101 in binary
    }

    @Test
    public void testDataFour() {
        // Test for the dataFour method
        String result = mockView.dataFour("10");

        // Assert the expected result
        assertEquals("1010", result);  // Assuming 10 -> 1010 in binary
    }

    @Test
    public void testDataFive() {
        // Test for the dataFive method
        String result = mockView.dataFive("18");

        // Assert the expected result
        assertEquals("10010", result);  // Assuming 18 -> 10010 in binary
    }

    @Test
    public void testPrimeImplicants() {
        // Example prime implicants with underscores
        String expectedResult = "_00_\n0__0\n1--1";

        // Call the method that updates the result in your GUI
        mockView.updateResult(expectedResult);

        // Verify that the expected result is set in the JTextArea mock
        verify(mockTextArea).setText(expectedResult);  // Ensures the correct result is set
    }

}