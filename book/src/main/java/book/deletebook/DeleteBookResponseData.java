package book.deletebook;

public class DeleteBookResponseData {
    private boolean success;
    private String message;

    public DeleteBookResponseData(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
