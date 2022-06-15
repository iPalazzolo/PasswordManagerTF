package com.example.passwordmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeController {

    public void addAccountButtonOnAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("addAccount.fxml"));
            Stage agregarCuentaStage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 450, 350);
            agregarCuentaStage.setTitle("Agregar Nueva Cuenta");
            agregarCuentaStage.setScene(scene);
            agregarCuentaStage.setResizable(false);
            agregarCuentaStage.show();

        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

}
