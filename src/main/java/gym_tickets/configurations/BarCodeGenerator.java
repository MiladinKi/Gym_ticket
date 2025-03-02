package gym_tickets.configurations;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.boot.autoconfigure.ssl.SslProperties;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

public class BarCodeGenerator {

    public static BufferedImage generateBarCode(String barCodeText) throws WriterException, IOException {
        int width = 300;
        int height = 100;
        BarcodeFormat barcodeFormat = BarcodeFormat.CODE_128;
//        String filePath = "D:\\Programiranje\\sts\\gym_tickets\\src\\main\\resources\\barCodes\\" + barCodeText + ".png";
        BitMatrix bitMatrix = new MultiFormatWriter().encode(barCodeText, BarcodeFormat.CODE_128, width, height);
//        Path path = FileSystems.getDefault().getPath(filePath);
//        MatrixToImageWriter.writeToPath(matrix, "PNG", path);
        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
        return image;

    }

    public static String getBarCodeBase64(String barCodeText) throws WriterException, IOException {
        BufferedImage filePath = generateBarCode(barCodeText);
        byte[] imageBytes = Files.readAllBytes(Path.of(String.valueOf(filePath)));
        return Base64.getEncoder().encodeToString(imageBytes);
    }

}
