public abstract class Product {

  protected int id;
  protected ItemType type;
  protected String manufacturer;
  protected String name;

  public Product(String name, String manufacturer, ItemType type) {
    this.type = type;
    this.manufacturer = manufacturer;
    this.name = name;
  }

  int getId() {
    return id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  @Override
  public String toString() {
    return "Name: " + name +
        "\n Manufacturer: " + manufacturer +
        "\n Type: " + type;
  }
}
