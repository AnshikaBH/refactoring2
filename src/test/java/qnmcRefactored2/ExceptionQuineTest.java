package qnmcRefactored2;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExceptionQuineTest {
    @Test
    public void testExceptionQuineWithMessage() {
        String message = "Test message";
        ExceptionQuine exception = new ExceptionQuine(message);
        assertEquals("Test message", exception.getMessage());
    }

    @Test
    public void testExceptionQuineWithMessageAndCause() {
        String message = "Test message";
        Throwable cause = new Throwable("Cause of exception");
        ExceptionQuine exception = new ExceptionQuine(message, cause);

        assertEquals("Test message", exception.getMessage());
        assertEquals("Cause of exception", exception.getCause().getMessage());
    }

    @Test
    public void testExceptionQuineWithCause() {
        Throwable cause = new Throwable("Cause of exception");
        ExceptionQuine exception = new ExceptionQuine(cause);
        assertNull(exception.getMessage());
        assertEquals("Cause of exception", exception.getCause().getMessage());
    }

}