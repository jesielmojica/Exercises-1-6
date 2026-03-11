import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RegistrationApp extends Application {

    @Override
    public void start(Stage primaryStage) {

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        Label nameLabel = new Label("Student Name:");
        TextField nameField = new TextField();

        Label courseLabel = new Label("Course Code:");
        ComboBox<String> courseBox = new ComboBox<>();
        courseBox.getItems().addAll("CMP 269", "CMP 167", "CMP 338", "CMP 326");
        courseBox.setPromptText("Select a Course");

        Button registerButton = new Button("Register");

        Label statusLabel = new Label();

        registerButton.setOnAction(e -> {
            String name = nameField.getText();
            String course = courseBox.getValue();

            if (name.isEmpty() || course == null) {
                statusLabel.setText("Please enter name and select a course.");
            } else {
                statusLabel.setText(
                    "Registration Successful for " + name + " in " + course + "!"
                );
            }
        }
        );

        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);

        grid.add(courseLabel, 0, 1);
        grid.add(courseBox, 1, 1);

        grid.add(registerButton, 1, 2);
        grid.add(statusLabel, 0, 3, 2, 1);

        Scene scene = new Scene(grid, 400, 250);
        primaryStage.setTitle("Lehman Course Registration");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}