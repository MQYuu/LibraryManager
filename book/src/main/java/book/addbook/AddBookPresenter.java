package book.addbook;

import book.ui.AddBookResultForm;

public class AddBookPresenter implements AddBookOutputBoundary{
        private AddBookResultForm resultForm;
    
        public AddBookPresenter(AddBookResultForm resultForm) {
            this.resultForm = resultForm;
        }
    
        @Override
        public void presentResult(AddBookResponseData responseData) {
            resultForm.displayResult(responseData.message);
        }
    }
    
