package fr.esgi.rent.servlets;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RentalPropertiesConsultationServletTest {

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private HttpServletResponse httpServletResponse;

    @Test
    void shouldDoGet() throws IOException {
        RentalPropertiesConsultationServlet rentalPropertiesConsultationServlet = new RentalPropertiesConsultationServlet();

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        when(httpServletResponse.getWriter()).thenReturn(printWriter);

        rentalPropertiesConsultationServlet.doGet(httpServletRequest, httpServletResponse);

        String responseContent = stringWriter.toString();

        assertTrue(responseContent.contains("<h1>Liste des locations</h1>"));
        assertTrue(responseContent.contains("<ul>"));
        assertTrue(responseContent.contains("<li>Appartement à louer</li>"));
        assertTrue(responseContent.contains("<li>Description : Appartement spacieux avec vue sur l'ESGI</li>"));
        assertTrue(responseContent.contains("<li>Loyer : 750.90 €</li>"));
        assertTrue(responseContent.contains("<li>Caution : 1200.90 €</li>"));
        assertTrue(responseContent.contains("<li>Surface : 37.48 m²</li>"));
        assertTrue(responseContent.contains("</ul>"));
        assertTrue(responseContent.contains("<li>Maison à louer</li>"));
        assertTrue(responseContent.contains("<li>Description : Maison à louer dans banlieue calme et proche du RER</li>"));
        assertTrue(responseContent.contains("<li>Loyer : 1050.90 €</li>"));
        assertTrue(responseContent.contains("<li>Caution : 1400.90 €</li>"));
        assertTrue(responseContent.contains("<li>Surface : 62.50 m²</li>"));
        assertTrue(responseContent.contains("</ul>"));

        verifyNoInteractions(httpServletRequest);
        verifyNoMoreInteractions(httpServletResponse);
    }

}
