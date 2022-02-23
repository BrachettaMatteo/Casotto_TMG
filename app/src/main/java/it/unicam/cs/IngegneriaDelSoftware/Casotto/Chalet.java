package it.unicam.cs.IngegneriaDelSoftware.Casotto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Chalet extends Application {
    static Stage currentStage;

    public static Stage getCurrentStage() {
        return currentStage;
    }

    public static void setCurrentStage(Stage stage) {
        currentStage = stage;
    }

    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(Chalet.class.getResource("PrenotazioneOmbrellone-view.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(Chalet.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Casotto!");
        stage.setScene(scene);
        stage.show();
        currentStage = stage;
    }

    public static void main(String[] args) {
        launch();
    }

}
