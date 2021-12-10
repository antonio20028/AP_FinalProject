package iiitd.ac.ap_group17.willhero;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PageController {
    static AnchorPane currentPage;
    static AnchorPane nextPage;

    static  void goToPage(AnchorPane page) {
        Stage nextStage = (Stage) currentPage.getScene().getWindow();
        nextStage.setScene(new Scene(page));
        nextStage.setTitle("Win Hero - Saved Games");
        nextStage.show();
    }

    static void setCurrentPage(AnchorPane currentPage) {
        PageController.currentPage = currentPage;
    }
}
