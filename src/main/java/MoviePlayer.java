/**
 * Programmer Name: Jaden Williams
 * Description: Defines movie player that extends
 * products and implements multimediacontrol
 * Date: 9/18/2020 - 10/31/2020
 */

public class MoviePlayer extends Product implements MultimediaControl {

  Screen screen;
  MonitorType monitorType;

  public MoviePlayer(String name, String manufacturer, Screen screen, MonitorType monitorType) {
    super(name, manufacturer, ItemType.VISUAL);
    this.screen = screen;
    this.monitorType = monitorType;
  }

  /**
   * Print method
   */
  public void Play() {
    System.out.println("Playing movie");
  }

  /**
   * Print method
   */
  public void Stop() {
    System.out.println("Stopping movie");
  }

  /**
   * Print method
   */
  public void Previous() {
    System.out.println("Previous movie");
  }

  /**
   * Print method
   */
  public void Next() {
    System.out.println("Next movie");
  }

  /**
   * Print method
   */
  @Override
  public String toString() {
    return
        "Name: " + name + "\n" +
        "Manufacturer: " + manufacturer + "\n" +
        "Type: " + type + "\n" +
        "Screen: " + "\n" +
        screen + "\n" +
        "Monitor Type: " + monitorType;
  }
}
