package lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {
    private static final String url = "jdbc:postgresql://localhost:5432/cinema_db";
    private static final String user = "postgres";
    private static final String password = "4230";

    // Метод для добавления фильма
    public void addMovie(Movie movie) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO movies (movie_name) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, movie.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для получения всех фильмов
    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM movies";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Movie movie = new Movie(resultSet.getInt("movie_id"), resultSet.getString("movie_name"));
                movies.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    // Метод для обновления информации о фильме
    public void updateMovie(int movieId, String newName) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "UPDATE movies SET movie_name = ? WHERE movie_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newName);
            statement.setInt(2, movieId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для удаления фильма
    public void deleteMovie(int movieId) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "DELETE FROM movies WHERE movie_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, movieId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
