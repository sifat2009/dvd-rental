package dvdrental.sifat.exception;

public class FilmException extends RuntimeException{

    public FilmException() {
    }
    public FilmException(String message) {
        super(message);
    }

    public FilmException(String message, Throwable cause) {
        super(message, cause);
    }

    public FilmException(Throwable cause) {
        super(cause);
    }

    public FilmException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
