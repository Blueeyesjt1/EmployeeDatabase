/**
 * Programmer Name: Jaden Williams
 * Description: Controller class that holds input variables and
 * gateway to H2 database
 * Date: 9/18/2020 - 10/31/2020
 */

import java.sql.*;

public class ProductionRecord {

  int productionNumber;
  int productID;
  String serialnumber;
  Date dateProduced;

  /**
   * Constructor of basic production record with a product ID
   * @param productID holds product ID
   */
  public ProductionRecord(int productID) {
    this.productID = productID;
    this.productionNumber = 0;
    this.serialnumber = "0";
    this.dateProduced = new Date(System.currentTimeMillis());
  }

  /**
   * Constructor of basic production record with a product #, ID, serial #, and date
   * @param productionNumber holds product Number
   * @param productID holds product ID
   * @param serialnumber holds serial number fro product
   * @param dateProduced holds date of Product publishing
   */
  public ProductionRecord(int productionNumber, int productID, String serialnumber, Date dateProduced) {
    this.productionNumber = productionNumber;
    this.productID = productID;
    this.serialnumber = serialnumber;
    this.dateProduced = dateProduced;
  }

  public ProductionRecord(Product product, int quantityNum, int productNum) {     //Issues 5 method
    this.productionNumber = productionNumber;
    this.productID = productID + quantityNum;
    this.productionNumber = productNum;
    String serialNumEnd = "00000";
    int serialCharLength = serialNumEnd.length() - String.valueOf(this.productionNumber + this.productID).length();
    String serialNum = serialNumEnd.substring(0, serialCharLength) + "" + String.valueOf(this.productionNumber + this.productID);
    this.serialnumber = product.manufacturer.toString().substring(0, 3) + product.type.code + serialNum;
    this.dateProduced = new Date(System.currentTimeMillis());
  }

  @Override
  public String toString() {
    return
        "Prod. Num: " + productionNumber + " " +
            "Product ID: " + productID + " " +
            "Serial Num: " + serialnumber + " " +
            "Date: " + dateProduced;
  }

  public int getProductionNumber() {
    return productionNumber;
  }

  public void setProductionNumber(int productionNumber) {
    this.productionNumber = productionNumber;
  }

  public int getProductID() {
    return productID;
  }

  public void setProductID(int productID) {
    this.productID = productID;
  }

  public String getSerialnumber() {
    return serialnumber;
  }

  public void setSerialnumber(String serialnumber) {
    this.serialnumber = serialnumber;
  }

  public Date getDateProduced() {
    return dateProduced;
  }

  public void setDateProduced(Date dateProduced) {
    this.dateProduced = dateProduced;
  }
}


