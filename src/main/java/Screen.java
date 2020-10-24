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
