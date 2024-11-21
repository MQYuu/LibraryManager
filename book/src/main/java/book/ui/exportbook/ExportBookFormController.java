package book.ui.exportbook;

import book.exportbook.ExportBookInputBoundary;
import book.exportbook.ExportBookRequestData;

public class ExportBookFormController {
    private ExportBookInputBoundary exportBookInputBoundary;

    public ExportBookFormController(ExportBookInputBoundary exportBookInputBoundary) {
        this.exportBookInputBoundary = exportBookInputBoundary;
    }

    public void showExportBookForm() {
        // Hiển thị form để người dùng nhập liệu
        ExportBookForm form = new ExportBookForm(this);
        form.setVisible(true);
    }

    public void handlePublisherInput(String publisher) {
        // Nhận tên nhà xuất bản từ form và gửi dữ liệu đến service để xuất thông tin sách
        ExportBookRequestData requestData = new ExportBookRequestData(publisher);
        exportBookInputBoundary.exportBook(requestData);
    }
}

