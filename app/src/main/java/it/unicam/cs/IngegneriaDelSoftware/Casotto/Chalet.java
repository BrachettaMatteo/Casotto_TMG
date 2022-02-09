package it.unicam.cs.IngegneriaDelSoftware.Casotto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Chalet extends Application {
    @Override
    public void start(Stage stage) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(Chalet.class.getResource("login-view.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(Chalet.class.getResource("Login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Casotto!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
