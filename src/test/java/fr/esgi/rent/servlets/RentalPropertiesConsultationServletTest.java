package fr.esgi.rent.servlets;

import fr.esgi.rent.beans.RentalProperty;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static fr.esgi.rent.samples.RentalPropertySample.rentalProperties;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RentalPropertiesConsultationServletTest {

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private HttpServletResponse httpServletResponse;

    @Test
    void shouldDoGet() throws ServletException, IOException {
        String jspPath = "/rentalProperties.jsp";
        List<RentalProperty> rentalProperties = rentalProperties();

        RentalPropertiesConsultationServlet rentalPropertiesConsultationServlet = new RentalPropertiesConsultationServlet();

        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

        when(httpServletRequest.getRequestDispatcher(jspPath)).thenReturn(requestDispatcher);

        rentalPropertiesConsultationServlet.doGet(httpServletRequest, httpServletResponse);

        verify(httpServletRequest).setAttribute("rentalProperties", rentalProperties);
        verify(httpServletRequest).getRequestDispatcher(jspPath);
        verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);

        verifyNoMoreInteractions(httpServletRequest, httpServletResponse, requestDispatcher);
    }

}
