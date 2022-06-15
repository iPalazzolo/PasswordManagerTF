package com.example.passwordmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Esta es la ventana del 'Home' - Tiene 3 ACCIONES: Agregar Cuenta, Editar Cuenta, Ir al sitio web
public class HomeController {

    // Acción (1) = Agregar Nueva Cuenta
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

    // Acción (2) = Editar Cuenta

    // Acción (3) = Ir al sitio web
}
