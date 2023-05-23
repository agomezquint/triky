package interfaz;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import controlador.*;

public class PanelOpciones extends JPanel implements ActionListener
{
 // Atributos de la clase 		
    private JButton btnNewGame, btnRstData; 
	private JRadioButton iwtLearn;
	
 // Atributo controlador
	private Controlador ctrl;
	
 // Constructor   
    public PanelOpciones( Controlador ctrl  ) 
    {   	
    // ..............................................( T, L, B, R ).............................................
       setBorder( new CompoundBorder( new EmptyBorder( 0, 0, 0, 0 ), new TitledBorder( "" ) ) );
       setLayout( null );
	
    // Integra el controlador
       this.ctrl = ctrl;
       
    // Instancia atributos de la clase 
       btnNewGame = new JButton("New game");
       btnRstData = new JButton("Reset D.B");
       iwtLearn = new JRadioButton(" I want to learn ", false);
       
       btnNewGame.addActionListener(this);
       btnRstData.addActionListener(this);
       iwtLearn.addActionListener(this);
       
       btnNewGame.setBounds(5, 10, 100, 30);
       btnRstData.setBounds(110, 10, 90, 30);
       iwtLearn.setBounds(200, 10, 120, 30);
       
    // Agrega los atributos al panel   
       add(btnNewGame); add(btnRstData); add(iwtLearn);
    }


    public void actionPerformed(ActionEvent e) 
    {
	   if (e.getActionCommand().equals("New game"))
	   {   ctrl.newGame();		
                 iwtLearn.setSelected(false);
		// System.exit(ABORT); // Cierra el programa
	   }
	   else
	   if (e.getActionCommand().equals("Reset D.B"))	
	   {   
               ctrl.resetDB();
               ctrl.newGame();
	   }
	   else
           if (e.getActionCommand().equals(" I want to learn "))	
	   {   System.out.println("Entra");
               ctrl.learn();
	   }
		  
    } 
}