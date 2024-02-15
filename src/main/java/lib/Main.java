import lib.MovieDAO;
import lib.TicketDAO;
import lib.UserDAO;

public class Main {
    public static void main(String[] args) {
        MovieDAO movieDAO = new MovieDAO();
        UserDAO userDAO = new UserDAO();
        TicketDAO ticketDAO = new TicketDAO();

        // Пример работы с таблицей movies
        // Добавление нового фильма
        Movie newMovie = new Movie("Avatar");
        movieDAO.addMovie(newMovie);

        // Получение всех фильмов и вывод на консоль
        List<Movie> movies = movieDAO.getAllMovies();
        for (Movie movie : movies) {
            System.out.println("Movie ID: " + movie.getId() + ", Name: " + movie.getName());
        }

        // Пример работы с таблицей users
        // Добавление нового пользователя
        User newUser = new User("Dastan");
        userDAO.addUser(newUser);

        // Получение всех пользователей и вывод на консоль
        List<User> users = userDAO.getAllUsers();
        for (User user : users) {
            System.out.println("User ID: " + user.getId() + ", Name: " + user.getName());
        }

        // Пример работы с таблицей tickets
        // Добавление нового билета
        Ticket newTicket = new Ticket(20.0);
        ticketDAO.addTicket(newTicket);

        // Получение всех билетов и вывод на консоль
        List<Ticket> tickets = ticketDAO.getAllTickets();
        for (Ticket ticket : tickets) {
            System.out.println("Ticket ID: " + ticket.getId() + ", Cost: " + ticket.getCost());
        }


    }
}

