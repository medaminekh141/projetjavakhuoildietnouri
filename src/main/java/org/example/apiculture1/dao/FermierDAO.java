package org.example.apiculture1.dao;

import org.example.apiculture1.models.Fermier;
import org.example.apiculture1.utils.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FermierDAO {
    // SQL queries
    private static final String INSERT_SQL = "INSERT INTO fermier (nom) VALUES (?)";
    private static final String SELECT_ALL_SQL = "SELECT id, nom FROM fermier";
    private static final String SELECT_BY_ID_SQL = "SELECT id, nom FROM fermiers WHERE id = ?";
    private static final String UPDATE_SQL = "UPDATE fermier SET nom = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM fermier WHERE id = ?";

    public List<Fermier> getAll() {
        List<Fermier> fermiers = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SQL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Fermier fermier = new Fermier(
                        resultSet.getInt("id"),
                        resultSet.getString("nom")
                );
                fermiers.add(fermier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // In a real application, you might want to throw a custom exception
        }
        return fermiers;
    }

    public Optional<Fermier> getById(int id) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Fermier fermier = new Fermier(
                            resultSet.getInt("id"),
                            resultSet.getString("nom")
                    );
                    return Optional.of(fermier);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void add(Fermier fermier) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, fermier.getNom());
            statement.executeUpdate();

            // Retrieve the auto-generated ID
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    fermier.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public void update(Fermier fermier) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {

            statement.setString(1, fermier.getNom());
            statement.setInt(2, fermier.getId());
            statement.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}