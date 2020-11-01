/**
 * Programmer Name: Jaden Williams
 * Description: Item interface that holds ID, name,
 * and manufacturer
 * Date: 9/18/2020 - 10/31/2020
 */

public interface Item {

  int getID();

  void setName(String name);

  String getName();

  void setManufacturer(String manufacturerName);

  String getManufacturer();

}
