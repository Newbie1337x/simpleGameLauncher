import enums.Store;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;


public class Game implements Comparable<Game> {

    protected String appid;
    protected String name; //Sparking Zero
    protected String description;
    protected File pathParent;
    protected File path;


    public Game(String name, String pathS) {
        this.appid = SteamSearch.getAppIdFromName(name);
        this.name = name;
        this.path = new File(pathS);
        this.pathParent = path.getParentFile();
        generateData();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void run() {
        try {
            // Inicia el proceso
            Desktop.getDesktop().open(path);

            System.out.println("Aplicación iniciada");
        } catch (IOException e) {
            System.err.println("Error al iniciar la aplicación: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void openPath() {

        try {
            Desktop.getDesktop().open(pathParent);
        } catch (IOException e) {
            System.err.println("Error al abrir el archivo: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("Error al abrir el archivo: " + e.getMessage());
        }
    }


    public void generateData() {

        SteamSearch steamSearch = new SteamSearch();


        if (appid != null) {
            this.description = steamSearch.getGameInfo(appid, "description");
            this.name = steamSearch.getGameInfo(appid, "name");
        }
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game game)) return false;
        return Objects.equals(getName(), game.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }

    @Override
    public int compareTo(Game other) {
        return this.name.compareTo(other.name);
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }
}
