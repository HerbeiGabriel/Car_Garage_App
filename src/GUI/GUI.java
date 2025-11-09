package GUI;

import Service.Service;
import UI.UI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileReader;
import java.util.Properties;

public class GUI extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("Cars_Reservations.fxml"));
        try{
            Properties prop = new Properties();
            prop.load(new FileReader("settings.properties"));
            String repopath=prop.getProperty("Path");
            String type=prop.getProperty("Repository");
            //Service service=new Service(type, repopath, );
            //Controller controller=new Controller(service);
            /*fxmlLoader.setController(controller);
            Scene scene=new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();*/

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
