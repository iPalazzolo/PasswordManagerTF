package com.example.passwordmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

// Esta es la ventana 'Agrega Nueva Cuenta' - Tiene 2 ACCINOES: Confirmar y Cancelar
public class AddAccountController {

    @FXML
    private Button cancelarButton;

    // Acción (1) = Confirmar

    // Acción (2) = Cancelar
    public void cancelarButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelarButton.getScene().getWindow();
        stage.close();
    }
}
