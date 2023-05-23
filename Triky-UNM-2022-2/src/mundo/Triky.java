package mundo;

import java.util.Arrays;
import persistencia.DBMovimientos;

public class Triky 
{
// Atributos de instancia
   private final int triky[][];
   private final String strik[][];
   private String status;
   private boolean gameOver, swRandFirst;
   private boolean swLearn;
   private int f0, f1, f2, c0, c1, c2, d1, d2;
   private int clics; 
   private int firstDefense, lastDefense, firstRandom, lastRandom;
   private String mode; 
   private Match match[];
   
   public Triky()
   { triky = new int[3][3];
     strik = new String[3][3];
     for (int i=0; i<3; i++)
      for (int j=0; j<3; j++)
      {	   triky[i][j] = 0; strik[i][j] = ""; 
      }
     swRandFirst = true;
     gameOver = false;
     clics = 0;
     mode = "";
     match = new Match[9];
   }

   /*
    * Activa o desactiva aprender
    */
   public void setLearn() { swLearn = !swLearn;  System.out.println(swLearn);}
   
   public boolean getLearn() {  return swLearn;}
   
   /*
    * Retorna la coordena de juego en ataque, defenza, azar o primer blanco
    */
   public String getStatus() { return status; }
   
   /*
    * Retorna si el estado del juego si ha terminado o contin�a
    */
   public boolean getGame() { return gameOver; }
   
   /*
    * swRandFirst: Determina si la m�quina juega al azar o al primer blanco encontrado
    */
   public void setRandFirst(boolean sw) { swRandFirst = sw; }
   
   /*
    * Retorna el modo de juego
    */
   public String getMode() { return mode; }
   
   /*
    * Verifica si el juego ha terminado
    */
   private boolean isGameOver()
   { 
	 f0 = triky[0][0]*100 + triky[0][1]*10 + triky[0][2];
	 f1 = triky[1][0]*100 + triky[1][1]*10 + triky[1][2];
	 f2 = triky[2][0]*100 + triky[2][1]*10 + triky[2][2];
	       
	 c0 = triky[0][0]*100 + triky[1][0]*10 + triky[2][0];
	 c1 = triky[0][1]*100 + triky[1][1]*10 + triky[2][1];
	 c2 = triky[0][2]*100 + triky[1][2]*10 + triky[2][2];
	       
	 d1 = triky[0][0]*100 + triky[1][1]*10 + triky[2][2];
	 d2 = triky[0][2]*100 + triky[1][1]*10 + triky[2][0]; 

	 if (f0 == 111 || f1 == 111 || f2 == 111) { match("W"); return true; }
         if (c0 == 111 || c1 == 111 || c2 == 111) { match("W"); return true; }
         if (d1 == 111 || d2 == 111)              { match("W"); return true; }
         if (clics == 8)                          { match("H"); return true; }
         match("H"); return false;  
   }
   
   /*
    * Humano se identifica con 1, la maquina con 2.
    */
   public void machinePlays()
   { String m3, m4, m5; int value;
	 gameOver = isGameOver(); 	
	 
     //System.out.println("machinePlays() " + swLearn);
     
  // Genera estados predefinidos para ataque y defensa     
     if (!gameOver)															  
     {            																	      // ----------------- ATAQUE (ok) ----------------------
    	 if (f0 ==  22) { triky[0][0] = 2; status = "0,0"; match("A"); gameOver = true; } // Analisis de las filas 
	     else
	     if (f0 == 202) { triky[0][1] = 2; status = "0,1"; match("A"); gameOver = true; }
	     else
	     if (f0 == 220) { triky[0][2] = 2; status = "0,2"; match("A"); gameOver = true; }
	     else
	     if (f1 ==  22) { triky[1][0] = 2; status = "1,0"; match("A"); gameOver = true; }
	     else
	     if (f1 == 202) { triky[1][1] = 2; status = "1,1"; match("A"); gameOver = true; }
	     else
	     if (f1 == 220) { triky[1][2] = 2; status = "1,2"; match("A"); gameOver = true; }
	     else
	     if (f2 ==  22) { triky[2][0] = 2; status = "2,0"; match("A"); gameOver = true; }
	     else
	     if (f2 == 202) { triky[2][1] = 2; status = "2,1"; match("A"); gameOver = true; }
	     else
	     if (f2 == 220) { triky[2][2] = 2; status = "2,2"; match("A"); gameOver = true; }
	     else 	 
	     if (c0 ==  22) { triky[0][0] = 2; status = "0,0"; match("A"); gameOver = true; } // Analisis de las columnas 
	     else
	     if (c0 == 202) { triky[1][0] = 2; status = "1,0"; match("A"); gameOver = true; }
	     else
	     if (c0 == 220) { triky[2][0] = 2; status = "2,0"; match("A"); gameOver = true; }
	     else
	     if (c1 ==  22) { triky[0][1] = 2; status = "0,1"; match("A"); gameOver = true; }
	     else
	     if (c1 == 202) { triky[1][1] = 2; status = "1,1"; match("A"); gameOver = true; }
	     else
	     if (c1 == 220) { triky[2][1] = 2; status = "2,1"; match("A"); gameOver = true; }
	     else
	     if (c2 ==  22) { triky[0][2] = 2; status = "0,2"; match("A"); gameOver = true; }
	     else
	     if (c2 == 202) { triky[1][2] = 2; status = "1,2"; match("A"); gameOver = true; }
	     else
	     if (c2 == 220) { triky[2][2] = 2; status = "2,2"; match("A"); gameOver = true; }
	     else
	     if (d1 ==  22) { triky[0][0] = 2; status = "0,0"; match("A"); gameOver = true; } // Analisis de la diagonal positiva+ 
	     else
	     if (d1 == 202) { triky[1][1] = 2; status = "1,1"; match("A"); gameOver = true; }
	     else
	     if (d1 == 220) { triky[2][2] = 2; status = "2,2"; match("A"); gameOver = true; }
	     else
	     if (d2 ==  22) { triky[0][2] = 2; status = "0,2"; match("A"); gameOver = true; } // Analisis de la diagonal negativa-
	     else
	     if (d2 == 202) { triky[1][1] = 2; status = "1,1"; match("A"); gameOver = true; }
	     else
	     if (d2 == 220) { triky[2][0] = 2; status = "2,0"; match("A"); gameOver = true; }
	     else   											 			 // ----------------- DEFENSA ---------------------- 
	     if (f0 ==  11) { triky[0][0] = 2; status = "0,0"; match("D"); } // Analisis de las filas
	     else
	     if (f0 == 101) { triky[0][1] = 2; status = "0,1"; match("D"); }
	     else
	     if (f0 == 110) { triky[0][2] = 2; status = "0,2"; match("D"); }
	     else
	     if (f1 ==  11) { triky[1][0] = 2; status = "1,0"; match("D"); }
	     else
	     if (f1 == 101) { triky[1][1] = 2; status = "1,1"; match("D"); }
	     else
	     if (f1 == 110) { triky[1][2] = 2; status = "1,2"; match("D"); }
	     else
	     if (f2 ==  11) { triky[2][0] = 2; status = "2,0"; match("D"); }
	     else
	     if (f2 == 101) { triky[2][1] = 2; status = "2,1"; match("D"); }
	     else
	     if (f2 == 110) { triky[2][2] = 2; status = "2,2"; match("D"); }
	     else 	 
	     if (c0 ==  11) { triky[0][0] = 2; status = "0,0"; match("D"); } // Aa�lisis de las columnas 
	     else
	     if (c0 == 101) { triky[1][0] = 2; status = "1,0"; match("D"); }
	     else
	     if (c0 == 110) { triky[2][0] = 2; status = "2,0"; match("D"); }
	     else
	     if (c1 ==  11) { triky[0][1] = 2; status = "0,1"; match("D"); }
	     else
	     if (c1 == 101) { triky[1][1] = 2; status = "1,1"; match("D"); }
	     else
	     if (c1 == 110) { triky[2][1] = 2; status = "2,1"; match("D"); }
	     else
	     if (c2 ==  11) { triky[0][2] = 2; status = "0,2"; match("D"); }
	     else
	     if (c2 == 101) { triky[1][2] = 2; status = "1,2"; match("D"); }
	     else
	     if (c2 == 110) { triky[2][2] = 2; status = "2,2"; match("D"); }
	     else
	     if (d1 ==  11) { triky[0][0] = 2; status = "0,0"; match("D"); } // Analisis de la diagonal positiva+ 
	     else
	     if (d1 == 101) { triky[1][1] = 2; status = "1,1"; match("D"); }
	     else
	     if (d1 == 110) { triky[2][2] = 2; status = "2,2"; match("D"); }
	     else
	     if (d2 ==  11) { triky[0][2] = 2; status = "0,2"; match("D"); } // Analisis de la diagonal negativa-
	     else
	     if (d2 == 101) { triky[1][1] = 2; status = "1,1"; match("D"); }
	     else
	     if (d2 == 110) { triky[2][0] = 2; status = "2,0"; match("D"); }
	     else							 					 		  // Azar, firstBlank o experiencia
	     { gameOver = false;
                 System.out.println(swLearn);
	       if (swLearn)
	       {   
                   if (swRandFirst) 
	           {   random(); /*strik[0][0] = Integer.toString(clics+1) + "m";*/ match("R"); 
	           }
	           else
	           {   firstBlank(); /*strik[0][0] = Integer.toString(clics+1) + "m";*/ match("F"); 
	           }
	       }
	       else
	       {   
                   System.out.println(swLearn);
                   random(); match("K");
	       }
	     }
      // view();
     } 
     else
     { if (mode.charAt(mode.length()-1) == 'W') 
       { 
    	 lastRandom = mode.lastIndexOf("R");
         System.out.println("lastRandom = " + lastRandom); 
         
         if ( lastRandom >=0 ){
            System.out.println("save status = " + match[lastRandom]);
            String movimiento = match[lastRandom].toString();
            boolean guardar =false;
            guardar = new DBMovimientos().InsertarMov(movimiento.substring(2,11));
         }
         
       }
       else
       if (mode.charAt(mode.length()-1) == 'A')
       {   //System.out.println("gameOver & A");
       }
     }
  // viewS(); // Para que es util strik[][]?
     viewMatch();
   }

   /*
    * Guarda el estado del juego en memoria
    */
   private void match(String mode)
   { this.mode+=mode; match[clics] = new Match(toString(), this.mode); clics++; 
     //System.out.println(">> match & clics " + toString() + " " + clics);
   }
   
   /*
    * Juega el humano
    */
   public void humanPlayed(int i, int j)
   { //System.out.println("humanPlayed()");
	 triky[i][j] = 1; strik[i][j] = Integer.toString(clics+1) + "h"; 
   }
   
   /*
    * Guarda un estado de aprendizaje
    */
   private void save(String status, int value, int row, int col)
   { System.out.println("save(status, value, row, col) " + status + " " + value + " " + row + " " + col);
   }
   
   /*
    * Juega con la experiencia del conocimiento
    */
   private boolean knowloge()
   { System.out.println("Utilizaria el conocimiento..");  
	 return swLearn;   
   }
   
   /* 
    * Convertir estado a String
    */
   public String toString()
   { String striky="";
   
     for (int i=0;i<3;i++)
      for (int j=0;j<3;j++)
    	   striky = striky + (triky[i][j]+"");
	 return striky;
   }
   
   
   
   /*
   Valida existencia del movimeinto
   */
    public boolean validaMovimiento(int i, int j){
        boolean respuesta= false;
        String valido="";
        int validar [][]= new int[3][3];
        if (triky[i][j] == 0) 
        {   
            for (int b=0; b<3; b++){
                for (int c=0; c<3; c++){
                  validar[b][c] = triky[b][c];
                }
            }
            validar[i][j] = 2;
            for (int x=0;x<validar.length;x++){
                for (int z=0;z<validar.length;z++){
                    valido=valido+validar[x][z];
                }
            }
            boolean consultar = new DBMovimientos().consultarMov(valido);
            if (!consultar){
                respuesta=true;
            }
        }     
        return respuesta;
   }
   /*
    * Juega al azar en un blanco 
    */
  
   private void random()
   { int i, j;
    boolean valido=false;
    while (true)
     { i = (int)(Math.random() * 10)%3;
       j = (int)(Math.random() * 10)%3;
       if(!swLearn){
           valido =validaMovimiento(i,j);
           if (valido){
            triky[i][j] = 2; status = i+","+j; 
           break;
           }
       }
       else{
           if (triky[i][j] == 0){
               triky[i][j] = 2;status = i+","+j; 
               break;
           }  
       }      
     }
   }
   /*
    * Juega al primer blanco encontrado
    */
   private void firstBlank()
   { for (int i=0; i<3; i++)
	  for (int j=0; j<3; j++)
	   if (triky[i][j] == 0)
	   {   triky[i][j] = 2; status = i+","+j; i=j=10;
	    // System.out.println("first blank...");
	   }
   }

/* ----------------------------------------------------------------------------------- */
   
   /*
    * Visualiza la matriz triky de juego
    */
   private void view()
   { System.out.println(status);
	 System.out.println("-----------------------------------------------------------");
	 for (int i=0; i<3; i++)
	 { for (int j=0; j<3; j++)
	    System.out.print(triky[i][j]+" ");
	    System.out.println();		 
	 }
	 System.out.println("-----------------------------------------------------------");
   }
   
   private void viewS()
   { //System.out.println(status);
	 System.out.println("-----------------------------------------------------------");
	 for (int i=0; i<3; i++)
	 { for (int j=0; j<3; j++)
	    System.out.print(strik[i][j]+" ");
	    System.out.println();		 
	 }
	 System.out.println("-----------------------------------------------------------");
   }

   private void viewMatch()
   { //System.out.println(status);
	 System.out.println("-----------------------------------------------------------");
       for (Match match1 : match) {
           if (match1 != null) {
               System.out.print(match1.toString() + " ");
           }
       }
	 System.out.println();		 	 
	 System.out.println("-----------------------------------------------------------");
   }
}
