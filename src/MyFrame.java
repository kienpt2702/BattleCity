import javax.swing.*;

public class MyFrame extends JFrame {
    public MyFrame(){
        MyPanel panel = new MyPanel();
        this.add(panel);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("TankGame");
        panel.startGame();
    }
}
