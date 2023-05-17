package com.lhl.xxxcloud.generator.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // 这里的root从FXML文件中加载进行初始化，这里FXMLLoader类用于加载FXML文件
            Pane root = (Pane)FXMLLoader.load(getClass().getResource("/Generate.fxml"));
            Scene scene = new Scene(root, 670, 285);
            primaryStage.setScene(scene);
            primaryStage.setTitle("代码生成器");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}
