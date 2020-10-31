/*
  Programmer Name: Jaden Williams
  Description: Controller class that
       holds input variables and
       gateway to H2 database
  Date: 9/18/2020 - 10/30/2020
 */

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.*;
import javafx.scene.control.cell.PropertyValueFactory;

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

  @FXML   //Text log on last page
  private TextArea textLog;

  @FXML
  private TableView<Product> productTable;

  public ObservableList< Product.Widget > productLine = FXCollections.observableArrayList();

  /**
   * Initialized method used at program startup
   */
  public void initialize() {

    //Widget newProductTest = new Widget("iPod45", "Apple", ItemType.AUDIO);  //Test widget

    System.out.println("Launched program");
    tableViewSetup();
    populateProductLineTabs();  //Populates item type dropdown
    populateItemQuantity(); //Populates quantity dropdown
    testMultimedia(); //Testing
    testProductionRecord(); //Testing text log on last page
  }

  /**
   * Product button call
   */
  @FXML
  void but_AddProduct(ActionEvent event) {

    //Product testProduct = new Product("testName", "testManf", ItemType.valueOf(choice_Type.getValue()));
    //productLine.add(testProduct);

    /*for (Product productFound : productLine) {
     // productTable.getColumns().add(new TableRow<>());
    }*/

    System.out.println("Product added");
  }

  public void tableViewSetup() {
    TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
    productTable.getColumns().add(nameColumn);

    TableColumn<Product, String> manuColumn = new TableColumn<>("Manufacturer");
    manuColumn.setCellValueFactory(new PropertyValueFactory<>("Manufacturer"));
    productTable.getColumns().add(manuColumn);

    TableColumn<Product, ItemType> typeColumn = new TableColumn<>("Type");
    typeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
    productTable.getColumns().add(typeColumn);

    productLine.add(new Product.Widget("testName", "testManf", ItemType.VISUAL));

    productTable.getItems().add(productLine.get(0));
  }

  public void testMultimedia() {
    AudioPlayer newAudioProduct = new AudioPlayer("DP-X1A", "Onkyo", "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC", "M3U/PLS/WPL");
    Screen newScreen = new Screen("720x480", 40, 22);
    MoviePlayer newMovieProduct = new MoviePlayer("DBPOWER MK101", "OracleProduction", newScreen,
        MonitorType.LCD);
    ArrayList<MultimediaControl> productList = new ArrayList<MultimediaControl>();
    productList.add(newAudioProduct);
    productList.add(newMovieProduct);
    for (MultimediaControl p : productList) {
      System.out.println(p);
      p.Play();
      p.Stop();
      p.Next();
      p.Previous();
    }
  }

  public void testProductionRecord() {
    // test constructor used when creating production records from user interface
    Integer numProduced = 3;  // this will come from the combobox in the UI

    for (int productionRunProduct = 0; productionRunProduct < numProduced; productionRunProduct++) {
      ProductionRecord pr = new ProductionRecord(0);
      System.out.println(pr.toString());
    }

    // test constructor used when creating production records from reading database
    ProductionRecord pr = new ProductionRecord(0, 3, "1", new Date());

    textLog.setText(textLog.getText() + pr.toString());
    textLog.setText(textLog.getText() + "\n" + pr.getProductionNumber());
    textLog.setText(textLog.getText() + "\n" + pr.getProductID());
    textLog.setText(textLog.getText() + "\n" + pr.getSerialnumber());
    textLog.setText(textLog.getText() + "\n" + pr.getDateProduced());

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
      conn = DriverManager.getConnection(DB_URL, USER,
          PASS);   //Security bug - Temporary placeholders, will be modified in future.

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

    for (ItemType typeValue : ItemType.values()) {
      typeNames.add(typeValue);
    }
    System.out.println("type array size = " + typeNames.size());

    for (int i = 0; i < typeNames.size(); i++) {
      choice_Type.getItems().add(i, typeNames.get(i).toString());
    }
    choice_Type.getSelectionModel().selectFirst();
  }

}