package org.example.apiculture1.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.apiculture1.dao.FermierDAO;
import org.example.apiculture1.models.Fermier;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/fermiers")
public class FermierServlet extends HttpServlet {
    private FermierDAO fermierDAO;

    @Override
    public void init() {
        fermierDAO = new FermierDAO();
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
                    deleteFermier(request, response);
                    break;
                case "list":
                default:
                    listFermiers(request, response);
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
                    insertFermier(request, response);
                    break;
                case "update":
                    updateFermier(request, response);
                    break;
                default:
                    listFermiers(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listFermiers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Fermier> fermiers = fermierDAO.getAll();
        request.setAttribute("fermiers", fermiers);
        request.getRequestDispatcher("/WEB-INF/views/fermier/list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/fermier/form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Fermier fermier = fermierDAO.getById(id).orElseThrow(() -> new ServletException("Fermier non trouvé"));
        request.setAttribute("fermier", fermier);
        request.getRequestDispatcher("/WEB-INF/views/fermier/form.jsp").forward(request, response);
    }

    private void insertFermier(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        Fermier fermier = new Fermier(0, request.getParameter("nom"));
        fermierDAO.add(fermier);
        response.sendRedirect("fermiers");
    }

    private void updateFermier(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        Fermier fermier = new Fermier(id, request.getParameter("nom"));
        fermierDAO.update(fermier);
        response.sendRedirect("fermiers");
    }

    private void deleteFermier(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        fermierDAO.delete(id);
        response.sendRedirect("fermiers");
    }
}