package miniproject;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;

public class ButtonController implements Initializable {

    @FXML
    private Button button;

    @FXML
    private TextArea textArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void handleButtonAction() {
        button.setDisable(true);
        textArea.clear();

        Thread thread = new Thread(() -> {
            try {
                URL url = new URL(
                        "https://api.open-meteo.com/v1/forecast?latitude=37.421654&longitude=-122.084248&current_weather=true");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    Platform.runLater(() -> {
                        textArea.setText(response.toString());
                    });

                } else {
                    Platform.runLater(() -> {
                        textArea.setText("Failed to retrieve data. Response code: " + responseCode);
                    });
                }

            } catch (Exception e) {
                Platform.runLater(() -> {
                    textArea.setText("An error occurred: " + e.getMessage());
                });
            } finally {
                Platform.runLater(() -> {
                    button.setDisable(false);
                });
            }
        });
        thread.start();
    }

}
