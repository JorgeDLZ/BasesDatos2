package com.biblioteca.dao;

import com.biblioteca.model.Libro;
import com.biblioteca.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {

    public void addLibro(Libro libro) {
        String sql = "INSERT INTO libros (titulo, genero, autor_id) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet generatedKeys = null;
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getGenero());
            if (libro.getAutorId() > 0) {
                stmt.setInt(3, libro.getAutorId());
            } else {
                stmt.setNull(3, java.sql.Types.INTEGER);
            }

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    libro.setLibroId(generatedKeys.getInt(1));
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

    public List<Libro> getAllLibros() {
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT * FROM libros";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int autorId = rs.getInt("autor_id");
                if (rs.wasNull()) {
                    autorId = 0;
                }

                Libro libro = new Libro(
                        rs.getInt("libro_id"),
                        rs.getString("titulo"),
                        rs.getString("genero"),
                        autorId
                );
                libros.add(libro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeResultSet(rs);
            DatabaseConnection.closeStatement(stmt);
            DatabaseConnection.closeConnection(conn);
        }
        return libros;
    }

    public Libro getLibroById(int id) {
        String sql = "SELECT * FROM libros WHERE libro_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int autorId = rs.getInt("autor_id");
                if (rs.wasNull()) {
                    autorId = 0;
                }
                return new Libro(
                        rs.getInt("libro_id"),
                        rs.getString("titulo"),
                        rs.getString("genero"),
                        autorId
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

    public void updateLibro(Libro libro) {
        String sql = "UPDATE libros SET titulo = ?, genero = ?, autor_id = ? WHERE libro_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getGenero());
            if (libro.getAutorId() > 0) {
                stmt.setInt(3, libro.getAutorId());
            } else {
                stmt.setNull(3, java.sql.Types.INTEGER);
            }
            stmt.setInt(4, libro.getLibroId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeStatement(stmt);
            DatabaseConnection.closeConnection(conn);
        }
    }

    public void deleteLibro(int id) {
        String sql = "DELETE FROM libros WHERE libro_id = ?";
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