package Assignment3;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class UI extends Application{
	
	RadioButton rbInsert;
	RadioButton rbSearch;
	RadioButton rbUpdate;
	RadioButton rbResult;
	RadioButton rbGrade;
	Control control;
	
	//textfields
	TextField tfID = new TextField("ID");
	TextField tfStudentName = new TextField("Student Name");
	TextField tfQuiz = new TextField("Quiz");
	TextField tfA1 = new TextField("A1");
	TextField tfA2 = new TextField("A2");
	TextField tfA3 = new TextField("A3");
	TextField tfExam = new TextField("Exam");
	
	TextField tfAttribute = new TextField("Attribute");
	TextField tfValue = new TextField("Value");
	
	TextField tfNewValue = new TextField("New Value");
	
	
	
	@Override
	public void start(Stage primaryStage) {
		
		control = new Control();
		
		//create radio buttons and add them to the toggle group
		rbInsert = new RadioButton("Insert Record");
		rbSearch = new RadioButton("Search");
		rbUpdate = new RadioButton("Update");
		rbResult = new RadioButton("Calculate Result");
		rbGrade = new RadioButton("Calculate Grade");
		
		ToggleGroup toggle = new ToggleGroup();
		rbInsert.setToggleGroup(toggle);
		rbSearch.setToggleGroup(toggle);
		rbUpdate.setToggleGroup(toggle);
		rbResult.setToggleGroup(toggle);
		rbGrade.setToggleGroup(toggle);
		
		//proceed button
		Button btProceed = new Button("Proceed");
		btProceed.setOnAction(e -> showProceed());
		
		//put in a pane
		VBox vbox = new VBox();
		vbox.getChildren().addAll(rbInsert, rbSearch, rbUpdate, rbResult, rbGrade);
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(vbox);
		borderPane.setRight(btProceed);
		
		Scene sc = new Scene(borderPane);
		primaryStage.setTitle("University Grading System");
		primaryStage.setScene(sc);
		primaryStage.show();
		
		
	}
	
	private void showProceed() {
		
				
		if(rbInsert.isSelected()) {
			
			VBox vbox = new VBox();
					
			//button
			Button btProceed = new Button("Proceed");
			btProceed.setOnAction(e -> proceedInsert());
			
			//setup pane
			vbox.getChildren().addAll(tfID, tfStudentName, tfQuiz, tfA1, tfA2, tfA3, tfExam);
			BorderPane pane = new BorderPane();
			pane.setTop(vbox);
			pane.setRight(btProceed);
			
			//set the scene
			Scene sc = new Scene(pane);
			Stage insertStage = new Stage();
			insertStage.setScene(sc);
			insertStage.show();
			
			
			
			
			
			
		}
		
		if(rbSearch.isSelected()) {
			VBox vbox = new VBox();
			
			//button
			Button btSearch = new Button("Search");
			btSearch.setOnAction(e -> searchDatabase());
			
			//setup pane
			vbox.getChildren().addAll(tfAttribute, tfValue);
			BorderPane pane = new BorderPane();
			pane.setTop(vbox);
			pane.setRight(btSearch);
			
			//set the scene
			Scene sc = new Scene(pane);
			Stage searchStage = new Stage();
			searchStage.setScene(sc);
			searchStage.show();
		}
		
		if(rbUpdate.isSelected()) {
			VBox vbox = new VBox();
			
			//button
			Button btUpdate = new Button("Update");
			btUpdate.setOnAction(e -> updateDatabase());
			
			//setup pane
			vbox.getChildren().addAll(tfID, tfAttribute, tfNewValue);
			BorderPane pane = new BorderPane();
			pane.setTop(vbox);
			pane.setRight(btUpdate);
			
			//set the scene
			Scene sc = new Scene(pane);
			Stage searchStage = new Stage();
			searchStage.setScene(sc);
			searchStage.show();
			
		}
		
		if(rbResult.isSelected()) {
			
			VBox vbox = new VBox();
			
			//button
			Button btGetResult = new Button("Get Result");
			btGetResult.setOnAction(e -> getResult());
			
			//setup pane
			vbox.getChildren().addAll(tfQuiz, tfA1, tfA2, tfA3, tfExam);
			BorderPane pane = new BorderPane();
			pane.setTop(vbox);
			pane.setRight(btGetResult);
			
			//set the scene
			Scene sc = new Scene(pane);
			Stage searchStage = new Stage();
			searchStage.setScene(sc);
			searchStage.show();
			
			
		}
		
		if(rbGrade.isSelected()) {
			
VBox vbox = new VBox();
			
			//button
			Button btGetGrade = new Button("Get Grade");
			btGetGrade.setOnAction(e -> getGrade());
			
			//setup pane
			vbox.getChildren().addAll(tfQuiz, tfA1, tfA2, tfA3, tfExam);
			BorderPane pane = new BorderPane();
			pane.setTop(vbox);
			pane.setRight(btGetGrade);
			
			//set the scene
			Scene sc = new Scene(pane);
			Stage searchStage = new Stage();
			searchStage.setScene(sc);
			searchStage.show();
			
		}
		
		
		
	}
	
	
	
	private void getGrade() {
		double result = control.calculateResults(Integer.parseInt(tfQuiz.getText()), Integer.parseInt(tfA1.getText()), Integer.parseInt(tfA2.getText()), Integer.parseInt(tfA3.getText()), Integer.parseInt(tfExam.getText()));

		String grade = control.calcultateGrade(result);
		
		VBox vbox = new VBox();
		vbox.getChildren().add(new Text("Grade: " + grade));
		Scene sc = new Scene(vbox);
		Stage stage = new Stage();
		stage.setScene(sc);
		stage.show();
	}

	private void getResult() {
		double result = control.calculateResults(Integer.parseInt(tfQuiz.getText()), Integer.parseInt(tfA1.getText()), Integer.parseInt(tfA2.getText()), Integer.parseInt(tfA3.getText()), Integer.parseInt(tfExam.getText()));
		
		VBox vbox = new VBox();
		vbox.getChildren().add(new Text("Result: " + Double.toString(result)));
		Scene sc = new Scene(vbox);
		Stage stage = new Stage();
		stage.setScene(sc);
		stage.show();
	}

	private void updateDatabase() {
		control.updateRecord(tfID.getText(), tfAttribute.getText(), tfNewValue.getText());
	}

	private void searchDatabase() {
		String result = control.search(tfAttribute.getText(), tfValue.getText());
		
		VBox vbox = new VBox();
		vbox.getChildren().add(new Text(result));
		Scene sc = new Scene(vbox);
		Stage stage = new Stage();
		stage.setScene(sc);
		stage.show();
	}

	private void proceedInsert() {
		
		//calculate results and grade
		try {
			Double result = control.calculateResults(Integer.parseInt(tfQuiz.getText()), Integer.parseInt(tfA1.getText()), 
					Integer.parseInt(tfA2.getText()), Integer.parseInt(tfA3.getText()), Integer.parseInt(tfExam.getText()));
			String grade = control.calcultateGrade(result);
			
			//insert
			control.insertRecord(Integer.parseInt(tfID.getText()), tfStudentName.getText(), Integer.parseInt(tfQuiz.getText()), 
					Integer.parseInt(tfA1.getText()), Integer.parseInt(tfA2.getText()), Integer.parseInt(tfA3.getText()), 
					Integer.parseInt(tfExam.getText()), result, grade);
			
			
			
							
		}
		catch(NumberFormatException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("Please ensure you use the correct format for the numeric values");
			alert.showAndWait();
		}
		
		
		
		
	}

	public static void main(String[] args) {
		
		launch(args);
	}
	
	

}
