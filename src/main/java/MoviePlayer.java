public class MoviePlayer extends Product implements MultimediaControl {

  Screen screen;
  MonitorType monitorType;

  public MoviePlayer(String name, String manufacturer, Screen screen, MonitorType monitorType) {
    super(name, manufacturer, ItemType.VISUAL);
    this.screen = screen;
    this.monitorType = monitorType;
  }

  public void Play() {
    System.out.println("Playing movie");
  }

  public void Stop() {
    System.out.println("Stopping movie");
  }

  public void Previous() {
    System.out.println("Previous movie");
  }

  public void Next() {
    System.out.println("Next movie");
  }

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
