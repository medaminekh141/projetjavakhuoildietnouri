package org.example.apiculture1.controllers;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.apiculture1.dao.FermeDAO;
import org.example.apiculture1.dao.RucheDAO;
import org.example.apiculture1.models.Ruche;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ruches")
public class RucheServlet extends HttpServlet {
    private RucheDAO rucheDAO;
    private FermeDAO fermeDAO;

    @Override
    public void init() {
        rucheDAO = new RucheDAO();
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
                    deleteRuche(request, response);
                    break;
                case "list":
                default:
                    listRuches(request, response);
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
                    insertRuche(request, response);
                    break;
                case "update":
                    updateRuche(request, response);
                    break;
                default:
                    listRuches(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listRuches(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Ruche> ruches = rucheDAO.getAll();
        request.setAttribute("ruches", ruches);
        request.getRequestDispatcher("/WEB-INF/views/ruche/list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("fermes", fermeDAO.getAll());
        request.getRequestDispatcher("/WEB-INF/views/ruche/form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Ruche ruche = rucheDAO.getById(id).orElseThrow(() -> new ServletException("Ruche non trouvée"));

        request.setAttribute("ruche", ruche);
        request.setAttribute("fermes", fermeDAO.getAll());
        request.getRequestDispatcher("/WEB-INF/views/ruche/form.jsp").forward(request, response);
    }

    private void insertRuche(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            Ruche ruche = new Ruche();
            ruche.setCode(request.getParameter("code"));
            ruche.setFermeId(Integer.parseInt(request.getParameter("fermeId")));

            rucheDAO.add(ruche);
            response.sendRedirect("ruches");
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            request.setAttribute("fermes", fermeDAO.getAll());
            request.getRequestDispatcher("/WEB-INF/views/ruche/form.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateRuche(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            Ruche ruche = new Ruche();
            ruche.setId(Integer.parseInt(request.getParameter("id")));
            ruche.setCode(request.getParameter("code"));
            ruche.setFermeId(Integer.parseInt(request.getParameter("fermeId")));

            rucheDAO.update(ruche);
            response.sendRedirect("ruches");
        } catch (IllegalArgumentException | SQLException e) {
            request.setAttribute("error", e.getMessage());
            request.setAttribute("fermes", fermeDAO.getAll());
            request.setAttribute("ruche", new Ruche(
                    Integer.parseInt(request.getParameter("id")),
                    request.getParameter("code"),
                    Integer.parseInt(request.getParameter("fermeId"))
            ));
            request.getRequestDispatcher("/WEB-INF/views/ruche/form.jsp").forward(request, response);
        }
    }

    private void deleteRuche(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        rucheDAO.delete(id);
        response.sendRedirect("ruches");
    }
}