package javafx_qrcodewriter;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 */
public class JavaFX_QRCodeWriter extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String inputUrl = "http://java-buddy.blogspot.com/";
        int width = 300;
        int height = 300;
        String fileType = "png";
        ImageView qrView = new ImageView();
                try {
        BitMatrix bitMatrix = qrCodeWriter.encode(inputUrl, BarcodeFormat.QR_CODE, width, height);

        //Create a Canvas (a place to draw on), with a 2D Graphic (a kind of drawing)
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc2D = canvas.getGraphicsContext2D();

        //in white, paint a rectangle on it, with the full size
        gc2D.setFill(javafx.scene.paint.Color.WHITE);
        gc2D.fillRect(0, 0, width, height);

        //start painting in black: each bit/pixel set in the bitMatix
        gc2D.setFill(javafx.scene.paint.Color.BLACK);
        for (int v = 0; v < height; v++) {
        for (int h = 0; h < width; h++) {
        if (bitMatrix.get(v, h)) {
        gc2D.fillRect(h, v, 1, 1);
        }
        }
        }

        //Take a snapshot of the canvas and set it as an image in the ImageView control
        qrView.setImage(canvas.snapshot(null, null));
        } catch (WriterException e) {
        e.printStackTrace();
        }
        StackPane root = new StackPane();
        root.getChildren().add(qrView);
        
        Scene scene = new Scene(root, 350, 350);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
