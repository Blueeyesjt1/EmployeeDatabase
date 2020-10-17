public class AudioPlayer extends Product implements MultimediaControl {

  String supportedAudioFormats;
  String supportedPlaylistFormats;

  public AudioPlayer(String name, String manufacturer, String supportedAudioFormats, String supportedPlaylistFormats) {
    super(name, manufacturer, ItemType.AUDIO);
    this.supportedAudioFormats = supportedAudioFormats;
    this.supportedPlaylistFormats = supportedPlaylistFormats;
  }

  public String toString() {
    return "Name: " + name +
        "\n Manufacturer: " + manufacturer +
        "\n Type: " + type +
        "\n Supported Audio Formats: " + supportedAudioFormats +
        "\n Supported Playlist Formats: " + supportedPlaylistFormats;
  }

  public void Play() {
    System.out.println("Playing");
  }

  public void Stop() {
    System.out.println("Stopping");
  }

  public void Previous() {
    System.out.println("Previous");
  }

  public void Next() {
    System.out.println("Next");
  }

}
