package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class ApiErrorTest {

    private RestTemplate restTemplate = Mockito.mock(RestTemplate.class);

    @Test
    public void testConnectionTimeout() {
        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(String.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.REQUEST_TIMEOUT));

        Exception exception = assertThrows(HttpClientErrorException.class, () -> {
            restTemplate.getForObject("http://example.com/api/transaction", String.class);
        });

        assertEquals(HttpStatus.REQUEST_TIMEOUT, ((HttpClientErrorException) exception).getStatusCode());
    }

    @Test
    public void testAuthenticationFailed() {
        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(String.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.UNAUTHORIZED));

        Exception exception = assertThrows(HttpClientErrorException.class, () -> {
            restTemplate.getForObject("http://example.com/api/transaction", String.class);
        });

        assertEquals(HttpStatus.UNAUTHORIZED, ((HttpClientErrorException) exception).getStatusCode());
    }

    @Test
    public void testInvalidInputParameter() {
        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(String.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));

        Exception exception = assertThrows(HttpClientErrorException.class, () -> {
            restTemplate.getForObject("http://example.com/api/transaction?param=invalid", String.class);
        });

        assertEquals(HttpStatus.BAD_REQUEST, ((HttpClientErrorException) exception).getStatusCode());
    }

    @Test
    public void testResourceNotFound() {
        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(String.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        Exception exception = assertThrows(HttpClientErrorException.class, () -> {
            restTemplate.getForObject("http://example.com/api/nonexistent", String.class);
        });

        assertEquals(HttpStatus.NOT_FOUND, ((HttpClientErrorException) exception).getStatusCode());
    }

    @Test
    public void testInternalServerError() {
        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(String.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR));

        Exception exception = assertThrows(HttpClientErrorException.class, () -> {
            restTemplate.getForObject("http://example.com/api/transaction", String.class);
        });

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, ((HttpClientErrorException) exception).getStatusCode());
    }

    @Test
    public void testConnectionTimeoutFails() {
        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(String.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.REQUEST_TIMEOUT));

        Exception exception = assertThrows(HttpClientErrorException.class, () -> {
            restTemplate.getForObject("http://example.com/api/transaction", String.class);
        });

        // This assertion is incorrect—it expects OK status instead of REQUEST_TIMEOUT, so this test will fail
        assertEquals(HttpStatus.OK, ((HttpClientErrorException) exception).getStatusCode());
    }

    @Test
    public void testAuthenticationFailedWrongExpectation() {
        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(String.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.UNAUTHORIZED));

        Exception exception = assertThrows(HttpClientErrorException.class, () -> {
            restTemplate.getForObject("http://example.com/api/transaction", String.class);
        });

        // This assertion is incorrect—expects FORBIDDEN instead of UNAUTHORIZED, so this test will fail
        assertEquals(HttpStatus.FORBIDDEN, ((HttpClientErrorException) exception).getStatusCode());
    }

    @Test
    public void testInvalidInputParameterWrongHandling() {
        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(String.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));

        try {
            restTemplate.getForObject("http://example.com/api/transaction?param=invalid", String.class);
            fail("Expected HttpClientErrorException");
        } catch (Exception e) {
            // This incorrect handling will fail the test—wrong exception type is expected
            assertTrue(e instanceof NullPointerException);
        }
    }

    @Test
    public void testResourceNotFoundWrongURL() {
        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(String.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        Exception exception = assertThrows(HttpClientErrorException.class, () -> {
            restTemplate.getForObject("http://example.com/api/existing-resource", String.class);
        });

        // The test fails because the requested resource actually exists, making the assertion incorrect
        assertEquals(HttpStatus.NOT_FOUND, ((HttpClientErrorException) exception).getStatusCode());
    }

    @Test
    public void testInternalServerErrorWithSuccess() {
        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(String.class)))
                .thenReturn("Successful response");

        Exception exception = assertThrows(HttpClientErrorException.class, () -> {
            restTemplate.getForObject("http://example.com/api/transaction", String.class);
        });

        // This test fails because no exception occurs—response is successful instead
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, ((HttpClientErrorException) exception).getStatusCode());
    }

}
