import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Programmer Name: Jaden Williams
 * Description: Sample class that holds UI information Date.
 * 9/18/2020 - 10/31/2020.
 */
public class Controller {

  @FXML   //Product input
  private TextField txtProductName;

  @FXML   //Manufacture input
  private TextField txtManufacturer;

  @FXML   //Type box
  private ChoiceBox<ItemType> choiceType;

  @FXML   //Produce box //
  private ListView<Product> listProduce;

  @FXML   //Quantity box
  private ComboBox<String> comboQuantity;

  @FXML   //Text log on last page
  private TextArea textLog;

  @FXML
  private TableView<Product> productTable;

  @FXML   //Text field for employee name
  private TextField txtEmployeeName;

  @FXML   //Text field for employee password
  private TextField txtEmployeePass;

  public int globalProductCount = 0;

  public ObservableList<Product> productLine = FXCollections.observableArrayList();

  TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
  TableColumn<Product, String> manuColumn = new TableColumn<>("Manufacturer");
  TableColumn<Product, ItemType> typeColumn = new TableColumn<>("Type");

  public Employee mainEmployee;

  /**
   * Initialized method used at program startup.
   */
  public void initialize() {

    Employee emp = new Employee("jaden Williams", "abEFc123!");

    //Widget newProductTest = new Widget("iPod45", "Apple", ItemType.AUDIO);  //Test widget

    System.out.println("Launched program");
    loadDatabaseProducts();
    loadDatabaseRecords();
    tableViewSetup();  //Sets up table structure
    populateProductLineTabs();  //Populates item type dropdown
    populateItemQuantity(); //Populates quantity dropdown
    //testMultimedia(); //Testing
    //testProductionRecord(); //Testing text log on last page
  }

  /**
   * Method called when add product button is pressed.
   * @param event used when "add product" button is pressed.
   */
  @FXML void but_AddProduct(ActionEvent event) {

    productLine.add(new Widget(txtProductName.getText(), txtManufacturer.getText(),
        choiceType.getValue())); //Adding test product in observable list

    productTable.setItems(productLine);     //Adds product to table
    listProduce.setItems(productLine);     //Adds product to produce list

    addToDatabaseProduct();

    System.out.println("Product added");
  }

  /**
   * Method called when login button is pressed to log employee into log page.
   * @param event used when "login" button is pressed.
   */
  @FXML
  public void but_Login(ActionEvent event) {

    Employee workingEmployee = new Employee(txtEmployeeName.getText(),
        txtEmployeePass.getText());     //Attempts login

    textLog.setText(
        textLog.getText() + "\n" + "Employee login: " + "\n     " + workingEmployee.email
            + "\n     " + workingEmployee.username);     //Logs login

    mainEmployee = workingEmployee;     //Set attempted employee login
  }

  /**
   * Sets up the table on the products page to load proper columns.
   */
  public void tableViewSetup() {
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
    productTable.getColumns().add(nameColumn);

    manuColumn.setCellValueFactory(new PropertyValueFactory<>("Manufacturer"));
    productTable.getColumns().add(manuColumn);

    typeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
    productTable.getColumns().add(typeColumn);
  }

  /**
   * When "add product" button is pressed, the input fields get sent to database to be stored.
   */
  public void addToDatabaseProduct() {
    //  Database credentials
    String pass = "";

    InputStream passPath = Controller.class.getResourceAsStream("/password.txt");
    if (passPath == null) {
      System.out.println("Could not find password in resources folder");
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(passPath));
    String line = null;
    while (true) {
      try {
        if (!((line = reader.readLine()) != null)) {
          break;
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
      System.out.println(line);
      if (line != null) {
        pass = line;
        System.out.println("Password login: " + line);
        break;
      }
    }
    Connection conn = null; //Temporary
    PreparedStatement stmt = null; //Temporary

    try {
      // STEP 1: Register JDBC driver
      Class.forName("org.h2.Driver");

      //STEP 2: Open a connection
      conn = DriverManager.getConnection("jdbc:h2:./res/HR", "",
          pass);

      final String sqlProductName = txtProductName.getText();
      String sqlManufName = txtManufacturer.getText();
      ItemType sqlItemType = choiceType.getValue();

      stmt = conn.prepareStatement("INSERT INTO Product(type, manufacturer, name) VALUES (?, ?, ?)",
          Statement.RETURN_GENERATED_KEYS);
      stmt.setString(1, sqlItemType.toString());
      stmt.setString(2, sqlManufName);
      stmt.setString(3, sqlProductName);

      stmt.executeUpdate();

      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Method used to add logs to the database for history.
   * @param prodRec holds production record type.
   * @param empUsername holds username of logged-in employee.
   */
  public void addToDatabaseRecord(ProductionRecord prodRec, String empUsername) {
    //  Database credentials
    String pass = "";

    InputStream passPath = Controller.class.getResourceAsStream("/password.txt");
    if (passPath == null) {
      System.out.println("Could not find password in resources folder");
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(passPath));
    String line = null;
    while (true) {
      try {
        if (!((line = reader.readLine()) != null)) {
          break;
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
      System.out.println(line);
      if (line != null) {
        pass = line;
        System.out.println("Password login: " + line);
        break;
      }
    }
    Connection conn = null; //Temporary
    PreparedStatement stmt = null; //Temporary

    try {
      // STEP 1: Register JDBC driver
      Class.forName("org.h2.Driver");

      //STEP 2: Open a connection
      conn = DriverManager.getConnection("jdbc:h2:./res/HR", "",
          pass);

      stmt = conn.prepareStatement(
          "INSERT INTO Productionrecord(production_num, product_id, "
              + "serial_num, date_produced, username) VALUES (?, ?, ?, ?, ?)",
          Statement.RETURN_GENERATED_KEYS);
      stmt.setInt(1, prodRec.getProductionNumber());
      stmt.setInt(2, prodRec.getProductID());
      stmt.setString(3, prodRec.serialnumber);
      stmt.setDate(4, prodRec.getDateProduced());
      stmt.setString(5, empUsername);

      stmt.executeUpdate();

      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Loads database to fill table at start of program initialization.
   */
  public void loadDatabaseProducts() {

    //  Database credentials
    String pass = "";

    InputStream passPath = Controller.class.getResourceAsStream("/password.txt");
    if (passPath == null) {
      System.out.println("Could not find password in resources folder");
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(passPath));
    String line = null;
    while (true) {
      try {
        if (!((line = reader.readLine()) != null)) {
          break;
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
      System.out.println(line);
      if (line != null) {
        pass = line;
        System.out.println("Password login: " + line);
        break;
      }
    }

    Connection conn;
    //Connection conn = null; //Temporary
    Statement stmt = null; //Temporary

    try {
      // STEP 1: Register JDBC driver
      Class.forName("org.h2.Driver");

      //STEP 2: Open a connection
      conn = DriverManager.getConnection("jdbc:h2:./res/HR", "",
          pass);   //Security bug - Temporary placeholders, will be modified in future.

      stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(
          "SELECT * FROM PRODUCT");  //Temporary database testing. Will be modified in the future.

      while (rs.next()) {
        String prodName = rs.getString("NAME");
        String prodManu = rs.getString("MANUFACTURER");
        ItemType prodType = ItemType.valueOf(rs.getString("TYPE"));

        productLine
            .add(new Widget(prodName, prodManu, prodType)); //Adding test product in observable list
      }
      productTable.setItems(productLine);
      listProduce.setItems(productLine);

      stmt.close();     //Close
      conn.close();     //Close
    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * When called loads logs into log tab from database.
   */
  public void loadDatabaseRecords() {
    //  Database credentials
    String pass = "";

    InputStream passPath = Controller.class.getResourceAsStream("/password.txt");
    if (passPath == null) {
      System.out.println("Could not find password in resources folder");
    }

    BufferedReader reader = new BufferedReader(new InputStreamReader(passPath));
    String line = null;
    while (true) {
      try {
        if (!((line = reader.readLine()) != null)) {
          break;
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
      System.out.println(line);
      if (line != null) {
        pass = line;
        System.out.println("Password login: " + line);
        break;
      }
    }

    Connection conn;
    //Connection conn = null; //Temporary
    Statement stmt = null; //Temporary

    try {
      // STEP 1: Register JDBC driver
      Class.forName("org.h2.Driver");

      //STEP 2: Open a connection
      conn = DriverManager.getConnection("jdbc:h2:./res/HR", "",
          pass);

      stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(
          "SELECT * FROM PRODUCTIONRECORD");

      int tempCounter = 0;
      while (rs.next()) {
        String prodNum = rs.getString("PRODUCTION_NUM");
        String prodID = rs.getString("PRODUCT_ID");
        String prodSerialNum = rs.getString("SERIAL_NUM").toString();
        String prodDate = rs.getString("DATE_PRODUCED").toString();
        String empUsername = rs.getString("USERNAME").toString();
        textLog.setText(
            textLog.getText() + "\n" + "Prod. Num: " + prodNum + " Product ID: " + prodID
                + " Serial Num: " + prodSerialNum + " " + prodDate + " " + empUsername);
        tempCounter++;
      }
      globalProductCount = tempCounter;
      productTable.setItems(productLine);
      listProduce.setItems(productLine);

      stmt.close();     //Close
      conn.close();     //Close
    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * On record button press on produce tab, data gets sent to log tab.
   * @param event button press for recording production.
   */
  @FXML
  void but_RecordProduction(ActionEvent event) {
    for (int i = 0; i < Integer.parseInt(comboQuantity.getValue()); i++) {
      globalProductCount++;     //Counts up product count
      if (mainEmployee != null) {
        textLog.setText(textLog.getText() + "\n" + new ProductionRecord(
            listProduce.getSelectionModel().getSelectedItem(), i + 1, globalProductCount)
            + " " + mainEmployee.username);
        addToDatabaseRecord(
            new ProductionRecord(listProduce.getSelectionModel().getSelectedItem(), i + 1,
                globalProductCount), mainEmployee.username);
      } else {
        textLog.setText(textLog.getText() + "\n" + new ProductionRecord(
            listProduce.getSelectionModel().getSelectedItem(), i + 1, globalProductCount)
            + " Anonymous");
        addToDatabaseRecord(
            new ProductionRecord(listProduce.getSelectionModel().getSelectedItem(), i + 1,
                globalProductCount), "Anonymous");
      }
    }

  }

  /**
   * Populates quantity dropdown on produce tab with digits 1 - 10.
   */
  void populateItemQuantity() {
    for (int i = 0; i < 10; i++) {
      String number = String.valueOf(i + 1);
      comboQuantity.getItems().add(i, number);
    }
    comboQuantity.setEditable(true);
    comboQuantity.getSelectionModel().selectFirst();
  }

  /**
   * Populates all enum item types inside the itemType dropdown on products tab.
   */
  void populateProductLineTabs() {
    ArrayList<ItemType> typeNames = new ArrayList<ItemType>();

    for (ItemType typeValue : ItemType.values()) {
      typeNames.add(typeValue);
    }
    System.out.println("type array size = " + typeNames.size());

    for (int i = 0; i < typeNames.size(); i++) {
      choiceType.getItems().add(i, typeNames.get(i));
    }
    choiceType.getSelectionModel().selectFirst();
  }

  /**
   * Testing method for multimedia methods.
   */
  public void testMultimedia() {
    AudioPlayer newAudioProduct = new AudioPlayer("DP-X1A", "Onkyo",
        "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC", "M3U/PLS/WPL");
    Screen newScreen = new Screen("720x480", 40, 22);
    MoviePlayer newMovieProduct = new MoviePlayer("DBPOWER MK101", "OracleProduction", newScreen,
        MonitorType.LCD);
    ArrayList<MultimediaControl> productList = new ArrayList<MultimediaControl>();
    productList.add(newAudioProduct);
    productList.add(newMovieProduct);
    for (MultimediaControl p : productList) {
      System.out.println(p);
      p.play();
      p.stop();
      p.next();
      p.previous();
    }
  }

  /**
   * Test production record method for producing production records.
   */
  public void testProductionRecord() {
    // test constructor used when creating production records from user interface
    Integer numProduced = 3;  // this will come from the combobox in the UI

    for (int productionRunProduct = 0; productionRunProduct < numProduced; productionRunProduct++) {
      ProductionRecord pr = new ProductionRecord(0);
      System.out.println(pr.toString());
    }

    ProductionRecord pr = new ProductionRecord(0, 3, "1", new Date(System.currentTimeMillis()));

    textLog.setText(textLog.getText() + pr.toString());
    textLog.setText(textLog.getText() + "\n" + pr.getProductionNumber());
    textLog.setText(textLog.getText() + "\n" + pr.getProductID());
    textLog.setText(textLog.getText() + "\n" + pr.getSerialnumber());
    textLog.setText(textLog.getText() + "\n" + pr.getDateProduced());

  }

}