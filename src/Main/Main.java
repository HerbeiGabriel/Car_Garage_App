package Main;

import Service.Service;
import UI.UI;

import java.io.FileReader;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        try{
            Properties prop = new Properties();
            prop.load(new FileReader("settings.properties"));
            String repopath=prop.getProperty("Path");
            String type=prop.getProperty("Repository");
            Service service=new Service(type, repopath);
            UI UI=new UI(service);
            UI.run();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}