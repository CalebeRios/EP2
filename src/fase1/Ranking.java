/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fase1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Collections.sort;
import java.util.Comparator;


/**
 *
 * @author caleberios
 */
public class Ranking {
    private static ArrayList<Player> players;
    
    public static void list() throws FileNotFoundException, IOException{
        players = new ArrayList();
        BufferedReader ranking = new BufferedReader(new FileReader("/home/caleberios/Documents/UnB/4Sem/OO/JAVA/EP2/Assets/doc/ranking.txt"));
        
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
        
        for(Player player : players){
            System.out.println(player.getName() + " " + player.getScore());
        }
    }    

    public static void insert(Player player) throws IOException{
        BufferedWriter ranking = new BufferedWriter(new FileWriter("/home/caleberios/Documents/UnB/4Sem/OO/JAVA/EP2/Assets/doc/ranking.txt", true));
        ranking.append(player.getName() + " " + player.getScore());
        
        ranking.newLine();
        
        ranking.close();
    }
}
