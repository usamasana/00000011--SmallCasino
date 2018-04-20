package test1;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author kaleb.nelson
 */
public class Craps extends Game
{
  
  
  @Override
  public void play()
  {
    
  }
  
  @Override
  public void returnHome() {}
  
  public Craps()
  {
    for(int i = 0; i < 2; i++)
    {
      Dice[i] = new Die();
    }
  }
  /**
   * Checks the first roll of the game; Uses a tracker variable inside the game
   * logic to check if needed
   */
  void checkFirstRoll()
  {
    //Gets the total value of the dice roll
    getDiceTotalValue(Dice[0],Dice[1]);
    
    //If/else block for checking of die
    //Checks if the user rolled 7 or 11, which is a win
    if(this.currentDiceSum == 7 || this.currentDiceSum== 11)
    {
      popupWindow("Congrats! You rolled a " + 
              Integer.toString(this.currentDiceSum) +"! You won" +
              Integer.toString(gamePlayer.getCurrentBet() * 2) +" dollars!");
      gamePlayer.user.addFunds(gamePlayer.getCurrentBet() * 2);
      gamePlayer.placeBet(0);
      Dice[0].setValue(0);
      Dice[1].setValue(0);
      this.targetDiceValue = 0;
    }
    
    //Checks if the user rolled a 2,3 or 12, which is an automatic loss
    else if(this.currentDiceSum == 2 || this.currentDiceSum == 3 || 
            this.currentDiceSum == 12)
    {
      popupWindow("Sorry! You rolled a " + 
              Integer.toString(this.currentDiceSum) + "! You lost your bet...");
      gamePlayer.placeBet(0);
      Dice[0].setValue(0);
      Dice[1].setValue(0);
      this.targetDiceValue = 0;
    }
    
    //Continues informs user to roll again
    else
    {
      popupWindow("You haven't won yet! Roll again for another chance to win!");
      this.targetDiceValue = this.currentDiceSum;
    }
  }
  
  /**
   * This function checks the next roll of the die, being implemented with a 
   * tracking variable inside of the game logic
   */
  void checkNextRoll()
  {
    getDiceTotalValue(Dice[0],Dice[1]);
    
    if(compareDiceValues() == true)
    {
    
      popupWindow("Congrats! You hit the target dice value!"
              + "You won " + Integer.toString(gamePlayer.getCurrentBet() * 2) + 
              "dollars!");
      
      gamePlayer.user.addFunds(gamePlayer.getCurrentBet() * 2);
      gamePlayer.placeBet(0);
      Dice[0].setValue(0);
      Dice[1].setValue(0);
      this.targetDiceValue = 0;
    }
    else if (this.currentDiceSum == 2 || this.currentDiceSum == 3 || 
            this.currentDiceSum == 7 || this.currentDiceSum == 12)
    {
      popupWindow("Sorry! You rolled a " + Integer.toString(this.currentDiceSum)
              + "! You lost your bet");
      gamePlayer.placeBet(0);
      Dice[0].setValue(0);
      Dice[1].setValue(0);
      this.targetDiceValue = 0;
    }
    
    else
    {
      popupWindow("You haven't hit your target value yet! Roll again!");
    }
  }
  /**
   * This function serves to check if currentDiceSum and targetDiceValue are 
   * are equal to one another within checkNextRoll()
   * @return true if the currentDiceSum and targetDiceValue are equal
   * @return false if currentDiceSum and targetDiceValue are not equal
   */
  boolean compareDiceValues()
  {
    if(this.currentDiceSum == this.targetDiceValue)
    {
      return true;
    }
    
    else
    {
      return false;
    }
  }
  /**
   * This function serves to add together the two values of the die objects
   * to give an integer for use in later functions.
   * @param one carries the first index of the Dice variable in the class
   * @param two carries the second index of the Dice variable in the class
   */
  private void getDiceTotalValue(Die one, Die two)
  {
    int dieSum;
    
    dieSum = one.getValue() + two.getValue();
    
    this.currentDiceSum = dieSum;
  }
  
  /**
   * This function will create a popup window with the text of input in the
   * window
   * @param input a string to be printed in the popup window
   */
  private void popupWindow(String input)
  {
    Stage popup = new Stage();
      
      popup.initModality(Modality.APPLICATION_MODAL);
      
      Label message = new Label(input);
     
      Button close = new Button("Close");
      close.setOnAction(e->popup.close());
      
      VBox layout= new VBox(10);
     
      
      layout.getChildren().addAll(message, close);
      
      layout.setAlignment(Pos.CENTER);
      
      Scene scene1= new Scene(layout, 300, 250);
      
      popup.setScene(scene1);
      
      popup.showAndWait();
  }
  
  //Variables for use in class
  public Die[] Dice = new Die[2];
  public CrapsPlayer gamePlayer = new CrapsPlayer("Johnny");
  public int targetDiceValue;
  private int currentDiceSum;
}
