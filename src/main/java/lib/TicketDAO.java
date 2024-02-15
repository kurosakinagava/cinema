package lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {
    private static final String url = "jdbc:postgresql://localhost:5432/cinema_db";
    private static final String user = "postgres";
    private static final String password = "4230";

    // Метод для добавления нового билета
    public void addTicket(Ticket ticket) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO tickets (ticket_cost) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, ticket.getCost());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для получения всех билетов
    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM tickets";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Ticket ticket = new Ticket(resultSet.getInt("ticket_id"), resultSet.getDouble("ticket_cost"));
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    // Метод для обновления информации о билете
    public void updateTicket(int ticketId, double newCost) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "UPDATE tickets SET ticket_cost = ? WHERE ticket_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, newCost);
            statement.setInt(2, ticketId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для удаления билета
    public void deleteTicket(int ticketId) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "DELETE FROM tickets WHERE ticket_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, ticketId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
