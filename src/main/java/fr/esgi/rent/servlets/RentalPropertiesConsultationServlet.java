package fr.esgi.rent.servlets;

import fr.esgi.rent.beans.RentalProperty;
import fr.esgi.rent.services.RentalPropertiesFileParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/rental-properties")
public class RentalPropertiesConsultationServlet extends HttpServlet {

    private final RentalPropertiesFileParser rentalPropertiesFileParser;

    public RentalPropertiesConsultationServlet() {
        this.rentalPropertiesFileParser = new RentalPropertiesFileParser();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<RentalProperty> rentalProperties = rentalPropertiesFileParser.parse("rentalProperties.csv");
        request.setAttribute("rentalProperties", rentalProperties);

        request.getRequestDispatcher("/rentalProperties.jsp").forward(request, response);
    }

}
