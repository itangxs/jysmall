package cn.qhjys.mall.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

import org.apache.commons.lang.math.JVMRandom;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class ZxingUtil {
	public static String made(String data,String path) throws IOException, WriterException{
         int qrcodeWidth = 300;
        int qrcodeHeight = 300;
        String qrcodeFormat = "png";
        HashMap<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, qrcodeWidth, qrcodeHeight, hints);

        BufferedImage image = new BufferedImage(qrcodeWidth, qrcodeHeight, BufferedImage.TYPE_INT_RGB);
        JVMRandom random = new JVMRandom();
        String qrcodeFilePath = "zxing/"+random.nextInt() + "." + qrcodeFormat;
        File QrcodeFile = new File(path+ qrcodeFilePath);
        ImageIO.write(image, qrcodeFormat, QrcodeFile);
        MatrixToImageWriter.writeToFile(bitMatrix, qrcodeFormat, QrcodeFile);
		return "/"+qrcodeFilePath;
	}
}
