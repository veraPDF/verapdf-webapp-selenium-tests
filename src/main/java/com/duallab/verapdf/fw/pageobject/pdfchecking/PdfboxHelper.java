package com.duallab.verapdf.fw.pageobject.pdfchecking;

import com.duallab.verapdf.fw.ApplicationManager;
import com.duallab.verapdf.fw.pageobject.HelperBase;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.WebDriver;

public class PdfboxHelper extends HelperBase {
    static Logger log = Logger.getLogger(PdfboxHelper.class.getName());
    protected WebDriver driver;

    public PdfboxHelper(ApplicationManager manager) {
        super(manager);
        this.driver = manager.getDriver();
    }

    public float compareImages(File fileA, File fileB) {
        try {
            BufferedImage biA = ImageIO.read(fileA);
            DataBuffer dbA = biA.getData().getDataBuffer();
            int sizeA = dbA.getSize();
            log.info(sizeA);

            BufferedImage biB = ImageIO.read(fileB);
            DataBuffer dbB = biB.getData().getDataBuffer();
            int sizeB = dbB.getSize();
            log.info(sizeB);
            int count = 0;
            if (sizeA == sizeB) {
                for (int i = 0; i < sizeA; i++) {
                    if (dbA.getElem(i) == dbB.getElem(i)) {
                        count = count + 1;
                    }
                }
                return (float) (count * 100) / sizeA;
            } else {
                return Float.MAX_VALUE;
            }
        } catch (Exception e) {
            log.info("Failed to compare image files ...");
            return Float.MAX_VALUE;
        }
    }

    public List<BufferedImage> getImages(PDDocument doc, int pageNo) throws IOException {
        log.info("Getting images from page " + pageNo + "...");
        List<BufferedImage> images = PDFFileParser.extractImage(doc, pageNo);
        log.info("Done. " + images.size() + " images have been extracted ");
        return images;
    }

    public String readPdfContent(String url) throws IOException {
        URL pdfUrl = new URL(url);
        InputStream in = pdfUrl.openStream();
        BufferedInputStream bf = new BufferedInputStream(in);
        PDDocument doc = PDDocument.load(bf);
        String content = new PDFTextStripper().getText(doc);
        doc.close();
        return content;
    }

    public String DocumentNameCheck(PDDocument document) throws IOException {
        log.info("Checking Size ... ");
        String str_DocumentName = PDFFileParser.readPDFInfoByArea(document, 10, 30, 48, 250, 20);
        return str_DocumentName;
    }

    public int getPageCount(PDDocument doc) {
        log.info("Checking pageCount ... ");
        int pageCount = doc.getNumberOfPages();
        log.info("pageCount " + pageCount);
        return pageCount;
    }

    public PDDocument getReport(String fileUrl) throws IOException {
        driver.get(fileUrl);

        URL pdfUrl = new URL(fileUrl);
        InputStream in = pdfUrl.openStream();
        BufferedInputStream bf = new BufferedInputStream(in);

        PDDocument document = PDDocument.load(bf);
        return document;
    }
}
