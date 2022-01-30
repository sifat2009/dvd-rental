package dvdrental.sifat.validator;

public class CheckNullOrEmpty {
    public static boolean isNullOrEmpty(String value) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        return false;
    }
}
