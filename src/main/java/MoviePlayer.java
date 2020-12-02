/**
 * Programmer Name: Jaden Williams
 * Description: Defines movie player that extends.
 * products and implements multimediacontrol.
 * Date: 9/18/2020 - 10/31/2020.
 */

public class MoviePlayer extends Product implements MultimediaControl {

  Screen screen;
  MonitorType monitorType;

  /**
   * Constructor of basic product with name, manfacturer name, and item type.
   * @param name holds product name.
   * @param manufacturer holds manufacturer/publisher name.
   * @param screen holds screen type.
   * @param monitorType holds monitor type.
   */
  public MoviePlayer(String name, String manufacturer, Screen screen, MonitorType monitorType) {
    super(name, manufacturer, ItemType.VISUAL);
    this.screen = screen;
    this.monitorType = monitorType;
  }

  /**
   * Print method.
   */
  public void play() {
    System.out.println("Playing movie");
  }

  /**
   * Print method.
   */
  public void stop() {
    System.out.println("Stopping movie");
  }

  /**
   * Print method.
   */
  public void previous() {
    System.out.println("Previous movie");
  }

  /**
   * Print method.
   */
  public void next() {
    System.out.println("Next movie");
  }

  /**
   * Print method.
   */
  @Override
  public String toString() {
    return
        "Name: " + name + "\n"
        + "Manufacturer: " + manufacturer + "\n"
        + "Type: " + type + "\n"
        + "Screen: " + "\n"
        + screen + "\n"
        + "Monitor Type: " + monitorType;
  }
}
