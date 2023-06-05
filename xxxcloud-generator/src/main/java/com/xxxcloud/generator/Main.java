package com.xxxcloud.generator;

import com.xxxcloud.generator.core.GenerateController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Generate.fxml"));
            Pane root = fxmlLoader.load();
            // 这里的root从FXML文件中加载进行初始化，这里FXMLLoader类用于加载FXML文件
            Scene scene = new Scene(root, 640, 285);
            primaryStage.getIcons().add(new Image("cattle.jpg"));
            GenerateController controller = fxmlLoader.getController();
            controller.init();
            primaryStage.setScene(scene);
            primaryStage.setTitle("\u4ee3\u7801\u751f\u6210\u5668-@Copyright-Lin Hongli");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
