package xyz.intent.iface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import xyz.intent.iface.controller.LoginController;

import java.io.IOException;
import java.net.URL;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = getClass().getClassLoader().getResource("main.fxml");
        if (url != null) {
            Parent root = FXMLLoader.load(url);
            primaryStage.setTitle("title");
            primaryStage.setScene(new Scene(root, 400, 400));
            primaryStage.setMaxWidth(500);
            primaryStage.setMaxHeight(500);
            primaryStage.setMinWidth(400);
            primaryStage.setMinHeight(400);
            primaryStage.show();
        } else {
            LoginController.alert("错误", "没有布局文件");
        }
    }


    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
