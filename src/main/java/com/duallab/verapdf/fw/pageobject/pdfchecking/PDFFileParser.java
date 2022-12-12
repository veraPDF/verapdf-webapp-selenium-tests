package com.duallab.verapdf.fw.pageobject.pdfchecking;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

public class PDFFileParser {

    static Logger log = Logger.getLogger(PDFFileParser.class.getName());

    public static String readPDFInfoByLine(
            String url,
            int pageNoStart,
            int pageNoEnd,
            String strStartIndentifier,
            String strEndIdentifier)
            throws IOException {
        String returnString = "";
        try {
            URL pdfUrl = new URL(url);
            InputStream in = pdfUrl.openStream();
            BufferedInputStream bf = new BufferedInputStream(in);

            PDDocument document = PDDocument.load(bf);
            document.getClass();
            if (!document.isEncrypted()) {
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);
                PDFTextStripper tStripper = new PDFTextStripper();
                tStripper.setStartPage(pageNoStart);
                tStripper.setEndPage(pageNoEnd);
                String pdfFileInText = tStripper.getText(document);

                String strStart = strStartIndentifier;
                String strEnd = strEndIdentifier;
                int startInddex = pdfFileInText.indexOf(strStart);
                int endInddex = pdfFileInText.indexOf(strEnd);
                returnString = pdfFileInText.substring(startInddex, endInddex) + strEnd;
            }
        } catch (Exception e) {
            returnString = "Text not found";
        }
        return returnString;
    }

    public static String readPDFInfoByArea(
            PDDocument document, int pageNo, int x, int y, int w, int h) throws IOException {
        log.info("readPDFInfoByArea starting .... ");
        String returnString = "";
        try {
            if (!document.isEncrypted()) {
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);
                Rectangle cellInfo = new Rectangle(x, y, w, h);
                stripper.addRegion("cellInfo", cellInfo);
                PDPageTree allPages = document.getDocumentCatalog().getPages();
                PDPage pageToCheck = allPages.get(pageNo);
                stripper.extractRegions(pageToCheck);
                returnString = stripper.getTextForRegion("cellInfo");
                log.info("cellInfo " + returnString);

            } else {
                log.info("document.isEncrypted() " + document.isEncrypted());
            }
        } catch (Exception e) {
            returnString = "Region not Found";
            log.info(e.toString());
        }
        return returnString;
    }

    public static List<BufferedImage> extractImage(PDDocument document, int pageNo) {
        List<BufferedImage> imageList = new ArrayList<>();
        log.info("extracting images ... ");
        try {
            if (!document.isEncrypted()) {
                PDPageTree allPages = document.getDocumentCatalog().getPages();
                PDPage pageToCheck = allPages.get(pageNo);
                PDResources pdResources = pageToCheck.getResources();

                for (COSName cosName : pdResources.getXObjectNames()) {
                    PDXObject pdxObject = pdResources.getXObject(cosName);
                    if (pdxObject instanceof PDImageXObject) {
                        //Image
                        PDImageXObject pdImage = (PDImageXObject) pdxObject;
                        BufferedImage bImage = pdImage.getImage();
                        imageList.add(bImage);
                    }
                }
            } else {
                log.info("document.isEncrypted() " + document.isEncrypted());
            }
        } catch (Exception e) {
            log.info("Failed to find images on the page");
            log.info(e.toString());
        }
        return imageList;
    }
}
