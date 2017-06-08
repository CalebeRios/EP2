/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Collections.sort;
import java.util.Comparator;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


/**
 *
 * @author caleberios
 */
public class Ranking {
    private static ArrayList<Player> players;

    public void list() throws FileNotFoundException, IOException{
        JFrame rank = new JFrame();
        JPanel panel = new JPanel();
        JLabel label = new JLabel();
        JButton back = new JButton();
        players = new ArrayList();
        StringBuffer buffer = new StringBuffer();
        String message = null;
        BufferedReader ranking;
        String path;
        path = this.getClass().getResource("").getPath();
        if(path.contains("jar!")){
            ranking = new BufferedReader(new FileReader(path.replace("file:", "").replace("dist/EP2.jar!/game/", "assets/doc/ranking.txt")));        
        }
        else{
            ranking = new BufferedReader(new FileReader(path.replace("build/classes/game/", "assets/doc/ranking.txt")));
        }


        while(ranking.ready() ){
            String linhas = ranking.readLine();

            int i = linhas.length();

            char player[] = linhas.toCharArray();
            String nome = "";
            String num = "";
            char cletra;
            int score = 0;

            int j = 0;

            while((cletra = linhas.charAt(j)) != ' '){
                nome += cletra;
                j++;
            }

            j++;

            while(j < i){
                cletra = linhas.charAt(j);

                num += cletra;

                j++;
            }

            score = Integer.parseInt(num);

            players.add(new Player(nome, score));
        }

        sort(players, new Comparator(){
           public int compare(Object o1, Object o2){
               Player p1 = (Player) o1;
               Player p2 = (Player) o2;
               return (p1.getScore() > p2.getScore() ? -1 : (p1.getScore() < p2.getScore() ? +1 : 0));
           }
        });

        int i = 1;

        for(Player player : players){
            buffer.append(i + ". " + player.getName() + " " + player.getScore());
            buffer.append('\n');
            i++;
        }

        rank.setTitle("Space and Aliens");
        rank.setSize(Game.getWidth(), Game.getHeight());
        rank.setResizable(false);
        rank.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rank.setLocationRelativeTo(null);
        rank.setVisible(true);

        back.setText("Back");
        back.setBounds(0, 427, 150, 40);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rank.dispose();
                Application app = new Application();
            }
        });

        panel.setLayout(null);

        message = buffer.toString();

        label.setText("Os Melhores!");
        Font font = new Font("Lobster", Font.ITALIC, 30);
        label.setFont(font);
        label.setBounds((Game.getWidth() - 175) /2 , 40, 200, 40);

        JTextArea text = new JTextArea(message);
        text.setEditable(false);
        text.setBackground(label.getBackground());
        text.setFont(new Font("Noto Sans", Font.BOLD, 14));
        text.setBounds((Game.getWidth() - 125) /2 , 80, 150, 400);

        panel.add(back);
        panel.add(label);
        panel.add(text);


        rank.add(panel);
    }

    public void insert(Player player) throws IOException{
        String path;
        BufferedWriter ranking;
        path = this.getClass().getResource("").getPath();
        if(path.contains("jar!")){
            ranking = new BufferedWriter(new FileWriter(path.replace("file:", "").replace("dist/EP2.jar!/game/", "assets/doc/ranking.txt"), true));        
        }
        else{
            ranking = new BufferedWriter(new FileWriter(path.replace("build/classes/game/", "assets/doc/ranking.txt"), true));
        }
        
        ranking.append(player.getName() + " " + player.getScore());

        ranking.newLine();

        ranking.close();
    }
}
