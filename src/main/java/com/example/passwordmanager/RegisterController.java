package com.example.passwordmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private ImageView logoRegistroImageView;
    @FXML
    private Button cancelarButton;
    @FXML
    private Button registrarButton;
    @FXML
    private Label registroLabel;
    @FXML
    private PasswordField setPasswordField;
    @FXML
    private PasswordField confirmarPasswordField;
    @FXML
    private Label confirmarPasswordLabel;
    @FXML
    private TextField nombreTextField;
    @FXML
    private TextField apellidoTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private Label nombreLabel;
    @FXML
    private Label apellidoLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label passwordLabel;

    // Método para que se vea la imagen de la escena
    @Override
    public void initialize(URL url, ResourceBundle resourcebundle) {
        // Logo del registro
        File logoRegistroFile = new File("images/logo.registro.png");
        Image logoRegistro = new Image(logoRegistroFile.toURI().toString());
        logoRegistroImageView.setImage(logoRegistro);
    }

    // Esta ventana puede realizar un total de 2 acciones:

    // Acción (1) = Registrar un nuevo usuario
    public void registrarButtonOnAction(ActionEvent event) {
        int total = 0;

        total = verificarNombre() + verificarApellido() + verificarEmail() + verificarPassword();
        if(total != 4) {
            registroLabel.setText("Error. Compruebe que los campos estén llenos.");
        } else {
            registrarUsuario();
            cuentaCreada();
            Stage stage = (Stage) registrarButton.getScene().getWindow();
            stage.close();
        }
    }

    // 1. Verifica que el campo 'Nombre' NO esté vacío
    public int verificarNombre() {
        if(nombreTextField.getText().isBlank()) {
            nombreLabel.setText("Campo vacío");
            return 0;
        } else {
            nombreLabel.setText("");
            return 1;
        }
    }

    // 1. Verifica que el campo 'Apellido' NO esté vacío
    public int verificarApellido() {
        if(apellidoTextField.getText().isBlank()) {
            apellidoLabel.setText("Campo vacío");
            return 0;
        } else {
            apellidoLabel.setText("");
            return 1;
        }
    }

    // 1. Verifica que el campo 'Email' NO esté vacío
    public int verificarEmail() {
        if(emailTextField.getText().isBlank()) {
            emailLabel.setText("Campo vacío");
            return 0;
        } else {
            /* Patrón para validar el email
            Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\+]+(\.[_A-Za-z0-9-]+)@"
                            + "[A-Za-z0-9-]+(\.[A-Za-z0-9]+)(\.[A-Za-z]{2,})$");

            Matcher mather = pattern.matcher(emailTextField.getText());

            if (mather.find() == true) {
                System.out.println("El email ingresado es válido.");
            } else {
                System.out.println("El email ingresado es inválido.");
            }
            */
            emailLabel.setText("");
            return 1;
        }
    }

    // 1. Verifica que los campos 'Contraseña' y 'Confirmar Contraseña' NO estén vacíos
    public int verificarPassword() {

        if(setPasswordField.getText().isBlank()) {
            passwordLabel.setText("Campo vacío");
            return 0;
        } else {
            passwordLabel.setText("");
            if(confirmarPasswordField.getText().isBlank()) {
                confirmarPasswordLabel.setText("Campo vacío");
                return 0;
            } else {
                confirmarPasswordLabel.setText("");
                // Comprueba que 'Confirmar Contraseña' sea igual a 'Contraseña'
                if(!verificarCoincidenciaPassword()) {
                    return 0;
                } else {
                    return 1;
                }
            }
        }
    }

    // 1. Verifica coincidencia entre 'Contraseña' y 'Confirmar Contraseña'
    public boolean verificarCoincidenciaPassword() {
        if(!setPasswordField.getText().equals(confirmarPasswordField.getText())) {
            confirmarPasswordLabel.setText("No coincide");
            return false;
        } else {
            confirmarPasswordLabel.setText("");
            return true;
        }
    }

    // 1. Concreta el registro del usuario al pasarlo a la base de datos
    public void registrarUsuario() {
        DBConnection conectarAhora = new DBConnection();
        Connection conectarBD = conectarAhora.getConnection();

        // Adquiere los valores del String de cada campo rellenado por el usuario
        String nombre = nombreTextField.getText();
        String apellido = apellidoTextField.getText();
        String email = emailTextField.getText();
        String password = hashPassword(setPasswordField.getText());

        // Escribe los valores en una línea de comando/código válida para MySQL Workbench
        String insertarCampos = "INSERT INTO usuario(nombre, apellido, email, password) VALUES ('";
        String insertarValores = nombre + "', '" + apellido + "', '" + email + "', '" + password + "')";
        String insertarRegistro = insertarCampos + insertarValores;

        try {
            // La línea de comando en 'insertarRegistro' se ejecuta en MySQL Workbench, cuya acción
            // añade otro registro a la tabla USUARIOS
            Statement statement = conectarBD.createStatement();
            statement.executeUpdate(insertarRegistro);
            //registroLabel.setText("¡Usuario registrado correctamente!");
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    // 1. Encripta la contraseña ingresada por el usuario
    public String hashPassword(String password) {

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes());
            byte [] resultByteArray = messageDigest.digest();
            StringBuilder sb = new StringBuilder();

            for(byte b : resultByteArray){
                sb.append(String.format("%02x",b));
            }
            return sb.toString();
        } catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return "";
    }

    // 1. Nueva ventana generada al crear nuevo usuario de manera exitosa
    public void cuentaCreada() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("registerSuccesful.fxml"));
            Stage registroExitosoStage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 250, 100);
            registroExitosoStage.setScene(scene);
            registroExitosoStage.setTitle("Registro Exitoso");
            registroExitosoStage.setResizable(false);
            registroExitosoStage.show();

        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    // Acción (2) = Cerrar la ventana
    public void cancelarButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelarButton.getScene().getWindow();
        stage.close();
    }
}
