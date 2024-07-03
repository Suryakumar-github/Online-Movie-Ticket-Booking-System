package dao;

import model.Ticket;
import java.util.List;

public interface TicketDAO {
    boolean addTicket(Ticket ticket);
    Ticket getTicketById(int ticketId);
    List<Ticket> getTicketsByUserId(int userId);
    boolean updateTicketStatus(int ticketId, Ticket.Status status);
}

