package com.biblioteca.dao;

import com.biblioteca.model.Prestamo;
import com.biblioteca.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class PrestamoDAO {

    public void addPrestamo(Prestamo prestamo) {
        String sql = "INSERT INTO prestamos (libro_id, miembro_id, fecha_prestamo, fecha_devolucion) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet generatedKeys = null;
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, prestamo.getLibroId());
            stmt.setInt(2, prestamo.getMiembroId());
            stmt.setDate(3, new java.sql.Date(prestamo.getFechaPrestamo().getTime()));

            if (prestamo.getFechaDevolucion() != null) {
                stmt.setDate(4, new java.sql.Date(prestamo.getFechaDevolucion().getTime()));
            } else {
                stmt.setNull(4, java.sql.Types.DATE);
            }

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    prestamo.setPrestamoId(generatedKeys.getInt(1));
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

    public List<Prestamo> getAllPrestamos() {
        List<Prestamo> prestamos = new ArrayList<>();
        String sql = "SELECT * FROM prestamos";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Prestamo prestamo = new Prestamo(
                        rs.getInt("prestamo_id"),
                        rs.getInt("libro_id"),
                        rs.getInt("miembro_id"),
                        rs.getDate("fecha_prestamo"),
                        rs.getDate("fecha_devolucion")
                );
                prestamos.add(prestamo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeResultSet(rs);
            DatabaseConnection.closeStatement(stmt);
            DatabaseConnection.closeConnection(conn);
        }
        return prestamos;
    }

    public Prestamo getPrestamoById(int id) {
        String sql = "SELECT * FROM prestamos WHERE prestamo_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return new Prestamo(
                        rs.getInt("prestamo_id"),
                        rs.getInt("libro_id"),
                        rs.getInt("miembro_id"),
                        rs.getDate("fecha_prestamo"),
                        rs.getDate("fecha_devolucion")
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

    public void updatePrestamo(Prestamo prestamo) {
        String sql = "UPDATE prestamos SET libro_id = ?, miembro_id = ?, fecha_prestamo = ?, fecha_devolucion = ? WHERE prestamo_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, prestamo.getLibroId());
            stmt.setInt(2, prestamo.getMiembroId());
            stmt.setDate(3, new java.sql.Date(prestamo.getFechaPrestamo().getTime()));

            if (prestamo.getFechaDevolucion() != null) {
                stmt.setDate(4, new java.sql.Date(prestamo.getFechaDevolucion().getTime()));
            } else {
                stmt.setNull(4, java.sql.Types.DATE);
            }
            stmt.setInt(5, prestamo.getPrestamoId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeStatement(stmt);
            DatabaseConnection.closeConnection(conn);
        }
    }

    public void deletePrestamo(int id) {
        String sql = "DELETE FROM prestamos WHERE prestamo_id = ?";
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