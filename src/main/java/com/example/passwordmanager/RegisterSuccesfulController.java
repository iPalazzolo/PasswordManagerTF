package com.example.passwordmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RegisterSuccesfulController {

    @FXML
    private Button aceptarButton;

    public void aceptarButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) aceptarButton.getScene().getWindow();
        stage.close();
    }
}
