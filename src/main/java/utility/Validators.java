package utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static utility.Settings.getEmailReg;
import static utility.Settings.getPasswordReg;
import static utility.Settings.getUsernsmeReg;

public class Validators {
    public static boolean isPasswordValid(String password) {
        Pattern validPass =
                Pattern.compile(getPasswordReg(), Pattern.CASE_INSENSITIVE);
        Matcher matcher = validPass .matcher(password);
        return matcher.find();

    }


    public static boolean isUsernameValid (String username) {

        Pattern usReg = Pattern.compile(getUsernsmeReg(),Pattern.CASE_INSENSITIVE);
        Matcher matcher = usReg.matcher(username);


        return matcher.find();
    }


    public static boolean isEmailValid(String email){

        Pattern emailReg =
                Pattern.compile(getEmailReg(), Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailReg .matcher(email);
        return matcher.find();

    }
}
