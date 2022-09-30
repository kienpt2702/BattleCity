package game_main;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GameMenu extends JPanel implements ActionListener{
    JButton startButton, continueButton,restart,exit;
    GameFrame frame;
    JLabel gameTitle, gameState;
    GamePanel gamePanel;
    Font arcadeFont;
    boolean nextLevel;
    public GameMenu(GameFrame myFrame){
        frame = myFrame;
        gameTitle = new JLabel();
        gameState = new JLabel();
        startButton = new JButton();
        continueButton = new JButton();
        exit = new JButton();
        restart = new JButton();
        try {
            arcadeFont = Font.createFont(Font.TRUETYPE_FONT, new File("Font/BattleCity.ttf")).deriveFont(30f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(arcadeFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        this.setNewMenu();
    }
    public void setNewMenu(){
        this.setBackground(Color.BLACK);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setMenuComponents();
        this.setup();
    }
    public void resetMenu(){
        this.removeAll();
        this.setup();
        startButton.setText("PLAY AGAIN");
    }
    public void hideThis(){
        this.setVisible(false);
        frame.remove(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startButton){
            preparePauseWindow();
            hideThis();
            gamePanel.setVisible(true);
            gamePanel.startGame();
        }
        else if(e.getSource() == continueButton){
            if(nextLevel){
                this.setNextLevel();
                nextLevel = false;
            }
            else{
                this.continueGame();
            }
        }
        else if(e.getSource() == restart){
            if(gamePanel.controlObject.endGame){
                gamePanel.controlObject.endGame = false;
                restart.setText("RESTART");
                preparePauseWindow();
            }
            setGamePanel(true);
        }
        else if(e.getSource() == exit){
            System.exit(0);
        }
    }
    public void prepareLoseWindow(){
        this.remove(continueButton);
        this.restart.setText("PLay AGAIN");
    }
    public void preparePauseWindow(){
        this.removeAll();
        this.add(Box.createVerticalStrut(50));
        this.add(gameTitle);
        this.add(Box.createVerticalStrut(50));
        this.add(continueButton);
        this.add(Box.createVerticalStrut(20));
        this.add(restart);
        this.add(Box.createVerticalStrut(20));
        this.add(exit);
    }
    public void prepareNextLevelWindow(){
        nextLevel = true;
        continueButton.setText("NEXT LEVEL");
    }
    public void setGameState(String gameState){
        this.gameState.setText(gameState);
        this.gameState.setVisible(true);
    }
    private void continueGame(){
        hideThis();
        gamePanel.setVisible(true);
        gamePanel.isRunning = true;
        gamePanel.controlMusic.playMusic();
    }
    private void setup(){
        this.add(Box.createVerticalStrut(50));
        this.add(gameTitle);
        this.add(Box.createVerticalStrut(30));
        this.add(gameState);
        this.add(Box.createVerticalStrut(20));
        this.add(startButton);
        this.add(Box.createVerticalStrut(15));
        this.add(exit);
        this.setGamePanel(false);
    }
    private void setNextLevel(){
        frame.upLevel();
        continueButton.setText("CONTINUE");
        setGamePanel(true);
    }
    private void setGamePanel(boolean visible){
        gamePanel = new GamePanel(frame);
        frame.add(gamePanel);
        frame.pack();
        gamePanel.setVisible(visible);
        if(visible){
            hideThis();
            gamePanel.startGame();
        }
    }
    private void setProperty(JButton button, String buttonName){
//        button.setIcon(new ImageIcon(filePath));
        button.setText(buttonName);
        button.setForeground(Color.RED);
        button.setFont(arcadeFont);
        button.setBorderPainted(false);
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.addActionListener(this);
    }
    private void setProperty(JLabel label, String filePath){
        label.setIcon(new ImageIcon(filePath));
        label.setAlignmentX(CENTER_ALIGNMENT);
    }
    private void setMenuComponents(){
        this.setProperty(gameTitle, "png/BattleCity.png");
        this.setProperty(startButton, "START");
        this.setProperty(continueButton, "CONTINUE");
        this.setProperty(exit, "EXIT");
        this.setProperty(restart, "RESTART");
        this.gameState.setVisible(false);
        this.gameState.setFont(arcadeFont.deriveFont(25f));
        this.gameState.setForeground(Color.GREEN);
//        this.gameState.setOpaque(true);
        this.gameState.setAlignmentX(CENTER_ALIGNMENT);
    }
}
