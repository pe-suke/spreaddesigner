package jp.gr.java_conf.pesk.spreaddesigner;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jp.gr.java_conf.pesk.spreaddesigner.controller.app.AppController;

/**
 * Spread Designer Main Class.
 * This is the entry point of JavaFX application.
 *
 */
public class App extends Application {
    
    private static Stage appStage;
    
    public static void main(String[] args) throws Exception {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        appStage = primaryStage;
        
        // load from App.fxml
        URL fxml = getClass().getClassLoader().getResource(getClass().getSimpleName() + ".fxml");
        FXMLLoader loader = new FXMLLoader(fxml);
        
        // bind controller
        loader.setController(new AppController());
        
        // create the scene from loaded fxml and set it to the default stage.
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Spread Designer");
        primaryStage.setScene(scene);
        
        // show the stage
        primaryStage.show();
    }
    
    @Override
    public void stop() throws Exception {}
    
    public static Stage getApplicationStage() {
        return appStage;
    }
}
