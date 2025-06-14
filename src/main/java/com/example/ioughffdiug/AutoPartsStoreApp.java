package com.example.ioughffdiug;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AutoPartsStoreApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                AutoPartsStoreApp.class.getResource(
                        "/com/example/ioughffdiug/hello-view.fxml"
                )
        );

        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Магазин Автозапчастей");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}