package org.example.webCam;


import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.example.barcode.Barcode;

import java.awt.*;
import java.awt.image.BufferedImage;

//Credit - https://gist.github.com/james-d/f826c9f38d53628114124a56fb7c4557#file-webcamservice-java

public class WebcamService extends Service<Image> {

    private BufferedImage bimg;
    private final Webcam cam;
    private final WebcamResolution resolution;
    private String readBarCodeId;

    public String getReadBarCodeId() {
        return readBarCodeId;
    }

    public Thread getT1() {
        return t1;
    }

    private Thread t1;

    public WebcamService(Webcam webcam, WebcamResolution resolution) {
        this.cam = webcam;
        this.resolution = resolution;
        webcam.setCustomViewSizes(new Dimension[]{resolution.getSize()});
        webcam.setViewSize(resolution.getSize());

    }

    public WebcamService(Webcam webcam) {
        this(webcam, WebcamResolution.QVGA);
    }


    @Override
    protected Task<Image> createTask() {
        return new Task<Image>() {
            @Override
            protected Image call() throws Exception {
                cam.close();
                t1 = new Thread(() -> {
                    while (true) {
                        try {
                            Thread.sleep(1000);
                            updateProgress(10, 100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (bimg != null) {
                            String s = Barcode.barcodeRead(bimg);
                            readBarCodeId = s;
                            if (s != null) {
                                updateMessage(s);
                                updateProgress(100, 100);
                                System.out.println(s + "            now ");
                            } else {
                                try {
                                    updateProgress(10, 100);
                                    Thread.sleep(200);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                continue;
                            }
                        }
                    }
                });
                t1.start();
                /*try {
                    updateProgress(20,100);
                    Thread.sleep(200);
                    *//*updateProgress(80,100);
                    Thread.sleep(200);
                    updateProgress(100,100);
                    Thread.sleep(200);*//*
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

                try {
                    cam.open();
                    while (!isCancelled()) {
                        if (cam.isImageNew()) {
                            bimg = cam.getImage();
                            updateValue(SwingFXUtils.toFXImage(bimg, null));
                        }
                    }
                    System.out.println("Cancelled, closing cam");
                    cam.close();
                    System.out.println("Cam closed");
                    return getValue();
                } finally {
                    cam.close();
                }
            }

        };
    }

    public int getCamWidth() {
        return resolution.getSize().width;
    }

    public int getCamHeight() {
        return resolution.getSize().height;
    }
}
