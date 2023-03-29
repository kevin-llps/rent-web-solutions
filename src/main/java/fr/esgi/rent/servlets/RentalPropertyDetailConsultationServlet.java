package fr.esgi.rent.servlets;

import fr.esgi.rent.beans.RentalProperty;
import fr.esgi.rent.exception.MissingRentalPropertyId;
import fr.esgi.rent.exception.NotFoundRentalPropertyException;
import fr.esgi.rent.services.RentalPropertiesFileParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/details")
public class RentalPropertyDetailConsultationServlet extends HttpServlet {

    private final RentalPropertiesFileParser rentalPropertiesFileParser;

    public RentalPropertyDetailConsultationServlet() {
        this.rentalPropertiesFileParser = new RentalPropertiesFileParser();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParamValue = request.getParameter("id");

        if (idParamValue == null) {
            throw new MissingRentalPropertyId("Parameter id is null");
        }

        int id = Integer.parseInt(idParamValue);

        RentalProperty rentalProperty = rentalPropertiesFileParser.parse("rentalProperties.csv")
                .stream()
                .filter(r -> r.referenceId() == id)
                .findAny()
                .orElseThrow(() -> new NotFoundRentalPropertyException("Not found referenceId : " + id));

        request.setAttribute("rentalProperty", rentalProperty);
        request.getRequestDispatcher("/rentalPropertyDetail.jsp").forward(request, response);
    }
}
