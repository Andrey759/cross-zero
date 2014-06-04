package Controller.Classes;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class MoveFrame {
    public static void toCenter (JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setBounds(x, y, frame.getWidth(), frame.getHeight());
    }
}
