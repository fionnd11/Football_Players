import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

//import java.util.Date;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

// for text font and color
import javafx.scene.text.Font;
import javafx.scene.paint.Color;

/* for background image
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Group;
import javafx.scene.paint.ImagePattern;
*/

import javafx.stage.Stage;

public class Launcher extends Application implements Serializable 
{
	private static final long serialVersionUID = 1L;

	// Instance Variables
	PlayerManager pm = new PlayerManager();
	Scene scene1;
	Scene scene2; // Used for adding Player
	GridPane gridPane1;
	VBox vBox1;
	
	@Override
	public void start(Stage primaryStage) 
	{
		
		Text myText = new Text(150, 50, "USE RELEVANT OPTION TO PROCEED:- \n");
        Font serif1 = Font.font("Serif", 20);
        myText.setFont(serif1);
        myText.setFill(Color.GREEN);		
		
		TextArea myOutput = new TextArea();
 		myOutput.setPrefHeight(100); //sets height of the TextArea 
 		myOutput.setPrefWidth(20); //sets width of the TextArea
	
		// Text for top of scene 2
		Text myText2 = new Text(150, 50, "FILL IN PLAYER'S INFORMATION: \n" );
        Font serif2 = Font.font("Serif", 20);
        myText2.setFont(serif2);
        myText2.setFill(Color.GREEN);		
			
		TextArea myOutput2 = new TextArea();
		myOutput2.setPrefHeight(100); //sets height of the TextArea 
		myOutput2.setPrefWidth(20); //sets width of the TextArea
		
		Button buttonLoadDB = new Button("Load Players' DB");
		TextField tfDBPath = new TextField();
		tfDBPath.setPromptText("Enter DataBase Path");
		
		buttonLoadDB.setOnAction(e -> 
		{
			if (tfDBPath.getText().trim().equals(""))  // If text field is empty
			{
				myOutput.setText("");
				myOutput.appendText("Please enter path to football players' DB \n");
			} 
			else 
			{
				pm = pm.loadDB(tfDBPath.getText());
				if (pm == null) 
				{
					myOutput.setText("ERROR: DB path " + tfDBPath.getText() + " does not exist \n");
					myOutput.appendText("Please check DB path and try again");
					tfDBPath.clear();
				} 
				else 
				{
					myOutput.setText("DB loaded successfully from " + tfDBPath.getText());
					tfDBPath.clear();
				}
			}
		});
		
		// Add Player
		Button buttonAdd = new Button("Add Player");
				
		TextField tfPlayerID = new TextField();
		tfPlayerID.setPromptText("Jersey No:");

		TextField tfPlayerFirstName = new TextField();
		tfPlayerFirstName.setPromptText("First Name:");
		
		TextField tfPlayerSurname = new TextField();
		tfPlayerSurname.setPromptText("Last Name:");
	
		TextField tfPlayerDoB = new TextField();
		tfPlayerDoB.setPromptText("Date of Birth (dd/mm/yy):");

		TextField tfPlayerClub = new TextField();
		tfPlayerClub.setPromptText("Club:");
		
		Button addPlayerDetailsBtn = new Button("Add Player");		
		
		Button cancelScene2 = new Button("Cancel Adding Player");

		
		// Setting the scene to Stage
		buttonAdd.setOnAction(e -> primaryStage.setScene(scene2));
		addPlayerDetailsBtn.setOnAction(e -> 
		{
			// If any of the Player fields are empty print prompt message
			if ( tfPlayerID.getText().trim().equals("") || 
			     tfPlayerFirstName.getText().trim().equals("") || 
				 tfPlayerSurname.getText().trim().equals("") || 
				 tfPlayerDoB.getText().trim().equals("") ||
				 tfPlayerClub.getText().trim().equals("")) { 
				myOutput2.setText("");
				myOutput2.appendText("All fields are mandatory \n");
			} 
			else 
			{								
				// Create new Player with information in text fields
				Player player = new Player(tfPlayerID.getText(), tfPlayerFirstName.getText(), tfPlayerSurname.getText(),						
									          tfPlayerDoB.getText(), tfPlayerClub.getText());			

				pm.add(player); // Add player to player list
				// Print success message
				myOutput.setText("Player " + tfPlayerID.getText() +
						" has been added to the Database");
				// Clear input fields
				tfPlayerID.clear();
				tfPlayerFirstName.clear();
				tfPlayerSurname.clear();
				tfPlayerDoB.clear();
				tfPlayerClub.clear();				
				// Return to scene 1
				primaryStage.setScene(scene1);				
			}
		});
		
		// Cancel button action for scene 2. If the user decides not to add a 
		// Player the can go back to the main scene.
		cancelScene2.setOnAction(e -> 
		{
			// Clear input fields
			tfPlayerID.clear();
			tfPlayerFirstName.clear();
			tfPlayerSurname.clear();
			tfPlayerDoB.clear();
			tfPlayerClub.clear();
			// Return to scene 1
			primaryStage.setScene(scene1);
		});
		
		// Delete Player
		Button buttonDelete = new Button("Remove Player");
		TextField tfPlayerDel = new TextField();
		tfPlayerDel.setPromptText("Enter Jersey No");
		
		buttonDelete.setOnAction(e -> 
		{
			if (tfPlayerDel.getText().trim().equals("")) { // If text field is empty
				myOutput.setText("Please enter the Player's Jersey Number you want to remove");
			} 
			else 
			{
				boolean deleteStatus;
				deleteStatus = pm.delete(tfPlayerDel.getText());
				if (deleteStatus == true) 
				{
					myOutput.setText("Player " + tfPlayerDel.getText() + " removed");
					tfPlayerDel.clear();
				} 
				else 
				{
					myOutput.setText("Player " + tfPlayerDel.getText() + " not found \n");
					myOutput.appendText("No Player removed!");
					tfPlayerDel.clear();
				}
			}
		});
		
		// Search by ID
		Button buttonSearchByID = new Button("Search by Jersey Number");
		TextField tfSearchID = new TextField();
		tfSearchID.setPromptText("Enter Jersey Number");
		buttonSearchByID.setOnAction(e -> 
		{
			if (tfSearchID.getText().trim().equals("")) 
			{
				myOutput.setText("Please enter the Jersey Number you want to search for");
			} 
			else 
			{
				Player playerObj = pm.getPlayerByID(tfSearchID.getText());
				if (playerObj != null) 
				{
					myOutput.setText(playerObj.toString());
				} else 
				{
					myOutput.setText("No Player Found with Jersey Number " + tfSearchID.getText());
				}
				tfSearchID.clear();
			}
		});
		
		// Search by First Name
		Button buttonSearchByFirstName = new Button("Search by First Name");
		TextField tfSearchFirstName = new TextField();
		tfSearchFirstName.setPromptText("Enter First Name");
		
		buttonSearchByFirstName.setOnAction(e -> 
		{
			if (tfSearchFirstName.getText().trim().equals("")) 
			{
				myOutput.setText("Please enter the Player's First Name you want to search for");
			} 
			else 
			{
				List<Player> sameNamesList = pm.getPlayersByFirstName(tfSearchFirstName.getText());
				if (!(sameNamesList == null)) 
				{
					for (Player player : sameNamesList) 
					{
						myOutput.setText(player.toString());
					}
				} 
				else 
				{
					myOutput.setText("No Player found with First Name: " + tfSearchFirstName.getText());
					tfSearchFirstName.clear();
				}
			}
		});
		
		// Show total number of players
		Button buttonShowTotal = new Button("Total Players in the list");
		TextField tfTotalNumberOfPlayers = new TextField();
		tfTotalNumberOfPlayers.setEditable(false); // This box is not editable. Only displays result.
		tfTotalNumberOfPlayers.setPromptText("0");
		
		buttonShowTotal.setOnAction(e -> 
		{
			tfTotalNumberOfPlayers.setText(Integer.toString(pm.findTotalPlayers()));
		});
		
		
		Button buttonSaveDB = new Button("Save the Database");
		
		buttonSaveDB.setOnAction(e -> 
		{
			try 
			{
	    		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("playersDB.ser"));
	    		out.writeObject(pm);
	    		out.close();
	    		myOutput.setText("Players' Database saved in project directory");
	    	} 
			catch (Exception exception) 
			{
	    		System.out.print("[Error] Can't save DB. Cause: ");
	    		exception.printStackTrace();
	    		myOutput.setText("ERROR: Failed to save Player's Database!");
	    	}
		});
		
		Button buttonQuit = new Button("Quit the Application");	
		buttonQuit.setOnAction(e -> Platform.exit());
			
		Button buttonReset = new Button("Reset the Form");	
		buttonReset.setOnAction(e -> 
		{
			tfDBPath.clear();
			tfPlayerDel.clear();			
			tfSearchID.clear();			
			tfSearchFirstName.clear();
			tfTotalNumberOfPlayers.clear();
			myOutput.setText("");			
		});

/*
		// Display All Players - Not Complete
		// enter DB path before this
		Button buttonAll = new Button("Display All Players");		
		buttonAll.setOnAction(e -> 
		{
			if (tfDBPath.getText().trim().equals("")) { // If text field is empty
				myOutput.setText("");
				myOutput.appendText("Please enter path to football players' DB \n");
		} 
		else {} 
		else 
		{
			myOutput.setText("Empty DB " + tfDBPath.getText());
			tfDBPath.clear();
		}
			}
		});
*/		
			
		Text myText3 = new Text(50, 50, "MESSAGES & WARNINGS: \n" );
        Font serif3 = Font.font("Serif", 20);
        myText3.setFont(serif3);
        myText3.setFill(Color.RED);		
        
		gridPane1 = new GridPane();
		vBox1 = new VBox();
		
		// Setting the padding
		gridPane1.setPadding(new Insets(15, 15, 15, 15));
		vBox1.setPadding(new Insets(15, 15, 15, 15));
		
		// Setting the vertical and horizontal gaps between the columns
		gridPane1.setVgap(15);
		gridPane1.setHgap(15);
		
		// Setting the Grid alignment
		gridPane1.setAlignment(Pos.CENTER);
		vBox1.setAlignment(Pos.CENTER_LEFT);
		
		// Arranging all the nodes in the grid
		// left side column
		gridPane1.add(myText, 0, 0);
		gridPane1.add(buttonLoadDB, 0, 1);
		gridPane1.add(buttonAdd, 0, 2);
		gridPane1.add(buttonDelete, 0, 3);		
		gridPane1.add(buttonSearchByID, 0, 4);
		gridPane1.add(buttonSearchByFirstName, 0, 5);		
		gridPane1.add(buttonShowTotal, 0, 6);
		gridPane1.add(buttonSaveDB, 0, 7);
		gridPane1.add(buttonQuit, 0, 8);
		
		// right side column
		gridPane1.add(tfDBPath, 1, 1);
		//gridPane1.add(tfPlayerID, 1, 2); // no textbox required next to add player button
		gridPane1.add(tfPlayerDel, 1, 3);
		gridPane1.add(tfSearchID, 1, 4);
		gridPane1.add(tfSearchFirstName, 1, 5);
		gridPane1.add(tfTotalNumberOfPlayers, 1, 6);
		gridPane1.add(buttonReset, 1, 7);
				
		gridPane1.add(myText3, 0, 10);
		gridPane1.add(myOutput, 0, 11, 2, 1); // messages in output box

		vBox1.setSpacing(10);
		
		// Arranging all the nodes in a vertical box for scene 2 player add
		vBox1.getChildren().addAll(myText2, tfPlayerID, tfPlayerFirstName, tfPlayerSurname, tfPlayerDoB, tfPlayerClub, addPlayerDetailsBtn, 
								   cancelScene2,myOutput2);

		/* Preparing the Scene */
		// Create a Scene by passing the root group object, height and width

		scene1 = new Scene(gridPane1, 1200, 650);
		scene2 = new Scene(vBox1, 1200, 650);		
			
		/* Preparing the Stage (i.e. the container of any JavaFX application) */

		// Setting the title to Stage.
		primaryStage.setTitle("Football Players Manager Application");

		// Setting the scene to Stage
		primaryStage.setScene(scene1);
	
		// Displaying the stage
		primaryStage.show();
	}

	public static void main(String[] args) 
	{
		launch(args);
	}
}
