package Classes;

import Controllers.UserInterfaceController;
import Database.DatabaseStatus;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.SQLException;

public class UserInterface {

    private DatabaseStatus databaseStatus = new DatabaseStatus();

    private String email, password;

    public void start(ActionEvent event, UserInformation userInformation) throws Exception {
        // Loads userInterface layout.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/layout/userInterface_layout.fxml"));
        Pane userInterface = loader.load(); // userInterface holds the loader information
        Scene scene = new Scene(userInterface); // new scene holding userInterface

        // Calling the controller class before we load it to pass information.
        UserInterfaceController userInterfaceController = loader.<UserInterfaceController>getController();
        String studentName = getDBInformation(userInformation);
        userInterfaceController.setStudentName(studentName); // setStudentname to String studentName

        // Stage now under windows, calls scene and loads the scene.
        Stage windows = (Stage) ((Node)event.getSource()).getScene().getWindow(); // hides previous window
        windows.setScene(scene);
        windows.show();
    }

    public String getDBInformation(UserInformation userInformation) throws SQLException {
        this.email = userInformation.getEmail();
        this.password = userInformation.getPassword();
        String studentName = databaseStatus.searchName(this.email, this.password);

        return studentName;
    }


}
