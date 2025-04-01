package fr.esgi.rent.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InvalidParameterErrorServletTest {

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private HttpServletResponse httpServletResponse;

    @Test
    void shouldDoGet() throws ServletException, IOException {
        String jspPath = "/invalid-parameter.jsp";

        InvalidParameterErrorServlet invalidParameterErrorServlet = new InvalidParameterErrorServlet();

        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

        when(httpServletRequest.getRequestDispatcher(jspPath)).thenReturn(requestDispatcher);

        invalidParameterErrorServlet.doGet(httpServletRequest, httpServletResponse);

        verify(httpServletRequest).getRequestDispatcher(jspPath);
        verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);

        verifyNoMoreInteractions(httpServletRequest, httpServletResponse, requestDispatcher);
    }

}
