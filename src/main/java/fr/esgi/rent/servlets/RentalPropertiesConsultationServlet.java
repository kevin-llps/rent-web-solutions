package fr.esgi.rent.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/rental-properties")
public class RentalPropertiesConsultationServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try (PrintWriter printWriter = response.getWriter()) {

            printWriter.println("<h1>Liste des locations</h1>");

            printWriter.println("<ul>");
            printWriter.println("<li>Appartement à louer</li>");
            printWriter.println("<li>Description : Appartement spacieux avec vue sur l'ESGI</li>");
            printWriter.println("<li>Loyer : 750.90 €</li>");
            printWriter.println("<li>Caution : 1200.90 €</li>");
            printWriter.println("<li>Surface : 37.48 m²</li>");
            printWriter.println("</ul>");

            printWriter.println("<ul>");
            printWriter.println("<li>Maison à louer</li>");
            printWriter.println("<li>Description : Maison à louer dans banlieue calme et proche du RER</li>");
            printWriter.println("<li>Loyer : 1050.90 €</li>");
            printWriter.println("<li>Caution : 1400.90 €</li>");
            printWriter.println("<li>Surface : 62.50 m²</li>");
            printWriter.println("</ul>");
        }
    }

}
