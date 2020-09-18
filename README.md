# gradleDataBaseFX

Websites used:
  https://sites.google.com/site/profvanselow/course/cop-3003/oop-project
  https://github.com/google/styleguide
  https://stackoverflow.com/questions/38621389/intellij-wont-compile-basic-javafx-program-copied-from-documentation
  https://www.codota.com/code/java/methods/javafx.scene.control.SplitMenuButton/getItems
  
Comments on code:

  The initialize method in Controller.java has a for-loop to set each digit in the quantity drop down in Produce

            public void initialize() {
                  System.out.println("Launched program");

                  for (int i = 0; i < 10; i++) {
                      String number = String.valueOf(i + 1);
                      combo_quantity.getItems().add(i, number);
                  }
                  combo_quantity.setEditable(true);
                  combo_quantity.getSelectionModel().selectFirst();
              }
  
