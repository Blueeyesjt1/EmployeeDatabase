public class Employee {
  String name;
  String username;
  String password;
  String email;

  public Employee(String name, String password) {
    this.name = name;

    if(checkName()) {     //If name contains a space,
      char firstInit =  name.charAt(0);     //First initial
      String lastName = name.substring(name.indexOf(" ") + 1, name.length());     //last name
      String firstname = name.substring(0, name.indexOf(" "));     //First name

      setUsername("" + firstInit + lastName);     //Set username
      setEmail("" + firstname.toLowerCase() + "." + lastName.toLowerCase() + "@oracleacademy.Test");     //Set email
    }
    else {     //If name does not contain a space,
      setUsername("default");
      setEmail("user@oracleacademy.Test");
    }

    if(isValidPass(password))
      this.password = password;
    else
      this.password = "pw";
  }

  private boolean checkName() {
    if(name != null && name.toString().contains(" "))
      return true;
    else
      return false;
  }

  public void setUsername(String username) {
    this.username = username.toLowerCase();     //Sets lowercase username
  }

  public void setEmail(String email) {
    this.email = email;
  }

  private boolean isValidPass(String password) {
    boolean hasLower = false;
    boolean hasUpper = false;
    boolean hasSpecial = false;

    for(int i = 0; i < password.length(); i++) {
      if(Character.isLowerCase(password.charAt(i))) {
        //System.out.println("pass lower " + hasLower);
        hasLower = true;
      }

      if(Character.isUpperCase(password.charAt(i))) {
        //System.out.println("pass upper " + hasUpper);
        hasUpper = true;
      }

      if(!Character.isLetter(password.charAt(i)) && !Character.isDigit(password.charAt(i))) {
        //System.out.println("pass special " + hasSpecial);
        hasSpecial = true;
      }
    }

    if(hasLower && hasUpper && hasSpecial)     //If password is valid,
      return true;
    else
      return false;
  }

  @Override
  public String toString() {
    return "Employee Details" + '\n' +
        "Name : " + this.name + '\n' +
        "Username : " + this.username + '\n' +
        "Email : " + this.email + '\n' +
        "Initial Password : " + this.password + '\n';
  }
}
