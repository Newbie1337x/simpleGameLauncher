import java.io.File;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class GameManager {
    protected String user;
    protected Set<Game> games;

    public GameManager() {
        games = new TreeSet<Game>();
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {;
        this.games = games;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


    public void addGame(Game game) {
        this.games.add(game);
    }
    public void removeGame(Game game) {
        this.games.remove(game);
    }

    public void addGame(File selectedFile) {
        if (selectedFile != null) {
            // Obtener el nombre del archivo sin la extensión
            String fileName = selectedFile.getName();
            String gameName = fileName.substring(0, fileName.lastIndexOf('.'));
            String gamePath = selectedFile.getAbsolutePath();

            Game newGame = new Game(gameName, gamePath);

            games.add(newGame);
        }
    }

}
