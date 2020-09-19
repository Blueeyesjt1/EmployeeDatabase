import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;

public class Controller {

  @FXML   //Product input
  private TextField txt_ProductName;

  @FXML   //Manufacture input
  private TextField txt_Manufacturer;

  @FXML   //Type box
  private ChoiceBox<?> choice_Type;

  @FXML   //Produce box //
  private ListView<?> list_Produce;

  @FXML   //Quantity box
  private ComboBox<String> combo_quantity;

  /**
   * Initialized method used to set the quantity combobox values only (for now)
   */
  public void initialize() {
    System.out.println("Launched program");

    for (int i = 0; i < 10; i++) {
      String number = String.valueOf(i + 1);
      combo_quantity.getItems().add(i, number);
    }
    combo_quantity.setEditable(true);
    combo_quantity.getSelectionModel().selectFirst();
  }

  /**
   * Product button call
   */
  @FXML
  void but_AddProduct(ActionEvent event) {
    System.out.println("Product added");
  }

  /**
   * Product record button call
   */
  @FXML
  void but_RecordProduction(ActionEvent event) {
    System.out.println("Product recorded");
  }

  /**
   * Calls and grabs data from database
   */
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

      //String empIDString = txtEmpID.getText();

      String sql = "SELECT EMAIL, FIRST_NAME, LAST_NAME " + "FROM EMPLOYEES "
          + "WHERE EMPLOYEE_ID = " /*+ empIDString*/;

      ResultSet rs = stmt.executeQuery(sql);
      rs.next();
      String empEmail = rs.getString(1);
      String empFirstName = rs.getString(2);
      String empLastName = rs.getString(3);
      //System.out.println(empFirstName + " " + empLastName + " " + empEmail);

      //empOutput.setText("Employee name is " + empFirstName + " " + empLastName + ", email is " + empEmail);

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