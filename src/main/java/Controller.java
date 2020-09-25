/*
  Programmer Name: Jaden Williams
  Description: Controller class that
       holds input variables and
       gateway to H2 database
  Date: 9/18/2020
 */

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.*;

//This comment is solely for testing git-committing. Please ignore.

public class Controller {

  @FXML   //Product input
  private TextField txt_ProductName;

  @FXML   //Manufacture input
  private TextField txt_Manufacturer;

  @FXML   //Type box
  private ChoiceBox<String> choice_Type;

  @FXML   //Produce box //
  private ListView<?> list_Produce;

  @FXML   //Quantity box
  private ComboBox<String> combo_quantity;

  /**
   * Initialized method used to set the quantity combobox values only (for now)
   */
  public void initialize() {
    System.out.println("Launched program");
    populateProductLineTabs();  //Populates item type dropdown
    populateItemQuantity(); //Populates quantity dropdown
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
    Connection conn = null; //Temporary
    Statement stmt = null; //Temporary

    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      //STEP 2: Open a connection
      conn = DriverManager.getConnection(DB_URL, USER, PASS);   //Security bug - Temporary placeholders, will be modified in future.

      //STEP 3: Execute a query
      stmt = conn.createStatement();  //Temporary database testing. Will be modified in the future.

      //String empIDString = txtEmpID.getText();

      //String sql = "SELECT EMAIL, FIRST_NAME, LAST_NAME " + "FROM EMPLOYEES "
      //    + "WHERE EMPLOYEE_ID = " /*+ empIDString*/;

      //ResultSet rs = stmt.executeQuery(sql);  //Temporary database testing. Will be modified in the future.
      //rs.next();
      //String empEmail = rs.getString(1);
      //String empFirstName = rs.getString(2);
      //String empLastName = rs.getString(3);
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

  /**
   * Populates quantity tab with digits 1 - 10
   */
  void populateItemQuantity() {
    for (int i = 0; i < 10; i++) {
      String number = String.valueOf(i + 1);
      combo_quantity.getItems().add(i, number);
    }
    combo_quantity.setEditable(true);
    combo_quantity.getSelectionModel().selectFirst();
  }


  /**
   * Populates all enum item types inside the itemType dropdown
   */
  void populateProductLineTabs() {
    ArrayList<ItemType> typeNames = new ArrayList<ItemType>();

    for(ItemType typeValue : ItemType.values()) {
      typeNames.add(typeValue);
    }
    System.out.println("type array size = " + typeNames.size());

    for(int i = 0; i < typeNames.size(); i++) {
      choice_Type.getItems().add(i, typeNames.get(i).toString());
    }
    choice_Type.getSelectionModel().selectFirst();
  }

}