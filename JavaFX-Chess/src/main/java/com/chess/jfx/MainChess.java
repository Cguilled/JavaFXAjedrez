package com.chess.jfx;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Guiller
 */
public class MainChess extends Application {

    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXMLChessWindow.fxml"));

            Scene scene = new Scene(root, 600, 400);
            scene.getStylesheets().add("/styles/Styles.css");

            stage.setTitle("Ajedrez");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("ERROR MainChess.start: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}