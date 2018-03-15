package wirtualnyswiat;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import wirtualnyswiat.krolestwo.roslin.Guarana;
import wirtualnyswiat.krolestwo.roslin.Mlecz;
import wirtualnyswiat.krolestwo.roslin.Trawa;
import wirtualnyswiat.krolestwo.zwierzat.Antylopa;
import wirtualnyswiat.krolestwo.zwierzat.Lew;
import wirtualnyswiat.krolestwo.zwierzat.Owca;
import wirtualnyswiat.krolestwo.zwierzat.Panda;
import wirtualnyswiat.krolestwo.zwierzat.Wilk;


public class FrameMoja extends javax.swing.JFrame {
    
    private Swiat swiat = null;
    private javax.swing.JButton[][] table = null;
            
    public FrameMoja() {
        
        
       this.setSize(525, 630);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       Toolkit tk = Toolkit.getDefaultToolkit();
       Dimension dim = tk.getScreenSize();
       
       int xPos = (dim.width/2) - (this.getWidth()/2);
       int yPos = (dim.height/2) - (this.getHeight()/2);
       
       this.setLocation(xPos,yPos);
       this.setResizable(false);
       this.setTitle("Mateusz Borzyszkowski - 157996");
       

// PANEL GL
       JPanel tlo = new JPanel();

        swiat = new Swiat((int)System.currentTimeMillis());     
        table = new javax.swing.JButton[20][20];
        
        for (int i = 0; i < 20; i++)
        {
            for (int j = 0; j < 20; j++)
            {
                Dimension wymiar = new Dimension(25,25);
                table[i][j] = new JButton();
                table[i][j].setMargin(new Insets(0,0,0,0));
                table[i][j].setPreferredSize(wymiar);
                table[i][j].setMaximumSize(wymiar);
                table[i][j].setMinimumSize(wymiar);
                table[i][j].setBackground(Color.green);
                table[i][j].setForeground(Color.black);
                table[i][j].setSize(wymiar);
                table[i][j].setLocation(i*26,j*26);
                table[i][j].setText(" ");
                this.add(table[i][j]);
            }
        }       
       

       Dimension wymiar_przycisku = new Dimension (150,60);
      
       JButton nextRound = new JButton();
       nextRound.setSize(wymiar_przycisku);
       nextRound.setLocation(10, 530);
       nextRound.setBackground(Color.LIGHT_GRAY);
       nextRound.setForeground(Color.black);
       nextRound.setText("Wykonaj Ture");
       nextRound.addActionListener(this::nextRound_OnClick);
       this.add(nextRound);
       
       JButton saveButton = new JButton();
       saveButton.setSize(wymiar_przycisku);
       saveButton.setLocation(185, 530);
       saveButton.setBackground(Color.LIGHT_GRAY);
       saveButton.setForeground(Color.black);
       saveButton.setText("Zapisz Swiat");
       saveButton.addActionListener(this::saveButton_OnClick);
       this.add(saveButton);
       
       JButton loadButton = new JButton();
       loadButton.setSize(wymiar_przycisku);
       loadButton.setLocation(355, 530);
       loadButton.setBackground(Color.LIGHT_GRAY);
       loadButton.setForeground(Color.black);
       loadButton.setText("Wczytaj Swiat");
       loadButton.addActionListener(this::loadButton_OnClick);
       this.add(loadButton);

       this.add(tlo);
       refresh();
    }
    
    public static void main(String args[]) 
    {
            new FrameMoja().setVisible(true);
    }
    
private void nextRound_OnClick(java.awt.event.ActionEvent x)
    {
        swiat.wykonajTure();
        refresh();
    }
    
    private void saveButton_OnClick(java.awt.event.ActionEvent evt){
        String s = "";
        for (int i = 0; i < 20; i++)
        {
            for (int j = 0; j < 20; j++)
            {
                Organizm x = swiat.getOrganizm(new Point(i, j));
                if (x != null) s = s + x.toString() + "\n";
            }
        }
        try 
        {
            File newTextFile = new File(System.getProperty("user.dir") + "\\swiat.txt");
            java.io.FileWriter fw = new java.io.FileWriter(newTextFile);
            fw.write(s);
            fw.close();
        } 
        catch (java.io.IOException iox) 
        {
            iox.printStackTrace();
        }
    }
    
    private void loadButton_OnClick(java.awt.event.ActionEvent evt){
        swiat.clear();
        try {

            java.util.List<String> lines = java.nio.file.Files.readAllLines(java.nio.file.Paths.get(System.getProperty("user.dir") + "\\swiat.txt"), StandardCharsets.UTF_8);
                for (int i = 0; i < lines.size(); i++)
                {
                    String s = lines.get(i);
                    java.util.List<String> splits = Arrays.asList(s.split(" "));
                
                    char znak = splits.get(0).charAt(0);
                    int sila = Integer.parseInt(splits.get(1));
                    int inicjatywa = Integer.parseInt(splits.get(2));
                    int wiek = Integer.parseInt(splits.get(3));
                    int x = Integer.parseInt(splits.get(4));
                    int y = Integer.parseInt(splits.get(5));
                    Point pozycja = new Point(x, y);
                    Organizm organizm = null;
                
                    switch (znak)
                    {
                        case 'T':
                            organizm = new Trawa(pozycja, swiat);
                        break;
                        case 'G':
                            organizm = new Guarana(pozycja, swiat);
                        break;
                        case 'M':
                            organizm = new Mlecz(pozycja, swiat);
                        break;
                        case 'O':
                            organizm = new Owca(pozycja, swiat);
                        break;
                        case 'A':
                            organizm = new Antylopa(pozycja, swiat);
                        break;
                        case 'P':
                            organizm = new Panda(pozycja, swiat);
                        break;
                        case 'W':
                            organizm = new Wilk(pozycja, swiat);
                        break;
                        case 'L':
                            organizm = new Lew(pozycja, swiat);
                        break;
                    }
                    organizm.setSila(sila);
                    organizm.setInicjatywa(inicjatywa);
                    organizm.setWiek(wiek);
                
                    swiat.dodajOrganizm(organizm);
                }
        }
        catch (java.io.IOException e)
        {
            e.printStackTrace();
        }
        refresh();
    }
    
    private void refresh()
    {
        for (int i = 0; i < 20; i++)
        {
            for (int j = 0; j < 20; j++)
            {
                Organizm organizm = swiat.getOrganizm(new Point(i, j));
                if (organizm == null) table[i][j].setText("");
                else table[i][j].setText(Character.toString(organizm.getZnak()));
            }
        }
    }
}