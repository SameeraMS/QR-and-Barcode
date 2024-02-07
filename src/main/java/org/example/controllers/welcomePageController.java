package org.example.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class welcomePageController {
    public void QrGeneratorOnAction(ActionEvent actionEvent) throws IOException {
        viewForm("/view/qr/QrGenerate.fxml");
    }

    public void QrReaderOnAction(ActionEvent actionEvent) throws IOException {
        viewForm("/view/qr/QrScanForm.fxml");
    }

    public void BarcodeGeneratorOnAction(ActionEvent actionEvent) {
    }

    public void BarcodeReaderOnAction(ActionEvent actionEvent) throws IOException {
        viewForm("/view/barcodeReader/BarcodeRead.fxml");
    }

    public void viewForm(String form) throws IOException {
        Stage primaryStage = new Stage();
        Parent load = FXMLLoader.load(getClass().getResource(form));
        Scene scene = new Scene(load);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
