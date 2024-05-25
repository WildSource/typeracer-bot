package app.backend.components;

import app.Application;
import app.backend.ApplicationParent;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class OpticalCharacterReader extends ApplicationParent {
    private Tesseract tesseract;

    public OpticalCharacterReader(Application application) {
        super(application);
        this.tesseract = new Tesseract();
        this.tesseract.setDatapath("tessdata");
        this.tesseract.setLanguage("eng");
        this.tesseract.setPageSegMode(1);
        this.tesseract.setOcrEngineMode(1);
    }

    public String readScreenShot(File image) {
        String result = null;

        try {
            result = this.tesseract.doOCR(image);

        } catch (TesseractException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(result);

        return result;
    }

    public String readScreenShot(BufferedImage image) {
        String result = null;

        try {
            result = this.tesseract.doOCR(image);

        } catch (TesseractException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(result);

        return result;
    }
}
