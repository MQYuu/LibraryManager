package book.exportbook;

import book.ui.exportbook.ExportBookResultForm;

public class ExportBookPresenter implements ExportBookOutputBoundary {
    private ExportBookResultForm resultForm;

    public ExportBookPresenter(ExportBookResultForm resultForm) {
        this.resultForm = resultForm;
    }

    @Override
    public void presentExportBookResult(ExportBookResponseData responseData) {
        resultForm.display(responseData);
    }
}
