package com.wby.common.core.utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Base64OutputStream;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Description 二维码工具类
 * @Author wby
 * @Date 2020/3/5 19:40
 */
public class QRCodeUtil {
    public static final String QRCODE_DEFAULT_CHARSET = "UTF-8";

    public static final int QRCODE_DEFAULT_HEIGHT = 150;

    public static final int QRCODE_DEFAULT_WIDTH = 150;

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;


    /**
     * 使用默认宽度和高度创建二维码
     *
     * @param data 内容
     * @return
     */
    public static BufferedImage createQRCode(String data) {
        return createQRCode(data, QRCODE_DEFAULT_WIDTH, QRCODE_DEFAULT_HEIGHT);
    }

    /**
     * 使用字符集UTF-8创建二维码
     *
     * @param data   内容
     * @param width  宽度
     * @param height 高度
     * @return
     */
    public static BufferedImage createQRCode(String data, int width, int height) {
        return createQRCode(data, QRCODE_DEFAULT_CHARSET, width, height);
    }

    /**
     * 创建二维码
     *
     * @param data    内容
     * @param charset 字符集
     * @param width   宽度
     * @param height  高度
     * @return
     */
    public static BufferedImage createQRCode(String data, String charset, int width, int height) {
        Map hint = new HashMap(16);
        hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hint.put(EncodeHintType.CHARACTER_SET, charset);

        return createQRCode(data, charset, hint, width, height);
    }

    /**
     * 创建二维码
     *
     * @param data    内容
     * @param charset 字符集
     * @param hint
     * @param width   宽度
     * @param height  高度
     * @return
     */
    public static BufferedImage createQRCode(String data, String charset, Map<EncodeHintType, ?> hint, int width,
                                             int height) {
        BitMatrix matrix;
        try {
            matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE,
                    width, height, hint);
            return toBufferedImage(matrix);
        } catch (WriterException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    /**
     * 使用默认宽度和高度及logo文件创建二维码
     *
     * @param data     内容
     * @param logoFile logo文件
     * @return
     */
    public static BufferedImage createQRCodeWithLogo(String data, File logoFile) {
        return createQRCodeWithLogo(data, QRCODE_DEFAULT_WIDTH, QRCODE_DEFAULT_HEIGHT, logoFile);
    }

    /**
     * 使用默认字符集UTF-8和logo文件创建二维码
     *
     * @param data     内筒
     * @param width    宽度
     * @param height   高度
     * @param logoFile logo文件
     * @return
     */
    public static BufferedImage createQRCodeWithLogo(String data, int width, int height, File logoFile) {
        return createQRCodeWithLogo(data, QRCODE_DEFAULT_CHARSET, width, height, logoFile);
    }

    /**
     * 创建带logo的二维码
     *
     * @param data     内容
     * @param charset  字符集
     * @param width    宽度
     * @param height   高度
     * @param logoFile logo文件
     * @return
     */
    public static BufferedImage createQRCodeWithLogo(String data, String charset, int width, int height, File logoFile) {
        Map hint = new HashMap();
        hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hint.put(EncodeHintType.CHARACTER_SET, charset);

        return createQRCodeWithLogo(data, charset, hint, width, height, logoFile);
    }

    /**
     * @param data     文件
     * @param charset  字符集
     * @param hint
     * @param width    高度
     * @param height   宽度
     * @param logoFile logo文件
     * @return
     */
    public static BufferedImage createQRCodeWithLogo(String data, String charset, Map<EncodeHintType, ?> hint,
                                                     int width, int height, File logoFile) {
        try {
            BufferedImage qrcode = createQRCode(data, charset, hint, width, height);
            BufferedImage logo = ImageIO.read(logoFile);
            int deltaHeight = height - logo.getHeight();
            int deltaWidth = width - logo.getWidth();

            BufferedImage combined = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = (Graphics2D) combined.getGraphics();
            g.drawImage(qrcode, 0, 0, null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            g.drawImage(logo, (int) Math.round(deltaWidth / 2), (int) Math.round(deltaHeight / 2), null);

            return combined;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 创建Base64
     *
     * @param image
     * @return
     */
    public static String getImageBase64String(BufferedImage image) {
        String result = null;
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            OutputStream b64 = new Base64OutputStream(os);
            ImageIO.write(image, "png", b64);
            result = os.toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 将Base64转化为图片
     *
     * @param base64ImageString
     * @param file
     */
    public static void convertBase64StringToImage(String base64ImageString, File file) {
        FileOutputStream os;
        try {
            Base64 d = new Base64();
            byte[] bs = d.decode(base64ImageString);
            os = new FileOutputStream(file.getAbsolutePath());
            os.write(bs);
            os.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * @param dir     生成二维码路径
     * @param content 内容
     * @return
     */
    public static String createQRCode(String dir, String content) {
        return createQRCodeImage(dir, content, 300, 300);
    }

    /**
     * @param dir     生成二维码路径
     * @param content 内容
     * @param width   宽度
     * @param height  高度
     * @return
     */
    public static String createQRCodeImage(String dir, String content, int width, int height) {
        try {
            File dirFile = null;
            if (StringUtils.isNotBlank(dir)) {
                dirFile = new File(dir);
                if (!dirFile.exists()) {
                    dirFile.mkdirs();
                }
            }
            String qrcodeFormat = "png";
            HashMap<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);

            File file = new File(dir, UUID.randomUUID().toString() + "." + qrcodeFormat);
            MatrixToImageWriter.writeToPath(bitMatrix, qrcodeFormat, file.toPath());
            return file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 读取二维码内容
     *
     * @param filePath 二维码路径
     * @return
     */
    public static String decodeQR(String filePath) {
        String retStr = "";
        if ("".equalsIgnoreCase(filePath) && filePath.length() == 0) {
            return "图片路径为空!";
        }
        try {
            BufferedImage bufferedImage = ImageIO.read(new FileInputStream(filePath));
            LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap bitmap = new BinaryBitmap(binarizer);
            HashMap<DecodeHintType, Object> hintTypeObjectHashMap = new HashMap<>();
            hintTypeObjectHashMap.put(DecodeHintType.CHARACTER_SET, "UTF-8");
            Result result = new MultiFormatReader().decode(bitmap, hintTypeObjectHashMap);
            retStr = result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retStr;
    }

    public static void main(String[] args) throws IOException, NotFoundException {
        String data = "http://www.baidu.com";
        // logo图片路径(这是相对路径)
        File logoFile = new File("img/logo/28.png");
        BufferedImage image = QRCodeUtil.createQRCode(data);
        // 生成图片 不需要的话注释即可
        //ImageIO.write(image, "png", new File("result7.png"));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", os);
    }
}
