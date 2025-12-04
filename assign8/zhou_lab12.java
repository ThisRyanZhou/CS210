import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class zhou_lab12 {
    public static void main(String[] args){
        JFrame frame = new JFrame("Hello");
        frame.setSize(700, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem readSortFile = new JMenuItem("Read sort file");
        JMenuItem readSearchFile = new JMenuItem("Read search file");
        JMenuItem exit = new JMenuItem("Exit");

        readSortFile.addActionListener(new MenuItemActionListener());
        readSearchFile.addActionListener(new MenuItemActionListener());
        exit.addActionListener(new MenuItemActionListener());
        fileMenu.add(readSortFile);
        fileMenu.add(readSearchFile);
        fileMenu.add(exit);
        menuBar.add(fileMenu);

        JPanel leftPanel = new JPanel();
        leftPanel.setBorder(new javax.swing.border.LineBorder(new Color(0,0,0,225),2));
        leftPanel.setLayout(new GridBagLayout());
        leftPanel.setPreferredSize(new Dimension(330, 350));
        leftPanel.setMinimumSize(new Dimension(330, 350));

        JPanel rightPanel = new JPanel();
        rightPanel.setBorder(new javax.swing.border.LineBorder(new Color(0,0,0,255), 2));
        rightPanel.setLayout(new GridBagLayout());
        rightPanel.setMinimumSize(new Dimension(330, 350));
        rightPanel.setPreferredSize(new Dimension(330, 350));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new javax.swing.border.LineBorder(new Color(0,0,0,255), 2));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        buttonPanel.add(leftPanel);
        buttonPanel.add(rightPanel);

        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(new javax.swing.border.LineBorder(new Color(0,0,0,255), 2));
        mainPanel.setLayout(new BorderLayout());

        mainPanel.add(menuBar, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        frame.setContentPane(mainPanel);
        frame.validate();
        frame.setVisible(true);
    }
    static class MenuItemActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            if (cmd.equals("Exit")) {
                System.out.println("exit");
                System.exit(0);
            }
            else if (cmd.equals("Read sort file")) {
                System.out.println("read sort file");
            }
            else if (cmd.equals("Read search file")) {
                System.out.println("read search file");
            }
        }
    }

    static class ButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Button clicked: " + e.getActionCommand());
        }
    }
}
