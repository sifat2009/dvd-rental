package dvdrental.exception;

public class DvdRentalException extends RuntimeException {

    public DvdRentalException() {
    }

    public DvdRentalException(String message) {
        super(message);
    }

    public DvdRentalException(String message, Throwable cause) {
        super(message, cause);
    }

    public DvdRentalException(Throwable cause) {
        super(cause);
    }

    public DvdRentalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
