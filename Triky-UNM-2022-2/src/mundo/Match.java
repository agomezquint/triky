package mundo;

public class Match 
{
  private int value;
  private String status, mode;
  private int row, col;
  
  public Match(String status, String mode)
  { value = row = col = 0; 
    this.status = status;
    this.mode = mode;
  }
  
  public int getValue() { return value; }
  public String getStatus() { return status; }
  public int getRow() { return row; }
  public int getCol() { return col; }
  
  public void setValue(int value) { this.value = value; }
  public void setStatus(String status) { this.status = status; }
  public void setRow(int row) { this.row = row; }
  public void setCol(int col) { this.col = col; }
  public String toString() { return value + " " + status + " " + mode; }
}
  
  


