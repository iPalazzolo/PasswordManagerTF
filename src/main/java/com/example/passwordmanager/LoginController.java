package com.example.passwordmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Button ingresarButton;
    @FXML
    private Button salirButton;
    @FXML
    private Label ingresoMensajeLabel;
    @FXML
    private ImageView logoUniversidadImageView;
    @FXML
    private ImageView logoCandadoImageView;
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField ingresarPasswordField;

    // Método para que se vean las imágenes de la escena
    @Override
    public void initialize(URL url, ResourceBundle resourcebundle) {
        // Para el logo de la universidad
        File logoUniversidadFile = new File("images/logo.uaa.png");
        Image logoUniversidad = new Image(logoUniversidadFile.toURI().toString());
        logoUniversidadImageView.setImage(logoUniversidad);

        // Para el logo del candado
        File logoCandadoFile = new File("images/logo.lock.png");
        Image logoCandado = new Image(logoCandadoFile.toURI().toString());
        logoCandadoImageView.setImage(logoCandado);
    }

    // Esta ventana puede hacer 4 acciones:

    // Acción (1) = Ingresar a la aplicación con un usuario ya existente
    public void loginButtonOnAction(ActionEvent event) {
        if(!emailTextField.getText().isBlank() && !ingresarPasswordField.getText().isBlank()) {
            validarIngreso();
        } else {
            ingresoMensajeLabel.setText("Por favor, ingrese un usuario y/o contraseña.");
        }
    }

    // 1. Conexión entre los datos llenados por el usuario con la base de datos
    public void validarIngreso() {
        RegisterController rc = new RegisterController();   // <- Para usar el método de encriptado
        // Conecta con la base de datos
        DBConnection conectarAhora = new DBConnection();
        Connection conectarBD = conectarAhora.getConnection();
        // Asigna el email y la contraseña ingresada por el usuario a variables locales
        String email = emailTextField.getText();
        String password = rc.hashPassword(ingresarPasswordField.getText());
        // Crea un String que servirá como línea de código para MySQL Workbench con los datos adquiridos
        String verificarIngreso = "SELECT count(1) FROM usuario WHERE email = '" + email + "' AND password = '" + password + "'";

        try {
            // Manda el String creado a MySQL Workbench para ejecutar dicho comando
            Statement statement = conectarBD.createStatement();
            ResultSet queryResult = statement.executeQuery(verificarIngreso);

            // Compara los datos ingresados por el usuario con la base de datos entera
            while(queryResult.next()) {
                // Si existe, entra a la aplicación. De lo contrario, le pide nuevas credenciales que sean válidas.
                if(queryResult.getInt(1) == 1) {
                    Stage stage = (Stage) ingresarButton.getScene().getWindow();
                    stage.close();
                    ingresarAplicacion();
                } else {
                    ingresoMensajeLabel.setText("Credenciales inválidas. Intente de nuevo.");
                }
            }

        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    // 1. Ingreso a la Ventana Principal de la aplicación
    public void ingresarAplicacion() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home.fxml"));
            Stage homeStage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 500, 380);
            homeStage.setScene(scene);
            homeStage.setTitle("Password Manager");
            homeStage.setResizable(false);
            homeStage.show();

        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    // Acción (2) - Abrir una ventana para registrar nuevo usuario
    public void crearCuenta() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("register.fxml"));
            Stage registroStage = new Stage();
            registroStage.initStyle(StageStyle.UNDECORATED);
            Scene scene = new Scene(fxmlLoader.load(), 400, 500);
            registroStage.setScene(scene);
            registroStage.show();

        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    // Acción (3) = Abrir nueva ventana para cambiar la contraseña
    // Work in progress . . .

    // Acción (4) = Cerrar la ventana
    public void exitButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) salirButton.getScene().getWindow();
        stage.close();
    }
}