package com.wuyue.until;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamSource;


import java.io.InputStream;

import static com.wuyue.until.MathmlUtils.xslConvert;

public class MathMLToLatex {
    public static String convertMathMLToLatex(String mml) {
        mml = mml.substring(mml.indexOf("?>") + 2, mml.length());
        URIResolver r = new URIResolver() {
            @Override
            public Source resolve(String href, String base) throws TransformerException {
                InputStream inputStream = MathmlUtils.class.getResourceAsStream("src/main/resources/conventer/mml2tex/" + href);
                return new StreamSource(inputStream);
            }
        };
        String latex = xslConvert(mml, "src/main/resources/conventer/mml2tex/mmltex.xsl", r);
        if (latex != null && latex.length() > 1) {
            latex = latex.substring(1, latex.length() - 1);
        }
        return latex;
    }
}
