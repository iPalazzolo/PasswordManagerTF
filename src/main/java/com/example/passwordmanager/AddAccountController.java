package com.example.passwordmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AddAccountController {

    @FXML
    private Button cancelarButton;
    public void cancelarButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelarButton.getScene().getWindow();
        stage.close();
    }
}
