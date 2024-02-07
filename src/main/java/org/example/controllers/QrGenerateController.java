package org.example.controllers;

import com.google.zxing.WriterException;
import com.jfoenix.controls.JFXTextField;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import org.example.qr.QRGenerator;

import java.awt.image.BufferedImage;


public class QrGenerateController {
    public JFXTextField txtText;
    public ImageView image;
    public TextField txtLocation;

    public void GenerateQrOnAction(ActionEvent actionEvent) throws WriterException {
        String text = txtText.getText();
        if (text.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter text").show();
        } else {
            BufferedImage bufferedImage = QRGenerator.generateQrCode(text, 1250, 1250);
            image.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
        }

    }

    public void QrsaveOnAction(ActionEvent actionEvent) throws WriterException {
        if (txtLocation.getText().isEmpty()) {
            LocationOnAction(actionEvent);
        } else {
            String text = txtText.getText();
            String filepath = txtLocation.getText() + "/" + text +".png";
            boolean isGenerated = QRGenerator.generateQrCode(text, 1250, 1250, filepath);
            if (isGenerated) {
                new Alert(Alert.AlertType.CONFIRMATION, "QR Code Generated Successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            }
        }

    }

    public void LocationOnAction(ActionEvent actionEvent) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select the location");
        String path = directoryChooser.showDialog(null).toString();
        txtLocation.setText(path);
    }
}
