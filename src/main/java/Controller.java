import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.*;

public class Controller {

    @FXML
    private Label empOutput;

    @FXML
    private TextField txtEmpID;
    //test

    @FXML
    void onButtonPressed(ActionEvent event) {
        connectDatabase();
    }   //test thing

    public void connectDatabase() {
        final String JDBC_DRIVER = "org.h2.Driver";
        final String DB_URL = "jdbc:h2:./res/HR";

        //  Database credentials
        final String USER = "";
        final String PASS = "";
        Connection conn = null;
        Statement stmt = null;

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            stmt = conn.createStatement();

            String empIDString = txtEmpID.getText();

            String sql = "SELECT EMAIL, FIRST_NAME, LAST_NAME " + "FROM EMPLOYEES " + "WHERE EMPLOYEE_ID = " + empIDString;

            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            String empEmail = rs.getString(1);
            String empFirstName = rs.getString(2);
            String empLastName = rs.getString(3);
            //System.out.println(empFirstName + " " + empLastName + " " + empEmail);

            empOutput.setText("Employee name is " + empFirstName + " " + empLastName + ", email is " + empEmail);

            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}