package app.backend;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class OpticalCharacterReader {
    private Tesseract tesseract;

    public OpticalCharacterReader() {
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("tessdata");
        tesseract.setLanguage("eng");
        tesseract.setPageSegMode(1);
        tesseract.setOcrEngineMode(1);
    }

    public String readScreenShot(File image) {
        String result = null;

        try {
            result = tesseract.doOCR(image);

        } catch (TesseractException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(result);

        return result;
    }
}
