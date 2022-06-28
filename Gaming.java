import java.util.Scanner;
import javax.swing.JFrame;
public class Gaming {
    public static void main(String[] args) {
        JFrame obj =new JFrame();
        gameplay gp1=new gameplay();
         
        obj.setBounds(80,100,700,600);
        obj.setTitle("Breaking wall");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gp1);
    }
}
