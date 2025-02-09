package gym_tickets.configurations;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.boot.autoconfigure.ssl.SslProperties;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

public class BarCodeGenerator {

    public static String generateBarCode(String barCodeText) throws WriterException, IOException {
        String filePath = "D:\\Programiranje\\sts\\gym_tickets\\src\\main\\resources\\barCodes\\" + barCodeText + ".png";
        BitMatrix matrix = new MultiFormatWriter().encode(barCodeText, BarcodeFormat.CODE_128, 300, 150);
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(matrix, "PNG", path);

        return filePath;

    }

    public static String getBarCodeBase64(String barCodeText) throws WriterException, IOException {
        String filePath = generateBarCode(barCodeText);
        byte[] imageBytes = Files.readAllBytes(Path.of(filePath));
        return Base64.getEncoder().encodeToString(imageBytes);
    }

}
