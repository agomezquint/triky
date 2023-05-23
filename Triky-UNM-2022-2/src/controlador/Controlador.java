package controlador;

import java.lang.*;

import interfaz.*;
import mundo.*;
import persistencia.DBMovimientos;

// Importar el paquete del mundo y de la interfaz.

public class Controlador
{   
 // Atributos controlables de la interfaz. 	    
    private InterfazApp interfazApp;
    private PanelTriky pnlTriky;
    private boolean gameOver;
    
 // Atributo del mundo.
    private Triky triky;
	
	
 // Constructor	
	public Controlador()
	{ // Instanciar la clase principal del modelo.
	     triky = new Triky();
	}
	
 // Recibe las referencias de los objetos a controlar en la interfaz	
	public void conectar( InterfazApp interfazApp, PanelTriky pnlTriky )
	{ this.interfazApp = interfazApp;
	  this.pnlTriky = pnlTriky;
	}

	public boolean getGame() { return gameOver; }
	
 // --------------------------------------------------------------	
 // Implementacion de los requerimientos de usuario.	
 // --------------------------------------------------------------

	/*
	 * Inicia un nuevo juego
	 */
	public void newGame()
	{ triky = null; triky = new Triky();	 
	  gameOver = false;
	  interfazApp.newGame();
	}
	
	/*
	 * Elimina el aprendizaje
	 */
	public void resetDB()
	{
            boolean borra=false;
            borra =new DBMovimientos().borraMovimientos();
	}
	
	/*
	 * Activa o desativa aprender
	 */
	public void learn()
	{ triky.setLearn();	
          
	}
	
    public void machinePlays()
    { String vec[];
      triky.setRandFirst(pnlTriky.getOption());
      
      triky.machinePlays(); 
      
      vec = (triky.getStatus().split(","));
      interfazApp.setMachine(Integer.parseInt(vec[0]), Integer.parseInt(vec[1]));
      pnlTriky.setMode(triky.getMode());
      
      if (triky.getGame())
      {   gameOver = true; pnlTriky.setGame(" Game over..."); 
          if (triky.getMode().charAt(triky.getMode().length()-1) == 'A')
              pnlTriky.setWinner("Machine win");
          else 
          if (triky.getMode().charAt(triky.getMode().length()-1) == 'W')
        	  pnlTriky.setWinner("Human win");
          else
        	  pnlTriky.setWinner("There is no winner");
          
      }
    }

    public void humanPlayed(int i, int j)
    { triky.humanPlayed(i, j);     	
    }

}