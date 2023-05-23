package interfaz;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import controlador.*;

public class PanelTriky extends JPanel
{
 // Atributos de la clase 		
    private JLabel lblTriky, lblMode, lblWinner;
    private JRadioButton optRandom, optFirstBlank;
    private ButtonGroup gpoOpt; 
    
 // Constructor   
    public PanelTriky(  ) 
    {   	
    // ..............................................( T, L, B, R ).............................................
       setBorder( new CompoundBorder( new EmptyBorder( 0, 0, 0, 0 ), new TitledBorder( "" ) ) );
       setLayout( null );
	    
    // Instancia atributos de la clase 
       lblTriky = new JLabel(" Hello, triky world..");
       lblTriky.setHorizontalAlignment( JLabel.CENTER );
       lblTriky.setBounds(0, 0, 122, 25);
    
       lblWinner = new JLabel("Winner?");
       lblWinner.setHorizontalAlignment( JLabel.CENTER );
       lblWinner.setBounds(0, 140, 122, 25);

       lblMode = new JLabel("");
       lblMode.setHorizontalAlignment( JLabel.CENTER );
       lblMode.setBounds(0, 165, 122, 25);

       optRandom = new JRadioButton("Random", true);
       optFirstBlank = new JRadioButton("First blank");
       optRandom.setBounds(5, 30, 122, 25);
       optFirstBlank.setBounds(5, 60, 122, 25);
       
       gpoOpt = new ButtonGroup();
       gpoOpt.add(optRandom); gpoOpt.add(optFirstBlank);
       
    // Agrega los atributos en el panel   
       add(lblTriky); add(lblWinner); add(lblMode); add(optRandom); add(optFirstBlank);
              
    } 
    
    public boolean getOption() 
    { if (optRandom.isSelected()) return true;
      return false;	
    }
    
    public void setGame(String text) { lblTriky.setText(text);}
    public void setMode(String text) { lblMode.setText(text); }
    public void setWinner(String text) { lblWinner.setText(text); }

}