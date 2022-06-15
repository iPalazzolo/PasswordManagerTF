package com.example.passwordmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

// Esta es la ventana de 'Registro Exitoso' - Tiene 1 ACCION: Aceptar
public class RegisterSuccesfulController {

    @FXML
    private Button aceptarButton;

    // Acción (1) = Aceptar
    public void aceptarButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) aceptarButton.getScene().getWindow();
        stage.close();
    }
}
