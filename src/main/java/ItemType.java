/**
 Programmer Name: Jaden Williams
 Description: ItemType enum defines types to choose
 when creating product
 Date: 9/18/2020 - 10/31/2020
 */

public enum ItemType {

  AUDIO("AU"), VISUAL("VI"), AUDIO_MOBILE("AM"), VISUAL_MOBILE("VM");

  public String code;

  /**
   * Used to get code for item type
   * @param code Holds actual code 2 character name for item type
   */
  ItemType(String code) {
    this.code = code;
  }

  /**
   * Returns code name that 2 characters long
   */
  public String code() {
    return code;
  }
}
