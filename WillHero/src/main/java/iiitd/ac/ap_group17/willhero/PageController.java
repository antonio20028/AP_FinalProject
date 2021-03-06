package iiitd.ac.ap_group17.willhero;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PageController {
    static AnchorPane previousPage;
    static AnchorPane nextPage;
    private AnchorPane nextCurrent;

    static  void goToPage(AnchorPane from, AnchorPane to, String name) {
        PageController.previousPage = from;
        PageController.nextPage = to;
        Stage nextStage = (Stage) from.getScene().getWindow();
        nextStage.setScene(new Scene(to));
        nextStage.setTitle(name);
        nextStage.show();
    }

    public void setNextCurrent(AnchorPane nextCurrent) {
        this.nextCurrent = nextCurrent;
    }

    public AnchorPane getNextCurrent() {
        return nextCurrent;
    }

    static void goBack(){
        Stage stage = (Stage) PageController.nextPage.getScene().getWindow();
        stage.setScene(PageController.previousPage.getScene());
        stage.show();
    }
}
