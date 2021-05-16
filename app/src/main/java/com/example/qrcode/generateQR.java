package com.example.qrcode;

public class generateQR {
    public static void main(String[] args) {
        String myCodeText = "https://crunchify.com";
        String filePath = "//cdn.crunchify.com/Users/app/Document/Crunchify.com-QRCode.png";
        int size = 512;
        String crunchifyFileType = "png";
        File crunchifyFile = new File(filePath);
        try {

            Map<EncodeHintType, Object> crunchifyHintType = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            crunchifyHintType.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            // Now with version 3.4.1 you could change margin (white border size)
            crunchifyHintType.put(EncodeHintType.MARGIN, 1); /* default = 4 */
            Object put = crunchifyHintType.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

            QRCodeWriter mYQRCodeWriter = new QRCodeWriter(); // throws com.google.zxing.WriterException
            BitMatrix crunchifyBitMatrix = mYQRCodeWriter.encode(myCodeText, BarcodeFormat.QR_CODE, size,
                    size, crunchifyHintType);
            int CrunchifyWidth = crunchifyBitMatrix.getWidth();

            // The BufferedImage subclass describes an Image with an accessible buffer of crunchifyImage data.
            BufferedImage crunchifyImage = new BufferedImage(CrunchifyWidth, CrunchifyWidth,
                    BufferedImage.TYPE_INT_RGB);

            // Creates a Graphics2D, which can be used to draw into this BufferedImage.
            crunchifyImage.createGraphics();

            // This Graphics2D class extends the Graphics class to provide more sophisticated control over geometry, coordinate transformations, color management, and text layout.
            // This is the fundamental class for rendering 2-dimensional shapes, text and images on the Java(tm) platform.
            Graphics2D crunchifyGraphics = (Graphics2D) crunchifyImage.getGraphics();

            // setColor() sets this graphics context's current color to the specified color.
            // All subsequent graphics operations using this graphics context use this specified color.
            crunchifyGraphics.setColor(Color.white);

            // fillRect() fills the specified rectangle. The left and right edges of the rectangle are at x and x + width - 1.
            crunchifyGraphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);

            // TODO: Please change this color as per your need
            crunchifyGraphics.setColor(Color.BLUE);

            for (int i = 0; i < CrunchifyWidth; i++) {
                for (int j = 0; j < CrunchifyWidth; j++) {
                    if (crunchifyBitMatrix.get(i, j)) {
                        crunchifyGraphics.fillRect(i, j, 1, 1);
                    }
                }
            }

            // A class containing static convenience methods for locating
            // ImageReaders and ImageWriters, and performing simple encoding and decoding.
            ImageIO.write(crunchifyImage, crunchifyFileType, crunchifyFile);

            System.out.println("\nCongratulation.. You have successfully created QR Code.. \n" +
                    "Check your code here: " + filePath);
        } catch (WriterException e) {
            System.out.println("\nSorry.. Something went wrong...\n");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
