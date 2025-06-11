package org.example.apiculture1.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.apiculture1.dao.FermeDAO;
import org.example.apiculture1.models.Ferme;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/fermes")
public class FermeServlet extends HttpServlet {
    private FermeDAO fermeDAO;

    @Override
    public void init() {
        fermeDAO = new FermeDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteFerme(request, response);
                    break;
                case "list":
                default:
                    listFermes(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "insert":
                    insertFerme(request, response);
                    break;
                case "update":
                    updateFerme(request, response);
                    break;
                default:
                    listFermes(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listFermes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Ferme> fermes = fermeDAO.getAll();
        System.out.println("Fermes: " + fermes);
        request.setAttribute("fermes", fermes);
        request.getRequestDispatcher("/WEB-INF/views/ferme/list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/ferme/form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Ferme ferme = fermeDAO.getByFermierId(id).stream().findFirst().orElse(null);
        request.setAttribute("ferme", ferme);
        request.getRequestDispatcher("/WEB-INF/views/ferme/form.jsp").forward(request, response);
    }

    private void insertFerme(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            Ferme ferme = new Ferme();
            ferme.setLocalisation(request.getParameter("localisation"));
            ferme.setFermierId(Integer.parseInt(request.getParameter("fermierId")));

            fermeDAO.add(ferme);
            response.sendRedirect("fermes");
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/ferme/form.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateFerme(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Ferme ferme = null;
        try {
            ferme = new Ferme();
            ferme.setId(Integer.parseInt(request.getParameter("id")));
            ferme.setLocalisation(request.getParameter("localisation"));
            ferme.setFermierId(Integer.parseInt(request.getParameter("fermierId")));

            fermeDAO.update(ferme);
            response.sendRedirect("fermes");
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            request.setAttribute("ferme", ferme); // Pour pré-remplir le formulaire
            request.getRequestDispatcher("/WEB-INF/views/ferme/form.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteFerme(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        fermeDAO.delete(id);
        response.sendRedirect("fermes");
    }
}