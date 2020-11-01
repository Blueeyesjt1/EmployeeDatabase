/**
 * Programmer Name: Jaden Williams
 * Description: Monitor type defines types
 * of monitors to choose from
 * Date: 9/18/2020 - 10/31/2020
 */

public enum MonitorType {

  LCD("LCD"), LED("LED");

  public String type;

  /**
   * Audio player constructor that holds name, manufacturer, supported audio formats, and supported
   * playlist formats
   * @param type holds type of monitor > LCD or LED
   */
  MonitorType(String type) {
    this.type = type;
  }
}
