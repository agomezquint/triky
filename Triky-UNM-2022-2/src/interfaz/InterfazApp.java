package interfaz;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import controlador.Controlador;
import win.Win;

public class InterfazApp extends JFrame
{
 // Constantes
	private static final String TRIKY = "imagenes/triky.jpg";
	private static final String OOOOO = "imagenes/O.png";
	
 // Atributos de instancia
    private PanelOpciones pnlOpciones;
    private PanelTriky pnlTriky;
    private ImageIcon imgTriky, imgOoooo;
    private JLabel lblTriky, lblGame[][];
    
 // Atributo de tipo Controlador   
    private Controlador ctrl;
    
 // Constructor
    public InterfazApp( Controlador ctrl ) 
    { setTitle( "Triky." );  
   	  getContentPane( ).setLayout( null );
   	
   // Integra el Controlador. 
   	  this.ctrl = ctrl;
   	  
   // Instancia los paneles    
      pnlOpciones  = new PanelOpciones( this.ctrl );
      pnlTriky     = new PanelTriky();
      imgTriky     = new ImageIcon( TRIKY );
      lblTriky     = new JLabel();
      
      lblTriky.setIcon( imgTriky );
      lblGame      = new JLabel[3][3];
      
   // Instancia imagen   
      imgOoooo = new ImageIcon( OOOOO );
      
   // Instancia los label de la matriz de juego
      int x = 7, y = 7, width = 50, botton = 50;
      for(int i=0;i<3;i++)
      { for(int j=0;j<3;j++)
   	    { lblGame[i][j] = new JLabel( "" );
   	   // lblGame[i][j].setBorder( new CompoundBorder( new EmptyBorder( 0, 0, 0, 0 ), new TitledBorder( "" ) ) );
   	      lblGame[i][j].setHorizontalAlignment( JLabel.CENTER );
   	      lblGame[i][j].setVerticalAlignment( JLabel.CENTER );
   	      lblGame[i][j].setEnabled( true );
   	      lblGame[i][j].setBounds(x, y, width, botton);
   	      x += 65;
   	      lblGame[i][j].addMouseListener( new LabelClicMouse( i, j, lblGame[i][j], this.ctrl ) );
   	      add(lblGame[i][j]);
   	    }
        x = 7; y += 65;
      }       
            
   // Organizar el panel principal. 
      getContentPane( ).add( pnlTriky );
      pnlTriky.setBounds(200,5,130,190);      
      getContentPane( ).add( pnlOpciones );
      pnlOpciones.setBounds(5,200,325,50);
      getContentPane( ).add( lblTriky );      
      lblTriky.setBounds(0, 0, 325, 190);
      
   // Propiedades de la interfaz.   
      setSize( 350, 295 );      
      setResizable( false );
      setBackground(Color.WHITE);
      setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
   
  //  Conecta objetos al controlador.
      ctrl.conectar( this,  pnlTriky);
      
  //  Centrar ventana.
      Win.centrarVentana( this );
    }    

    public void newGame()
    { for(int i=0;i<3;i++)
      { for(int j=0;j<3;j++)
     	{ lblGame[i][j].setIcon(null);
       // lblGame[i][j].removeMouseListener( lblGame[i][j].getMouseListeners()[0] );
     	  lblGame[i][j].addMouseListener( new LabelClicMouse( i, j, lblGame[i][j], this.ctrl ) );
     	}
      }       	
    }
    
    
//  Ejecucion.		
    public static void main(String args[])
    { InterfazApp frmMain = new InterfazApp( new Controlador() );
      frmMain.setVisible( true );	  
    }
    
    public void setMachine(int i, int j)
    { lblGame[i][j].setIcon( imgOoooo );    	
    }
        
}

/**
 * Controlador de eventos del Mouse
 * @author Giovanni Fajardo Utria
 */
class LabelClicMouse extends MouseAdapter 
{// Constantes  
    private static final String OOOOO = "imagenes/O.png";
    private static final String EQUIS = "imagenes/X.png";
    
 // Atributos de clase
    private static boolean swClic;
    
 // Atributos de instancia   
    private JLabel label;
    private Controlador ctrl;
    private int x, y;
 // private ImageIcon imgRedBlock, imgBlueBlock;
    private ImageIcon imgOoooo, imgEquis;
    
 // Constructor   
    public LabelClicMouse( int x, int y, JLabel label, Controlador ctrl )
    { this.label = label;
      this.ctrl = ctrl;
      this.x = x; this.y = y;
      this.swClic = false;
      this.imgOoooo = new ImageIcon( OOOOO );
      this.imgEquis = new ImageIcon( EQUIS );
    }	
   
    //public void resetSwClic() { swClic = false; }
    
    public void mouseClicked(MouseEvent evento)
    { if (!ctrl.getGame())
      {   if ( evento.isMetaDown() )  // boton derecho del raton - pone X / O
	      {    if ((label.getText()).equals( "" ) && label.getIcon() == null && !swClic )
	           {    label.setIcon( imgEquis ); swClic = true;
	           }
	           else
	           if (label.getIcon() != null && label.getIcon().equals(imgEquis))
	           {   label.setIcon( null ); swClic = false;
	           }      
	      }
	      else
	      if ( evento.isAltDown() )  // boton medio del raton
	      {
	      }
	      else
	      if ( evento.isControlDown() ) // Control + boton izquierdo
	      {    
	      }
	      else	         
	      if ( (label.getText()).equals( "" ) && label.getIcon() != null && label.getIcon().equals(imgEquis) ) // boton izquierdo del raton
	      {     label.removeMouseListener( label.getMouseListeners()[0] );  
	            ctrl.humanPlayed(x, y); ctrl.machinePlays(); swClic = false;
	      }
      }
      else
      {   
      }
    }
}