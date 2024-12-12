module miniproject {
    requires javafx.controls;
    requires javafx.fxml;

    opens miniproject to javafx.fxml;
    exports miniproject;
}
