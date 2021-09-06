import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class test extends JFrame implements ActionListener {
    boolean hasStart;
    MyPanel panel;
    JButton button, button2,button3;
    Panel p = new Panel(this);
    public test(){
//        //this.setFocusable(true);
//        button = new JButton("Game");
//        button.setBounds(250,500,100,100);
//        button.addActionListener(this);
//        button2 = new JButton("button2");
//        button2.setBounds(250,300,100,100);
//        button2.addActionListener(this);
//        button3 = new JButton("exit");
//        button3.setBounds(250,400,100,100);
//        button3.addActionListener(this);
//        this.setResizable(false);
//        this.add(button);
//        this.add(button2);
//        this.add(button3);
        this.add(p);
        this.pack();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        hasStart = true;

        this.setFocusable(true);

    }
    public static void main(String[] args) {
        new test();
    }

    public static void dutchNationalFlag(){
        int[] arr = {2,2,2,2,2,2,2,2,2,2,2,2,2,1,1,1,1,1,0,0,0,0,0,0,0};
        int start = 0;
        int end = arr.length-1;
        int index = 0;
        while(index <= end){
            switch (arr[index]){
                case 0 -> swap(arr,index++,start++);
                case 1 -> index++;
                case 2 -> swap(arr,index,end--);
            }
        }
        for(int i: arr) System.out.println(i);
    }
    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            System.out.println("pressed button");
        }
        if(e.getSource() == button2){
            System.out.println("pressed button2");
        }
        if(e.getSource() == button3){
            System.exit(0);
        }
        if(hasStart){
            panel = new MyPanel();
            this.add(panel);
            this.setVisible(true);
            this.remove(button);
//            this.remove(p);
            p.setVisible(false);
            this.remove(button2);
            panel.startGame();
            hasStart = false;
        }
    }
    public class LeftAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}