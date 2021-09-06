import javax.swing.*;


public class MyFrame extends JFrame {
    public MyFrame(){
        this.add(new Panel(this));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("TankGame");
        this.setVisible(true);
    }

}
