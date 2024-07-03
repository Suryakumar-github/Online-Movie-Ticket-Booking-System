package dao;

import model.Ticket;
import util.Database;
import util.SQLQueries;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDAOImplementation implements TicketDAO {
    public TicketDAOImplementation(){

    }
    @Override
    public boolean addTicket(Ticket ticket) {
        try {
            PreparedStatement ps = Database.getPreparedStatement(SQLQueries.INSERT_TICKET);
            ps.setInt(1, ticket.getUserId());
            ps.setInt(2,ticket.getShowtimeId());
            ps.setInt(3,ticket.getSeatId());
            ps.setInt(4, ticket.getMovieId());
            ps.setInt(5, ticket.getTheatreId());
            ps.setString(6, ticket.getStatus().toString());
            ps.setDate(7, ticket.getBookingDate());
            ps.setTime(8, ticket.getBookingTime());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Ticket getTicketById(int ticketId) {
        try {
            PreparedStatement ps = Database.getPreparedStatement(SQLQueries.GET_TICKET_BY_ID);
            ps.setInt(1, ticketId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setTicketId(rs.getInt("ticket_id"));
                ticket.setUserId(rs.getInt("user_id"));
                ticket.setMovieId(rs.getInt("movie_id"));
                ticket.setTheatreId(rs.getInt("theatre_id"));
                ticket.setShowtimeId(rs.getInt("showtime_id"));
                ticket.setBookingDate(rs.getDate("date"));
                ticket.setStatus(Ticket.Status.valueOf(rs.getString("status")));
                return ticket;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Ticket> getTicketsByUserId(int userId) {
        List<Ticket> tickets = new ArrayList<>();
        try {
            PreparedStatement ps = Database.getPreparedStatement(SQLQueries.GET_TICKET_BY_USERID);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setTicketId(rs.getInt("ticket_id"));
                ticket.setUserId(rs.getInt("user_id"));
                ticket.setMovieId(rs.getInt("movie_id"));
                ticket.setTheatreId(rs.getInt("theatre_id"));
                ticket.setShowtimeId(rs.getInt("showtime_id"));
                ticket.setBookingDate(rs.getDate("date"));
                ticket.setStatus(Ticket.Status.valueOf(rs.getString("status")));
                tickets.add(ticket);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return tickets;
    }

    @Override
    public boolean updateTicketStatus(int ticketId, Ticket.Status status) {
        try {
            PreparedStatement ps = Database.getPreparedStatement(SQLQueries.UPDATE_TICKET_STATUS);
            ps.setString(1, status.toString());
            ps.setInt(2, ticketId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean isTicketOwnedByUser(int ticketId, int userId) {
        try {
            String query = "SELECT COUNT(*) FROM tickets WHERE ticket_id = ? AND user_id = ?";
            PreparedStatement ps = Database.getPreparedStatement(query);
            ps.setInt(1, ticketId);
            ps.setInt(2, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean cancelTicket(int ticketId, int userId) {
        if (isTicketOwnedByUser(ticketId, userId)) {
            return updateTicketStatus(ticketId, Ticket.Status.CANCELED);
        } else {
            System.out.println("User does not own this ticket.");
            return false;
        }
    }
}

