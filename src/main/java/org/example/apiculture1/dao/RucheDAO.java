package org.example.apiculture1.dao;

import org.example.apiculture1.models.Ruche;
import org.example.apiculture1.utils.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RucheDAO {
    // SQL queries
    private static final String INSERT_SQL = "INSERT INTO ruche (code, ferme_id) VALUES ( ?, ?)";
    private static final String SELECT_ALL_SQL = "SELECT id, code, ferme_id FROM ruche";
    private static final String SELECT_BY_ID_SQL = "SELECT id, code, ferme_id FROM ruche WHERE id = ?";
    private static final String SELECT_BY_FERME_ID_SQL = "SELECT id, code, ferme_id FROM ruche WHERE ferme_id = ?";
    private static final String UPDATE_SQL = "UPDATE ruche SET code = ?, ferme_id = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM ruche WHERE id = ?";
    private static final String CHECK_FERME_EXISTS_SQL = "SELECT 1 FROM ferme WHERE id = ?";
    private static final String CHECK_CODE_UNIQUE_SQL = "SELECT 1 FROM ruche WHERE code = ? AND id != ?";

    private FermeDAO fermeDAO;

    public RucheDAO() {
        this.fermeDAO = new FermeDAO();
    }

    public List<Ruche> getAll() {
        List<Ruche> ruches = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SQL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Ruche ruche = mapResultSetToRuche(resultSet);
                ruches.add(ruche);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Consider throwing a custom exception
        }
        return ruches;
    }

    public Optional<Ruche> getById(int id) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapResultSetToRuche(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Ruche> getByFermeId(int fermeId) {
        List<Ruche> ruches = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_FERME_ID_SQL)) {

            statement.setInt(1, fermeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ruches.add(mapResultSetToRuche(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ruches;
    }

    public void add(Ruche ruche) throws SQLException, IllegalArgumentException {
        // Check if ferme exists
        if (!fermeExists(ruche.getFermeId())) {
            throw new IllegalArgumentException("La ferme avec l'ID " + ruche.getFermeId() + " n'existe pas");
        }

        // Check if code is unique
        if (codeExists(ruche.getCode(), 0)) {
            throw new IllegalArgumentException("Le code de ruche doit être unique");
        }

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, ruche.getCode());
            statement.setInt(2, ruche.getFermeId());
            statement.executeUpdate();

            // Retrieve the auto-generated ID
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    ruche.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public void update(Ruche ruche) throws SQLException, IllegalArgumentException {
        // Check if ferme exists
        if (!fermeExists(ruche.getFermeId())) {
            throw new IllegalArgumentException("La ferme avec l'ID " + ruche.getFermeId() + " n'existe pas");
        }

        // Check if code is unique (excluding current ruche)
        if (codeExists(ruche.getCode(), ruche.getId())) {
            throw new IllegalArgumentException("Le code de ruche doit être unique");
        }

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {

            statement.setString(1, ruche.getCode());
            statement.setInt(2, ruche.getFermeId());
            statement.setInt(3, ruche.getId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new IllegalArgumentException("Ruche non trouvée avec l'ID: " + ruche.getId());
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

    private Ruche mapResultSetToRuche(ResultSet resultSet) throws SQLException {
        return new Ruche(
                resultSet.getInt("id"),
                resultSet.getString("code"),
                resultSet.getInt("ferme_id")
        );
    }

    private boolean fermeExists(int fermeId) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(CHECK_FERME_EXISTS_SQL)) {

            statement.setInt(1, fermeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    private boolean codeExists(String code, int excludeId) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(CHECK_CODE_UNIQUE_SQL)) {

            statement.setString(1, code);
            statement.setInt(2, excludeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }
}