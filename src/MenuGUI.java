import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Set;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;
import javax.swing.event.*;
/*
 * Created by JFormDesigner on Wed Oct 23 11:22:42 ART 2024
 */


/**
 * @author Administrator
 */
public class MenuGUI extends JPanel {
    private Game selectedGame;
    private final DefaultListModel<Game> gameListModel;
    private final GameManager gameManager;

    public MenuGUI(GameManager gameManager) {
        initComponents();
        gameListModel = new DefaultListModel<>();
        gameList.setModel(gameListModel);
        this.gameManager = gameManager;
    }


    private void playButtonMouseClicked(MouseEvent e) {
        selectedGame.run();
        playButton.setText("Running");
    }


    private void addGameMouseClicked(MouseEvent e) {
        JFrame frame = new JFrame();
        JFileChooser fileChooser = new JFileChooser();
        // Solo permite seleccionar archivos
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Archivo seleccionado: " + selectedFile.getAbsolutePath());

            // Delegar al GameManager para que procese el archivo
            gameManager.addGame(selectedFile);

            // Actualizar la lista
            setGames(gameManager.getGames());

        } else {
            System.out.println("Por favor selecciona un archivo .exe");
        }
    }

    private void addGameMouseEntered(MouseEvent e) {
        addGame.setForeground(Color.white);
    }
    private void addGameMouseExited(MouseEvent e) {
        addGame.setForeground(new Color(0x666666));
    }

    //FileManager Listeners

    private void fileManagerMouseEntered(MouseEvent e) {
        fileManager.setForeground(Color.white);
    }
    private void fileManagerMouseExited(MouseEvent e) {
        fileManager.setForeground(new Color(0x666666));
    }
    private void fileManagerMouseClicked(MouseEvent e) {
        selectedGame.openPath();

    }
    public void setGames(Set<Game> games) {
        gameListModel.clear();

        for (Game game : games) {
            gameListModel.addElement(game); // Agregar juegos al modelo
        }
    }

    private void gameListSelected(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) { // Verifica que la selección haya terminado
            playButton.setText("Play");
            selectedGame = gameList.getSelectedValue();
            if (selectedGame != null) {
                gameTitle.setText(selectedGame.getName());
                gameDescription.setText(selectedGame.getDescription());
            } else {
                gameTitle.setText("No hay juego seleccionado");
            }
        }
    }

    private void deleteMouseClicked(MouseEvent e) {
        int confirm = JOptionPane.showConfirmDialog(
                null,
                "¿Are you sure you want to delete the game \"" + selectedGame.getName() + "\"?",
                "",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        if (confirm == JOptionPane.YES_OPTION) {
            gameManager.removeGame(selectedGame);
            setGames(gameManager.getGames());
        }

    }

    private void modifyMouseClicked(MouseEvent e) {

            String newAppId = JOptionPane.showInputDialog(null, "Enter new appid:", selectedGame.getAppid());


            if (newAppId != null && !newAppId.trim().isEmpty()) {
                selectedGame.setAppid(newAppId);
                JOptionPane.showMessageDialog(null, "Game name updated to: " + selectedGame.getName() + " and appid updated to: " + selectedGame.getAppid());
            } else {
                JOptionPane.showMessageDialog(null, "Game name updated to: " + selectedGame.getName());
            }
            selectedGame.generateData();
            setGames(gameManager.getGames());

        }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Tomas cabrera
        scrollPane1 = new JScrollPane();
        gameList = new JList<>();
        addGame = new JButton();
        panel1 = new JPanel();
        panel3 = new JPanel();
        fileManager = new JButton();
        scrollPane2 = new JScrollPane();
        gameDescription = new JTextPane();
        panel2 = new JPanel();
        playButton = new JButton();
        infoPlayed = new JLabel();
        timePlayed = new JLabel();
        infoRelease = new JLabel();
        releaseDate = new JLabel();
        infoPlatform = new JLabel();
        platform = new JLabel();
        gameTitlePanel = new JPanel();
        gameTitle = new JLabel();
        delete = new JButton();
        modify = new JButton();

        //======== this ========
        setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax
        . swing. border .EmptyBorder ( 0, 0 ,0 , 0) ,  "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn" , javax. swing
        .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder . BOTTOM, new java. awt .
        Font ( "Dia\u006cog", java .awt . Font. BOLD ,12 ) ,java . awt. Color .red
        ) , getBorder () ) );  addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override
        public void propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062ord\u0065r" .equals ( e. getPropertyName (
        ) ) )throw new RuntimeException( ) ;} } );

        //======== scrollPane1 ========
        {

            //---- gameList ----
            gameList.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));
            gameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            gameList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            gameList.setFocusable(false);
            gameList.addListSelectionListener(e -> gameListSelected(e));
            scrollPane1.setViewportView(gameList);
        }

        //---- addGame ----
        addGame.setText("Add game");
        addGame.setBorderPainted(false);
        addGame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addGame.setForeground(new Color(0x666666));
        addGame.setBorder(LineBorder.createBlackLineBorder());
        addGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addGameMouseClicked(e);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                addGameMouseEntered(e);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                addGameMouseExited(e);
            }
        });

        //======== panel1 ========
        {

            //======== panel3 ========
            {
                panel3.setForeground(Color.white);
                panel3.setBackground(new Color(0x282d33));

                //---- fileManager ----
                fileManager.setText("Path");
                fileManager.setFont(new Font("Inter", Font.BOLD, 13));
                fileManager.setForeground(new Color(0x666666));
                fileManager.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                fileManager.setBorder(new LineBorder(Color.darkGray));
                fileManager.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        fileManagerMouseClicked(e);
                    }
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        fileManagerMouseEntered(e);
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                        fileManagerMouseExited(e);
                    }
                });

                GroupLayout panel3Layout = new GroupLayout(panel3);
                panel3.setLayout(panel3Layout);
                panel3Layout.setHorizontalGroup(
                    panel3Layout.createParallelGroup()
                        .addGroup(panel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(fileManager, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(639, Short.MAX_VALUE))
                );
                panel3Layout.setVerticalGroup(
                    panel3Layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fileManager, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                );
            }

            //======== scrollPane2 ========
            {
                scrollPane2.setBorder(null);

                //---- gameDescription ----
                gameDescription.setBorder(null);
                gameDescription.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
                gameDescription.setFocusable(false);
                gameDescription.setEditable(false);
                gameDescription.setFont(gameDescription.getFont().deriveFont(gameDescription.getFont().getSize() + 1f));
                gameDescription.setForeground(new Color(0xcccccc));
                scrollPane2.setViewportView(gameDescription);
            }

            //======== panel2 ========
            {
                panel2.setBackground(new Color(0x282d33));

                //---- playButton ----
                playButton.setText("PLAY");
                playButton.setFont(playButton.getFont().deriveFont(playButton.getFont().getSize() + 11f));
                playButton.setForeground(Color.white);
                playButton.setBackground(new Color(0x00cc33));
                playButton.setFocusPainted(false);
                playButton.setBorder(null);
                playButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                playButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        playButtonMouseClicked(e);
                    }
                });

                //---- infoPlayed ----
                infoPlayed.setText("Time Played");
                infoPlayed.setFont(infoPlayed.getFont().deriveFont(infoPlayed.getFont().getStyle() | Font.BOLD, infoPlayed.getFont().getSize() + 1f));

                //---- timePlayed ----
                timePlayed.setText("0 Hours");

                //---- infoRelease ----
                infoRelease.setText("Release");
                infoRelease.setFont(infoRelease.getFont().deriveFont(infoRelease.getFont().getStyle() | Font.BOLD, infoRelease.getFont().getSize() + 1f));

                //---- releaseDate ----
                releaseDate.setText("unknown");

                //---- infoPlatform ----
                infoPlatform.setText("Platform");
                infoPlatform.setFont(infoPlatform.getFont().deriveFont(infoPlatform.getFont().getStyle() | Font.BOLD, infoPlatform.getFont().getSize() + 1f));

                //---- platform ----
                platform.setText("unknown");

                GroupLayout panel2Layout = new GroupLayout(panel2);
                panel2.setLayout(panel2Layout);
                panel2Layout.setHorizontalGroup(
                    panel2Layout.createParallelGroup()
                        .addGroup(panel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(playButton, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(panel2Layout.createParallelGroup()
                                .addGroup(panel2Layout.createSequentialGroup()
                                    .addGap(14, 14, 14)
                                    .addComponent(timePlayed, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
                                .addComponent(infoPlayed, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
                            .addGap(231, 231, 231)
                            .addGroup(panel2Layout.createParallelGroup()
                                .addComponent(infoRelease, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
                                .addComponent(releaseDate))
                            .addGroup(panel2Layout.createParallelGroup()
                                .addComponent(infoPlatform, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
                                .addComponent(platform, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
                            .addGap(12, 12, 12))
                );
                panel2Layout.setVerticalGroup(
                    panel2Layout.createParallelGroup()
                        .addGroup(panel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panel2Layout.createParallelGroup()
                                .addComponent(playButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panel2Layout.createSequentialGroup()
                                    .addGroup(panel2Layout.createParallelGroup()
                                        .addGroup(panel2Layout.createSequentialGroup()
                                            .addComponent(infoPlatform, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(platform, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(releaseDate, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(infoRelease, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(panel2Layout.createSequentialGroup()
                                            .addComponent(infoPlayed, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(timePlayed)))
                                    .addGap(0, 0, Short.MAX_VALUE)))
                            .addGap(1, 1, 1))
                );
            }

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addComponent(panel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 445, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 280, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(43, 43, 43)
                        .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(140, 140, 140))
            );
        }

        //======== gameTitlePanel ========
        {
            gameTitlePanel.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));

            //---- gameTitle ----
            gameTitle.setFont(gameTitle.getFont().deriveFont(gameTitle.getFont().getSize() + 27f));

            GroupLayout gameTitlePanelLayout = new GroupLayout(gameTitlePanel);
            gameTitlePanel.setLayout(gameTitlePanelLayout);
            gameTitlePanelLayout.setHorizontalGroup(
                gameTitlePanelLayout.createParallelGroup()
                    .addGroup(gameTitlePanelLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(gameTitle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(418, 418, 418))
            );
            gameTitlePanelLayout.setVerticalGroup(
                gameTitlePanelLayout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, gameTitlePanelLayout.createSequentialGroup()
                        .addContainerGap(61, Short.MAX_VALUE)
                        .addComponent(gameTitle, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
            );
        }

        //---- delete ----
        delete.setText("-");
        delete.setBorder(LineBorder.createBlackLineBorder());
        delete.setForeground(new Color(0x666666));
        delete.setBorderPainted(false);
        delete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        delete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                deleteMouseClicked(e);
            }
        });

        //---- modify ----
        modify.setText("m");
        modify.setForeground(new Color(0x666666));
        modify.setBorder(LineBorder.createBlackLineBorder());
        modify.setBorderPainted(false);
        modify.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                modifyMouseClicked(e);
            }
        });

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup()
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 288, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(addGame, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(modify, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(delete, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup()
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(gameTitlePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(addGame, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
                                .addComponent(modify, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                                .addComponent(delete, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
                            .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(gameTitlePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(12, 12, 12)
                            .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Tomas cabrera
    private JScrollPane scrollPane1;
    private JList<Game> gameList;
    private JButton addGame;
    private JPanel panel1;
    private JPanel panel3;
    private JButton fileManager;
    private JScrollPane scrollPane2;
    private JTextPane gameDescription;
    private JPanel panel2;
    private JButton playButton;
    private JLabel infoPlayed;
    private JLabel timePlayed;
    private JLabel infoRelease;
    private JLabel releaseDate;
    private JLabel infoPlatform;
    private JLabel platform;
    private JPanel gameTitlePanel;
    private JLabel gameTitle;
    private JButton delete;
    private JButton modify;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
