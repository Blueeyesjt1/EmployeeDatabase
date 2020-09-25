public abstract class Product {

  int id;
  String type;
  String manufacturer;
  String name;

  public Product(String type, String manufacturer, String name) {
    this.type = type;
    this.manufacturer = manufacturer;
    this.name = name;
  }

  public int getId() {
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
        "\' Manufacturer: " + manufacturer +
        "\' Type: " + type;
  }
}
