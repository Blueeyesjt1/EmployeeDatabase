import java.util.Date;

public class ProductionRecord {

  int productionNumber;
  int productID;
  String serialnumber;
  Date dateProduced;

  public ProductionRecord(int productID) {
    this.productID = productID;
    this.productionNumber = 0;
    this.serialnumber = "0";
    this.dateProduced = new Date();
  }

  public ProductionRecord(int productionNumber, int productID, String serialnumber, Date dateProduced) {
    this.productionNumber = productionNumber;
    this.productID = productID;
    this.serialnumber = serialnumber;
    this.dateProduced = dateProduced;
  }

  public ProductionRecord(Product product, int quantityNum) {     //Issues 5 method
    this.productionNumber = productionNumber;
    this.productID = productID;
    String serialNumEnd = "00000";
    int serialCharLength = serialNumEnd.length() - String.valueOf(quantityNum).length();
    String serialNum = serialNumEnd.substring(0, serialCharLength) + "" + String.valueOf(quantityNum);
    this.serialnumber =  product.manufacturer.toString().substring(0,3) + product.type.code + serialNum;
    this.dateProduced = new Date();
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


