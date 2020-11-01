/**
 * Programmer Name: Jaden Williams
 * Description: Screen that implements screenSpec
 * Date: 9/18/2020 - 10/31/2020
 */

public class Screen implements ScreenSpec {

  String resolution;
  int refreshRate;
  int responseTime;

  public Screen(String resolution, int refreshRate, int responseTime) {
    this.resolution = resolution;
    this.refreshRate = refreshRate;
    this.responseTime = responseTime;
  }

  public String getResolution() {
    return null;
  }

  public int getRefreshRate() {
    return 0;
  }

  public int getResponseTime() {
    return 0;
  }

  public String toString() {
    return
          "Resolution: " + resolution + '\n' +
          "Refresh rate: " + refreshRate + '\n' +
          "Response time: " + responseTime;
  }
}
