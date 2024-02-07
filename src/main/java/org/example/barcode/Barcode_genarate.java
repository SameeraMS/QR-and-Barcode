package org.example.barcode;

import javafx.scene.image.Image;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;

public class Barcode_genarate {
    private  BufferedImage img;
    private Image image;

    public void createImage(String image_name,String myString) throws IOException {

            Code128Bean code128 = new Code128Bean();
            code128.setHeight(15f);
            code128.setModuleWidth(0.3);
            code128.setQuietZone(10);
            code128.doQuietZone(true);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(baos, "image/x-png", 500, BufferedImage.TYPE_BYTE_BINARY, false, 0);
            code128.generateBarcode(canvas, myString);
            canvas.finish();

            //write to png file
            FileOutputStream fos = new FileOutputStream("/Users/sameeramadushan/Documents/final project/barcodes/"+image_name);
            fos.write(baos.toByteArray());


            BufferedImage read = ImageIO.read(new FileInputStream("/Users/sameeramadushan/Documents/final project/barcodes/"+image_name));
            img=read;
            FileInputStream fileInputStream = new FileInputStream("/Users/sameeramadushan/Documents/final project/barcodes/"+image_name);
            image=new Image(fileInputStream);
            String s = Barcode.barcodeRead(read);
            System.out.println(s+"       --okay");
            //
            read.flush();

            fos.flush();
            fos.close();


    }
    public  boolean createImage(String image_name,String myString,String location)  {
        try {
            Code128Bean code128 = new Code128Bean();
            code128.setHeight(15f);
            code128.setModuleWidth(0.3);
            code128.setQuietZone(10);
            code128.doQuietZone(true);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(baos, "image/x-png", 500, BufferedImage.TYPE_BYTE_BINARY, false, 0);
            code128.generateBarcode(canvas, myString);
            canvas.finish();

            //write to png file
            FileOutputStream fos = new FileOutputStream(location);
            fos.write(baos.toByteArray());


            BufferedImage read = ImageIO.read(new FileInputStream(location));
            img=read;
            FileInputStream fileInputStream = new FileInputStream(location);
            image=new Image(fileInputStream);
            String s = Barcode.barcodeRead(read);
            System.out.println(s+"       --okay");
            //
            read.flush();

            fos.flush();
            fos.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
        return true;
    }
    public  BufferedImage grtImgBufferedImage(){
        return img;
    }
    public Image getImg(){
        return image;
    }
}
