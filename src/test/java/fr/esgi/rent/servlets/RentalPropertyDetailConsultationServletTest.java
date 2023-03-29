package fr.esgi.rent.servlets;

import fr.esgi.rent.beans.RentalProperty;
import fr.esgi.rent.exception.MissingRentalPropertyId;
import fr.esgi.rent.exception.NotFoundRentalPropertyException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static fr.esgi.rent.samples.RentalPropertySample.oneRentalProperty;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RentalPropertyDetailConsultationServletTest {

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private HttpServletResponse httpServletResponse;

    @Test
    void shouldDoGet() throws ServletException, IOException {
        String jspPath = "/rentalPropertyDetail.jsp";
        RentalProperty rentalProperty = oneRentalProperty();

        RentalPropertyDetailConsultationServlet rentalPropertyDetailConsultationServlet = new RentalPropertyDetailConsultationServlet();

        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

        when(httpServletRequest.getParameter("id")).thenReturn("46890");
        when(httpServletRequest.getRequestDispatcher(jspPath)).thenReturn(requestDispatcher);

        rentalPropertyDetailConsultationServlet.doGet(httpServletRequest, httpServletResponse);

        verify(httpServletRequest).setAttribute("rentalProperty", rentalProperty);
        verify(httpServletRequest).getRequestDispatcher(jspPath);
        verify(requestDispatcher).forward(httpServletRequest, httpServletResponse);

        verifyNoMoreInteractions(httpServletRequest, httpServletResponse, requestDispatcher);
    }

    @Test
    void givenNullIdParamValue_shouldThrowMissingRentalPropertyId() {
        RentalPropertyDetailConsultationServlet rentalPropertyDetailConsultationServlet = new RentalPropertyDetailConsultationServlet();

        assertThatExceptionOfType(MissingRentalPropertyId.class)
                .isThrownBy(() -> rentalPropertyDetailConsultationServlet.doGet(httpServletRequest, httpServletResponse))
                .satisfies(e -> assertThat(e.getMessage()).isEqualTo("Parameter id is null"));

        verify(httpServletRequest).getParameter("id");

        verifyNoInteractions(httpServletResponse);
        verifyNoMoreInteractions(httpServletRequest);
    }

    @Test
    void givenIdParamValueNotParsableToInt_shouldThrowNumberFormatException() {
        RentalPropertyDetailConsultationServlet rentalPropertyDetailConsultationServlet = new RentalPropertyDetailConsultationServlet();

        when(httpServletRequest.getParameter("id")).thenReturn("toto");

        assertThatExceptionOfType(NumberFormatException.class)
                .isThrownBy(() -> rentalPropertyDetailConsultationServlet.doGet(httpServletRequest, httpServletResponse));

        verify(httpServletRequest).getParameter("id");

        verifyNoInteractions(httpServletResponse);
        verifyNoMoreInteractions(httpServletRequest);
    }

    @Test
    void givenNonExistentIdParamValue_shouldThrowNotFoundRentalPropertyException() {
        RentalPropertyDetailConsultationServlet rentalPropertyDetailConsultationServlet = new RentalPropertyDetailConsultationServlet();

        when(httpServletRequest.getParameter("id")).thenReturn("46390");

        assertThatExceptionOfType(NotFoundRentalPropertyException.class)
                .isThrownBy(() -> rentalPropertyDetailConsultationServlet.doGet(httpServletRequest, httpServletResponse))
                .satisfies(e -> assertThat(e.getMessage()).isEqualTo("Not found referenceId : 46390"));

        verify(httpServletRequest).getParameter("id");

        verifyNoInteractions(httpServletResponse);
        verifyNoMoreInteractions(httpServletRequest);
    }

}
