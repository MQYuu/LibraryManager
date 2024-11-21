package book.exportbook;

import book.ui.exportbook.ExportBookResultForm;

public class ExportBookPresenter implements ExportBookOutputBoundary {
    private ExportBookResultForm exportBookResultForm;

    public ExportBookPresenter(ExportBookResultForm exportBookResultForm) {
        this.exportBookResultForm = exportBookResultForm;
    }

    @Override
    public void presentExportBookResult(ExportBookResponseData exportBookResponseData) {
        exportBookResultForm.display(exportBookResponseData);  // Hiển thị kết quả trong form
    }
}
