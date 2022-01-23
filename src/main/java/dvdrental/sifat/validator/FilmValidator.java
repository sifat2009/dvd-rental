package dvdrental.sifat.validator;

public class FilmValidator {
    private static boolean isNullOrEmpty(String value) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        return false;
    }

}
