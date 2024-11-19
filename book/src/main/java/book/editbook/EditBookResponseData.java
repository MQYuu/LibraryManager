package book.editbook;

public class EditBookResponseData {
    private boolean success;
    private String message;

    public EditBookResponseData(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
}

