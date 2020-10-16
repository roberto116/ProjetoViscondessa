/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.projeto.app;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Beto
 */
public class Aplicacao extends Application{
    public static void main(String[] args){
        launch(args);
    }
    
    public void start(Stage stage)throws Exception{
        URL url = getClass().getResource("/br/senac/projeto/telas/TelaPrincipal.fxml");
        
        Parent TelaPrincipal = FXMLLoader.load(url);
        
        Scene scene = new Scene(TelaPrincipal);
        stage.setScene(scene);
        stage.show();
    }
}
