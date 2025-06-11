package org.example.apiculture1.dao;

import org.example.apiculture1.models.Ferme;
import org.example.apiculture1.utils.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FermeDAO {
    // SQL queries
    private static final String INSERT_SQL = "INSERT INTO ferme ( localisation, fermier_id) VALUES (?, ?)";
    private static final String SELECT_ALL_SQL = "SELECT id,  localisation, fermier_id FROM ferme";
    private static final String SELECT_BY_ID_SQL = "SELECT id,  localisation, fermier_id FROM fermes WHERE id = ?";
    private static final String SELECT_BY_FERMIER_ID_SQL = "SELECT id,  localisation, fermier_id FROM ferme WHERE fermier_id = ?";
    private static final String UPDATE_SQL = "UPDATE ferme SET  localisation = ?, fermier_id = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM ferme WHERE id = ?";
    private static final String CHECK_FERMIER_EXISTS_SQL = "SELECT 1 FROM fermier WHERE id = ?";

    private FermierDAO fermierDAO;

    public FermeDAO() {
        this.fermierDAO = new FermierDAO();
    }

    public List<Ferme> getAll() {
        List<Ferme> fermes = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SQL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Ferme ferme = mapResultSetToFerme(resultSet);
                fermes.add(ferme);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Consider throwing a custom exception
        }
        return fermes;
    }

    public List<Ferme> getByFermierId(int fermierId) {
        List<Ferme> fermes = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_FERMIER_ID_SQL)) {

            statement.setInt(1, fermierId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Ferme ferme = mapResultSetToFerme(resultSet);
                    fermes.add(ferme);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fermes;
    }

    public void add(Ferme ferme) throws SQLException, IllegalArgumentException {
        // Check if fermier exists
        if (!fermierExists(ferme.getFermierId())) {
            throw new IllegalArgumentException("Le fermier avec l'ID " + ferme.getFermierId() + " n'existe pas");
        }

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, ferme.getLocalisation());
            statement.setInt(2, ferme.getFermierId());
            statement.executeUpdate();

            // Retrieve the auto-generated ID
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    ferme.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public void update(Ferme ferme) throws SQLException, IllegalArgumentException {
        // Check if fermier exists
        if (!fermierExists(ferme.getFermierId())) {
            throw new IllegalArgumentException("Le fermier avec l'ID " + ferme.getFermierId() + " n'existe pas");
        }

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {

            statement.setString(1, ferme.getLocalisation());
            statement.setInt(3, ferme.getFermierId());
            statement.setInt(4, ferme.getId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new IllegalArgumentException("Ferme non trouvée avec l'ID: " + ferme.getId());
            }
        }
    }

    public void delete(int id) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    private Ferme mapResultSetToFerme(ResultSet resultSet) throws SQLException {
        return new Ferme(
                resultSet.getInt("id"),
                resultSet.getString("localisation"),
                resultSet.getInt("fermier_id")
        );
    }

    private boolean fermierExists(int fermierId) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(CHECK_FERMIER_EXISTS_SQL)) {

            statement.setInt(1, fermierId);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Returns true if fermier exists
            }
        }
    }
}