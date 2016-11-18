package Ex2;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FxLayoutDemo extends Application {
	private static final String[] courses = { "Cumputer Science", "Business" };
	private static final String[] courseName = { "course1", "course2", "course3" };
	private static final ObservableList<String> options = FXCollections.observableArrayList();
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) {
		primaryStage.setTitle("JavaFX Welcome");
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Welcome");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Label userName = new Label("User Name:");
		grid.add(userName, 0, 1);

		TextField userTextField = new TextField();
		grid.add(userTextField, 1, 1);

		Label address = new Label("Address:");
		grid.add(address, 0, 2);

		TextField addressBox = new TextField();
		grid.add(addressBox, 1, 2);

		CheckBox cb1 = new CheckBox("check1");
		grid.add(cb1, 3, 1);

		CheckBox cb2 = new CheckBox("check2");
		grid.add(cb2, 3, 2);

		final ToggleGroup group = new ToggleGroup();
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				if (group.getSelectedToggle() != null) {
					if (group.getSelectedToggle().getUserData().toString().equals("cs")) {
						options.setAll(courses);
					}
					if (group.getSelectedToggle().getUserData().toString().equals("bs")){
						options.setAll(courseName);
					}
				}
			}
		});
		RadioButton rb1 = new RadioButton("cs");
		rb1.setUserData("cs");
		rb1.setToggleGroup(group);
		rb1.setSelected(true);
		grid.add(rb1, 4, 1);

		RadioButton rb2 = new RadioButton("bs");
		rb2.setUserData("bs");
		rb2.setToggleGroup(group);
		grid.add(rb2, 5, 1);
		
		ListView<String> list = new ListView<String>();
		ObservableList<String> items = FXCollections.observableArrayList();
		list.setItems(items);
		grid.add(list, 5, 3);

		final ComboBox<String> comboBox = new ComboBox<String>(options);
		grid.add(comboBox, 5, 2);
		comboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override 
            public void changed(ObservableValue ov, String t, String t1) {                
                String item = t1;   
                if (null != item && !items.contains(item)) {
                	items.add(item);
                }
            }    
        });
		
		Button btn = new Button("display");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 4);

		TextArea textArea = new TextArea();
		grid.add(textArea, 0, 5, 6, 1);

		final Text actiontarget = new Text();
		grid.add(actiontarget, 1, 6);

		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				textArea.appendText(userTextField.getText() + "," + addressBox.getText() + "\nCourse:");
				for (int i=0; i<items.size(); i++) {
					textArea.appendText("\n" + items.get(i));
				}
			}
		});

		Scene scene = new Scene(grid, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}