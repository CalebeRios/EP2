package fase1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Application{
    private static JTextField text = new JTextField();
 
    public Application() {        
        menu();
    }
    
    private static void game(){
        JFrame game = new JFrame();
        
        game.add(new Map(text.getText()));
        
        game.setTitle("Space Combat Game");
        game.setSize(Game.getWidth(), Game.getHeight());
        game.setResizable(false);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setLocationRelativeTo(null);
        game.setVisible(true);
    }
    
    private static void menu(){
        JFrame menu = new JFrame();
        JButton Ranking = new JButton();
        JButton ExitGame = new JButton();
        JPanel panel = new JPanel();
        JButton StartGame = new JButton();
        Ranking rank = new Ranking();
        JLabel title = new JLabel();
        
        menu.setTitle("Space Combat Game");
        menu.setSize(Game.getWidth(), Game.getHeight());
        menu.setResizable(false);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
    
        panel.setLayout(null);
        panel.setFocusable(true);
        panel.setDoubleBuffered(true);
        panel.setBackground(Color.black);
        
        title.setText("Space And Aliens");
        title.setFont(new Font("Comic Sans", Font.ITALIC, 30));
        title.setForeground(Color.WHITE);
        title.setBounds(130, 100, 500, 40);    
        
        StartGame.setText("Start Game");
        StartGame.setBounds((Game.getWidth()-150 ) / 2, 200, 150, 40);
        StartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.dispose();
                getNome();
            }
        });
        
        Ranking.setText("Ranking");
        Ranking.setBounds((Game.getWidth()-150 ) / 2, 300, 150, 40);
        Ranking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.dispose();
                try{rank.list();} catch(FileNotFoundException ex){} catch (IOException ex) {}
            }
        });
        
        ExitGame.setText("Exit Game");
        ExitGame.setBounds((Game.getWidth()-150 ) / 2, 400, 150, 40);
        ExitGame.setPreferredSize(new Dimension(150, 40));
        ExitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        
        panel.add(title);
        panel.add(StartGame);
        panel.add(Ranking);
        panel.add(ExitGame);
    
        menu.add(panel);
    
    }
    
    private static void getNome(){
        JFrame nome = new JFrame();
        JPanel panel = new JPanel();
        JButton enter = new JButton();
        JLabel label = new JLabel();
        JButton back = new JButton("Back");
        
        panel.setLayout(null);

        text.setBounds(150, 200, 200, 30);
        
        enter.setText("Jogue agora!");
        enter.setBounds(175, 300, 150, 40);
        
        back.setText("Back");
        back.setBounds(0, 427, 150, 40);
        
        label.setText("Nome sem espaços em branco!");
        label.setBounds(160, 230, 200, 30);
        
        nome.setTitle("Space Combat Game");
        nome.setSize(Game.getWidth(), Game.getHeight());
        nome.setResizable(false);
        nome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nome.setLocationRelativeTo(null);
        nome.setVisible(true);
        
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(text.getText().indexOf(" ") == -1){
                    nome.dispose();

                    EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {                
                            game();
                        }
                    });
                }else{
                    label.setBounds(165, 230, 200, 30);
                    label.setText("Retire os espaços em branco!");
                }
            }
        });
        
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nome.dispose();
                Application app = new Application();
            }
        });

        panel.add(text);
        panel.add(enter);
        panel.add(label);
        panel.add(back);

        nome.add(panel);
    }
    
    public static void main(String[] args) {
        
        Application app = new Application();

    }
}
