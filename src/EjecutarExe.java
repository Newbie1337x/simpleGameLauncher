import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatGitHubIJTheme;

import java.awt.*;
import javax.swing.*;

public class EjecutarExe {

    public static void main(String[] args) {


        FlatGitHubDarkIJTheme.setup();

        JFrame frame = new JFrame("Game Launcher");
        frame.setMinimumSize(new Dimension(1000, 610));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GameManager gameManager = new GameManager();

        Game game6 = new Game("Dragon Ball:Sparking Zero","F:\\Games\\Others\\Dragon Ball Sparking ZERO\\SparkingZERO.exe");
        game6.setAppid(SteamSearch.getAppIdFromName("Dragon Ball Sparking Zero"));
        gameManager.addGame(game6);


        MenuGUI menuGUI = new MenuGUI(gameManager);

        frame.setContentPane(menuGUI);
        menuGUI.setGames(gameManager.getGames());
        frame.pack();
        frame.setVisible(true);
    }
}