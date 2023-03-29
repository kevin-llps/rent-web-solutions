package fr.esgi.rent.servlets;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RentalPropertiesConsultationServletTest {

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private HttpServletResponse httpServletResponse;

    @Mock
    private PrintWriter printWriter;

    @Test
    void shouldDoGet() throws IOException {
        RentalPropertiesConsultationServlet rentalPropertiesConsultationServlet = new RentalPropertiesConsultationServlet();

        when(httpServletResponse.getWriter()).thenReturn(printWriter);

        rentalPropertiesConsultationServlet.doGet(httpServletRequest, httpServletResponse);

        verify(printWriter).println("<h1>Liste des locations</h1>");

        verify(printWriter, times(2)).println("<ul>");
        verify(printWriter).println("<li>Appartement à louer</li>");
        verify(printWriter).println("<li>Description : Appartement spacieux avec vue sur l'ESGI</li>");
        verify(printWriter).println("<li>Loyer : 750.90 €</li>");
        verify(printWriter).println("<li>Caution : 1200.90 €</li>");
        verify(printWriter).println("<li>Surface : 37.48 m²</li>");
        verify(printWriter, times(2)).println("</ul>");

        verify(printWriter).println("<li>Maison à louer</li>");
        verify(printWriter).println("<li>Description : Maison à louer dans banlieue calme et proche du RER</li>");
        verify(printWriter).println("<li>Loyer : 1050.90 €</li>");
        verify(printWriter).println("<li>Caution : 1400.90 €</li>");
        verify(printWriter).println("<li>Surface : 62.50 m²</li>");

        verify(printWriter).close();

        verifyNoInteractions(httpServletRequest);
        verifyNoMoreInteractions(httpServletResponse, printWriter);
    }

}
