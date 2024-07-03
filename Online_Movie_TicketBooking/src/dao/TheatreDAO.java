package dao;

import model.Theatre;
import java.util.List;

public interface TheatreDAO {
    boolean addTheatre(Theatre theatre);
    Theatre getTheatreById(int theatreId);
    List<Theatre> getAllTheatres();
    boolean updateTheatre(Theatre theatre);
    boolean removeTheatre(int theatreId);
}

