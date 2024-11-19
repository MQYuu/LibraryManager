package book.ui.exportbook;

import book.exportbook.ExportBookInputBoundary;
import book.exportbook.ExportBookRequestData;

public class ExportBookFormController {
    private ExportBookInputBoundary exportBookService;

    public ExportBookFormController(ExportBookInputBoundary exportBookService) {
        this.exportBookService = exportBookService;
    }

    public void showExportBookForm() {
        // Hiển thị form để người dùng nhập liệu
        ExportBookForm form = new ExportBookForm(this);
        form.setVisible(true);
    }

    public void handlePublisherInput(String publisher) {
        // Nhận tên nhà xuất bản từ form và gửi dữ liệu đến service để xuất thông tin sách
        ExportBookRequestData requestData = new ExportBookRequestData(publisher);
        exportBookService.exportBook(requestData);
    }
}

