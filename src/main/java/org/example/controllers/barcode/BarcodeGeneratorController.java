package org.example.controllers.barcode;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import org.example.barcode.Barcode_genarate;

import java.io.IOException;


public class BarcodeGeneratorController {
    public JFXTextField txtText;
    public ImageView image;
    public TextField txtLocation;

    public void GenerateBarCodeOnAction(ActionEvent actionEvent) throws IOException {
        String text = txtText.getText();
        if (text.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter text").show();
        } else {
            Barcode_genarate barcodeGenarate = new Barcode_genarate();
            barcodeGenarate.createImage(text + ".png", text);
            Image img = barcodeGenarate.getImg();
            image.setImage(img);
        }
    }

    public void BarCodesaveOnAction(ActionEvent actionEvent) {
        if (txtLocation.getText().isEmpty()) {
            LocationOnAction(actionEvent);
        } else {
            String text = txtText.getText();
            String filepath = txtLocation.getText() + "/" + text +".png";

            Barcode_genarate barcodeGenarate = new Barcode_genarate();
            boolean isGenerated = barcodeGenarate.createImage(text + ".png", text, filepath);

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
