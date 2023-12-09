package com.example.movie_example_01;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloController {
    @FXML
    private Button signUp_button;

    @FXML
    private Button signUp_close;

    @FXML
    private TextField signUp_email;

    @FXML
    private AnchorPane signUp_form;

    @FXML
    private Button signUp_minimize;

    @FXML
    private PasswordField signUp_password;

    @FXML
    private Hyperlink signUp_signin;

    @FXML
    private TextField signUp_username;

    @FXML
    private Button signin_close;

    @FXML
    private AnchorPane signin_form;

    @FXML
    private Button signin_loginBtn;

    @FXML
    private Button signin_minimize;

    @FXML
    private Hyperlink signin_newaccount;

    @FXML
    private PasswordField signin_password;

    @FXML
    private TextField signin_username;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public void signin(){
        String sql = "SELECT * FROM MOCK_DATA WHERE username = ? and password = ?";

        connect = database.connectDb();

        try{

            prepare = connect.prepareStatement(sql);
            prepare.setString(1, signin_username.getText());
            prepare.setString(2, signin_password.getText());

            result = prepare.executeQuery();

            Alert alert;

            if(signin_username.getText().isEmpty() || signin_password.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Massage");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else{

                if(result.next()){
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Massage");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login!");
                    alert.showAndWait();

                    Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));

                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    stage.setScene(scene);
                    stage.show();
                }else{

                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username/Password");
                    alert.showAndWait();

                }

            }

        }catch(Exception e){e.printStackTrace();}
    }



    public void switchForm(ActionEvent event){

        if(event.getSource() == signin_newaccount){
            signin_form.setVisible(false);
            signUp_form.setVisible(true);
        }else if(event.getSource() == signUp_signin){
            signin_form.setVisible(true);
            signUp_form.setVisible(false);
        }

    }

    public void signin_close(){
        System.exit(0);
    }

    public void signin_minimize(){
        Stage stage = (Stage)signin_form.getScene().getWindow();
        stage.setIconified(true);
    }

    public void signUp_close(){
        System.exit(0);
    }

    public void signUp_minimize(){
        Stage stage = (Stage)signUp_form.getScene().getWindow();
        stage.setIconified(true);
    }
}