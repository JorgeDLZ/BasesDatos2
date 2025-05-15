package com.biblioteca.dao;

import com.biblioteca.model.Miembro;
import com.biblioteca.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class MiembroDAO {

    public void addMiembro(Miembro miembro) {
        String sql = "INSERT INTO miembros (nombre, apellido, fecha_inscripcion) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet generatedKeys = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, miembro.getNombre());
            stmt.setString(2, miembro.getApellido());
            stmt.setDate(3, new java.sql.Date(miembro.getFechaInscripcion().getTime()));

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    miembro.setMiembroId(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeResultSet(generatedKeys);
            DatabaseConnection.closeStatement(stmt);
            DatabaseConnection.closeConnection(conn);
        }
    }

    public List<Miembro> getAllMiembros() {
        List<Miembro> miembros = new ArrayList<>();
        String sql = "SELECT * FROM miembros";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Miembro miembro = new Miembro(
                        rs.getInt("miembro_id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getDate("fecha_inscripcion")
                );
                miembros.add(miembro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeResultSet(rs);
            DatabaseConnection.closeStatement(stmt);
            DatabaseConnection.closeConnection(conn);
        }
        return miembros;
    }

    public Miembro getMiembroById(int id) {
        String sql = "SELECT * FROM miembros WHERE miembro_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new Miembro(
                        rs.getInt("miembro_id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getDate("fecha_inscripcion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeResultSet(rs);
            DatabaseConnection.closeStatement(stmt);
            DatabaseConnection.closeConnection(conn);
        }
        return null;
    }

    public void updateMiembro(Miembro miembro) {
        String sql = "UPDATE miembros SET nombre = ?, apellido = ?, fecha_inscripcion = ? WHERE miembro_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, miembro.getNombre());
            stmt.setString(2, miembro.getApellido());
            stmt.setDate(3, new java.sql.Date(miembro.getFechaInscripcion().getTime()));
            stmt.setInt(4, miembro.getMiembroId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeStatement(stmt);
            DatabaseConnection.closeConnection(conn);
        }
    }

    public void deleteMiembro(int id) {
        String sql = "DELETE FROM miembros WHERE miembro_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeStatement(stmt);
            DatabaseConnection.closeConnection(conn);
        }
    }
}