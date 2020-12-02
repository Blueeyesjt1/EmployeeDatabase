/**
 Programmer Name: Jaden Williams.
 Description: Product abstract class
 that holds definitions of product.
 Date: 9/18/2020 - 10/31/2020.
 */

public abstract class Product {

  protected int id;
  protected ItemType type;
  protected String manufacturer;
  protected String name;

  /**
   * Constructor of basic product with name, manfacturer name, and item type.
   * @param name holds product name.
   * @param manufacturer holds manufacturer/publisher name.
   * @param type holds product type name.
   */
  public Product(String name, String manufacturer, ItemType type) {
    this.type = type;
    this.manufacturer = manufacturer;
    this.name = name;
  }

  /**
   * Getter for type.
   */
  public ItemType getType() {
    return type;
  }

  /**
   * Setter for type.
   */
  public void setType(ItemType type) {
    this.type = type;
  }

  /**
   * Getter for product ID.
   *
   */
  int getId() {
    return id;
  }

  /**
   * Setter for product name.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Getter for product name.
   */
  public String getName() {
    return name;
  }

  /**
   * Setter for product manufacturer.
   */
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  /**
   * Getter for manufacturer.
   */
  public String getManufacturer() {
    return manufacturer;
  }

  /**
   * Prints string of product information.
   */
  @Override
  public String toString() {
    return "Name: " + name + "\n"
        + "Manufacturer: " + manufacturer + "\n"
        + "Type: " + type;
  }

  /**
   * Prints string of product information.
   */
  public static class Widget extends Product {

    /**
   * Constructor of basic production record with a product ID.
   * @param name holds name of product.
   * @param manufacturer holds manufacturer name.
   * @param type hold item type.
   */
    Widget(String name, String manufacturer, ItemType type) {
      super(name, manufacturer, type);
    }
  }

}



