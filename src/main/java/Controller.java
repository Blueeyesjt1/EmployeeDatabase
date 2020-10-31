/*
  Programmer Name: Jaden Williams
      Description: Controller class that
                   holds input variables and
                   gateway to H2 database
             Date: 9/18/2020 - 10/30/2020
 */

import java.util.ArrayList;
import java.util.Date;
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
  private ChoiceBox<ItemType> choice_Type;

  @FXML   //Produce box //
  private ListView<Product> list_Produce;

  @FXML   //Quantity box
  private ComboBox<String> combo_quantity;

  @FXML   //Text log on last page
  private TextArea textLog;

  @FXML
  private TableView<Product> productTable;

  public int globalProductCount = 0;

  public ObservableList<Product> productLine = FXCollections.observableArrayList();

  TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
  TableColumn<Product, String> manuColumn = new TableColumn<>("Manufacturer");
  TableColumn<Product, ItemType> typeColumn = new TableColumn<>("Type");

  /**
   * Initialized method used at program startup
   */
  public void initialize() {

    //Widget newProductTest = new Widget("iPod45", "Apple", ItemType.AUDIO);  //Test widget

    System.out.println("Launched program");
    LoadDatabase();
    tableViewSetup();  //Sets up table structure
    populateProductLineTabs();  //Populates item type dropdown
    populateItemQuantity(); //Populates quantity dropdown
    //testMultimedia(); //Testing
    //testProductionRecord(); //Testing text log on last page
  }

  /**
   * Product button call
   */
  @FXML
  void but_AddProduct(ActionEvent event) {

    productLine.add(new Widget(txt_ProductName.getText(), txt_Manufacturer.getText(), choice_Type.getValue())); //Adding test product in observable list

    productTable.setItems(productLine);     //Adds product to table
    list_Produce.setItems(productLine);     //Adds product to produce list

    AddToDatabase();

    System.out.println("Product added");
  }

  public void tableViewSetup() {
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
    productTable.getColumns().add(nameColumn);

    manuColumn.setCellValueFactory(new PropertyValueFactory<>("Manufacturer"));
    productTable.getColumns().add(manuColumn);

    typeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
    productTable.getColumns().add(typeColumn);

/*
    productTable.getItems().add(new Product(productLine.get(0).name, productLine.get(0).manufacturer, ItemType.VISUAL_MOBILE));*/

  }

  public void AddToDatabase() {
    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./res/HR";

    //  Database credentials
    final String USER = "";
    final String PASS = "";
    Connection conn = null; //Temporary
    PreparedStatement stmt = null; //Temporary

    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      //STEP 2: Open a connection
      conn = DriverManager.getConnection(DB_URL, USER, PASS);   //Security bug - Temporary placeholders, will be modified in future.

      String sqlProductName = txt_ProductName.getText();
      String sqlManufName = txt_Manufacturer.getText();
      ItemType sqlItemType = choice_Type.getValue();

      stmt = conn.prepareStatement("INSERT INTO Product(type, manufacturer, name) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
      stmt.setString(1, sqlItemType.toString());
      stmt.setString(2, sqlManufName);
      stmt.setString(3, sqlProductName);

      stmt.executeUpdate();

      /*String sql = "SELECT EMAIL, FIRST_NAME, LAST_NAME " + "FROM EMPLOYEES "

      ResultSet rs = stmt.executeQuery(sql);  //Temporary database testing. Will be modified in the future.
      rs.next();
      String empEmail = rs.getString(1);
      String empFirstName = rs.getString(2);
      String empLastName = rs.getString(3);
      System.out.println(empFirstName + " " + empLastName + " " + empEmail);

      empOutput.setText("Employee name is " + empFirstName + " " + empLastName + ", email is " + empEmail);
      STEP 4: Clean-up environment*/

      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void LoadDatabase() {
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

      stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCT");  //Temporary database testing. Will be modified in the future.

      while(rs.next()) {
        String prodName = rs.getString("NAME");
        String prodManu = rs.getString("MANUFACTURER");
        ItemType prodType = ItemType.valueOf(rs.getString("TYPE"));

        productLine.add(new Widget(prodName, prodManu, prodType)); //Adding test product in observable list
      }
      productTable.setItems(productLine);
      list_Produce.setItems(productLine);

      stmt.close();     //Close
      conn.close();     //Close
    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Product record button call
   */
  @FXML
  void but_RecordProduction(ActionEvent event) {
    //textLog.setText(textLog.getText() + list_Produce.getSelectionModel().getSelectedItem().toString());

    for(int i = 0; i < Integer.parseInt(combo_quantity.getValue()); i++) {
      globalProductCount++;     //Counts up product count
      textLog.setText(textLog.getText() + "\n" + new ProductionRecord(list_Produce.getSelectionModel().getSelectedItem(), i + 1, globalProductCount));
    }

    System.out.println("Product recorded");
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
      choice_Type.getItems().add(i, typeNames.get(i));
    }
    choice_Type.getSelectionModel().selectFirst();
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

}