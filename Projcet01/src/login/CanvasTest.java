package login;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.ScrollPane;
 
public class CanvasTest {
    public static void main(String[] args) {
        Frame f = new Frame("Canvas Test");
        f.setSize(300, 200);
 
        ScrollPane sp = new ScrollPane();
        Panel p = new Panel();
        p.setBackground(Color.pink);
        p.add(new Button("1st"));
        p.add(new Button("2nd"));
        p.add(new Button("3rd"));
        p.add(new Button("4th"));
 
        sp.add(p);
        f.add(sp);
        f.setVisible(true);
    }
}
