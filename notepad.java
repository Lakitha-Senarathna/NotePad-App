import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
import java.io.*; 

public class notepad extends JFrame {
    
    JTextArea area;     // text area for writing
    JFileChooser chooser; // file chooser for open/save

    public Notepad() {
        // basic window setup
        setTitle("My Notepad");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        area = new JTextArea();
        chooser = new JFileChooser();

        // add scroll bar to text area
        JScrollPane sp = new JScrollPane(area);
        add(sp, BorderLayout.CENTER);

        // menu bar
        JMenuBar bar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem newFile = new JMenuItem("New");
        JMenuItem openFile = new JMenuItem("Open");
        JMenuItem saveFile = new JMenuItem("Save");
        JMenuItem exit = new JMenuItem("Exit");

        // actions for menu items
        newFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                area.setText("");
            }
        });

        openFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int i = chooser.showOpenDialog(null);
                if(i == JFileChooser.APPROVE_OPTION) {
                    File f = chooser.getSelectedFile();
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(f));
                        area.read(br, null);
                        br.close();
                    } catch(Exception ex) {
                        JOptionPane.showMessageDialog(null, "Cannot open file!");
                    }
                }
            }
        });

        saveFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int i = chooser.showSaveDialog(null);
                if(i == JFileChooser.APPROVE_OPTION) {
                    File f = chooser.getSelectedFile();
                    try {
                        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
                        area.write(bw);
                        bw.close();
                    } catch(Exception ex) {
                        JOptionPane.showMessageDialog(null, "Cannot save file!");
                    }
                }
            }
        });

        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // add items to menu
        fileMenu.add(newFile);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.add(exit);

        bar.add(fileMenu);
        setJMenuBar(bar);
    }

    public static void main(String[] args) {
        Notepad n = new Notepad();
        n.setVisible(true);
    }
}
