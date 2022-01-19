package com.lgren.office.aspose;

import com.aspose.ocr.AsposeOCR;
import com.aspose.ocr.Language;
import com.aspose.ocr.RecognitionResult;
import com.aspose.ocr.RecognitionSettings;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.io.InputStream;

public class AsposeOcr {

    @SneakyThrows
    public static String ocr(InputStream srcStream) {
        AsposeOCR ocr = new AsposeOCR();
        RecognitionSettings settings = new RecognitionSettings();
        settings.setLanguage(Language.Chi);
        RecognitionResult result = ocr.RecognizePage(ImageIO.read(srcStream), settings);
        // // print result
        // System.out.println("Result: \n" + result.recognitionText+"\n\n");
        // for(String n: result.recognitionAreasText) {
        //     System.out.println ( n );
        // }
        // for(Rectangle n: result.recognitionAreasRectangles) {
        //     System.out.println(n.height+":"+n.width+":"+n.x+":"+n.y);
        // }
        // System.out.println("\nJSON:" + result.GetJson());
        // System.out.println("angle:" + result.skew);
        // for(String n: result.warnings) {
        //     System.out.println ( n );
        // }
        // // ExEnd:1
        //
        // System.out.println("OCROperationWithLanguageSelection: execution complete");
        return result.recognitionText;
    }
}
