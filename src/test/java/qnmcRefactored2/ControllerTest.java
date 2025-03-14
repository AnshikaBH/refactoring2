package qnmcRefactored2;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;

import java.util.Set;
import static org.mockito.Mockito.*;

public class ControllerTest {
    private GUI mockView;
    private MinTermRepository mockModel;
    private Controller controller;

    @Before
    public void setUp() {
        mockView = mock(GUI.class);
        mockModel = mock(MinTermRepository.class);
        controller = new Controller(mockView, mockModel);
    }

    @Test
    public void testHandleNextButtonClick() {
        String input = "5";
        when(mockView.getMintermInput()).thenReturn(input);
        controller.handleNextButtonClick();

        verify(mockModel).setMinList(input);
    }

    @Test
    public void testHandleCalculateButtonClick() {
        Set<String> mockMinterms = Set.of("1", "2", "3");
        try (MockedStatic<MinTermRepository> mockedRepo = mockStatic(MinTermRepository.class)) {
            mockedRepo.when(MinTermRepository::getMin).thenReturn(mockMinterms);

            controller.handleCalculateButtonClick();
            mockedRepo.verify(MinTermRepository::getMin);
            verify(mockView).updateResult(anyString());
        }
    }
}