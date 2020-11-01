/**
 * Programmer Name: Jaden Williams
 * Description: Audio player that extends Product and implements
 * multimediaControl Date: 9/18/2020 - 10/31/2020
 */

public class AudioPlayer extends Product implements MultimediaControl {

  String supportedAudioFormats;
  String supportedPlaylistFormats;

  /**
   * Audio player constructor that holds name, manufacturer, supported audio formats, and supported
   * playlist formats
   * @param name holds name of audio player
   * @param manufacturer holds manufacturer/publisher name
   * @param supportedAudioFormats holds supported audio formats
   * @param supportedPlaylistFormats holds supported playlist formats
   */
  public AudioPlayer(String name, String manufacturer, String supportedAudioFormats,
      String supportedPlaylistFormats) {
    super(name, manufacturer, ItemType.AUDIO);
    this.supportedAudioFormats = supportedAudioFormats;
    this.supportedPlaylistFormats = supportedPlaylistFormats;
  }

  /**
   * Printing method to print information
   */
  public String toString() {
    return "Name: " + name +
        "\n Manufacturer: " + manufacturer +
        "\n Type: " + type +
        "\n Supported Audio Formats: " + supportedAudioFormats +
        "\n Supported Playlist Formats: " + supportedPlaylistFormats;
  }

  /**
   * Printing method to print information
   */
  public void Play() {
    System.out.println("Playing");
  }

  /**
   * Printing method to print information
   */
  public void Stop() {
    System.out.println("Stopping");
  }

  /**
   * Printing method to print information
   */
  public void Previous() {
    System.out.println("Previous");
  }

  /**
   * Printing method to print information
   */
  public void Next() {
    System.out.println("Next");
  }

}
