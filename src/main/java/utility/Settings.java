package utility;

public class Settings {
    static String PASSWORD_EMAIL_NULL = "Error , Password or Eamil is a null ";
    static String USER_NOT_FOUND = "User not found";
    static String INCORRECT_EMAIL = "Incorrect Email";
    static String INCOREECT_USERNAME = "Incorrect Username";
    static String INCORECT_PASSWORD = "Incorrect Password";
    static String EMAIL_OLREDY_REGISTRED = "Your email olredy registred";
    static String ERROR_UNKNOWN = "Unknown error in register";
    static String SERVER_ERROR = "Server error in home page";
    static String OLREDI_REGISTER = "You are currently registered and logged in to continue to create your own account";
    static String PASSWORD_REG = "^[a-zA-Z0-9]{6,25}";
    static String USERNSME_REG = "^[a-zA-Z0-9._-]{6,25}$";
    static String EMAIL_REG = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";



    public static String getOlrediRegister() {
        return OLREDI_REGISTER;
    }

    public static String getPasswordReg() {
        return PASSWORD_REG;
    }

    public static String getUsernsmeReg() {
        return USERNSME_REG;
    }

    public static String getEmailReg() {
        return EMAIL_REG;
    }

    public static String getServerError() {
        return SERVER_ERROR;
    }

    public static String getErrorUnknown() {
        return ERROR_UNKNOWN;
    }

    public static String getEmailOlredyRegistred() {
        return EMAIL_OLREDY_REGISTRED;
    }

    public static String getPasswordEmailNull() {
        return PASSWORD_EMAIL_NULL;
    }

    public static String getUserNotFound() {
        return USER_NOT_FOUND;
    }

    public static String getIncorrectEmail() {
        return INCORRECT_EMAIL;
    }

    public static String getIncoreectUsername() {
        return INCOREECT_USERNAME;
    }

    public static String getIncorectPassword() {
        return INCORECT_PASSWORD;
    }
}
