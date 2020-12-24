package com.wuyue.until;

import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.docx4j.wml.ContentAccessor;

public class MathmlUtils {
    /**
     * <p>Description: xsl转换器</p>
     */
    public static String xslConvert(String s, String xslpath, URIResolver uriResolver) {
        TransformerFactory tFac = TransformerFactory.newInstance();
        if (uriResolver != null)
            tFac.setURIResolver(uriResolver);
        StreamSource xslSource = new StreamSource(MathmlUtils.class.getResourceAsStream(xslpath));
        StringWriter writer = new StringWriter();
        try {
            Transformer t = tFac.newTransformer(xslSource);
            Source source = new StreamSource(new StringReader(s));
			System.out.println(source);
			System.out.println("##############################");
            Result result = new StreamResult(writer);
            t.transform(source, result);
			System.out.println(result);
        } catch (TransformerException e) {
            System.out.println(e.getMessage());
        }
        return writer.getBuffer().toString();
    }

    /**
     * <p>Description: 将mathml转为latx </p>
     * @param mml
     * @return
     */
    public static String convertMML2Latex(String mml) {
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

    /**
     * <p>Description: office mathml转为mml </p>
     * @param xml
     * @return
     */
    public static String convertOMML2MML(String xml) {
        String result = xslConvert(xml, "src/main/resources/conventer/OMML2MML.XSL", null);
        return result;
    }

    public static String convertMML2OMML(String xml) {
        String result = xslConvert(xml, "C:/Users/wuyue/Desktop/new/formual/src/main/resources/conventer/MML2OMML.XSL", null);
        return result;
    }
    /**
     * <p>Description: 获取所有元素 </p>
     * @paramxml
     * @return
     */
    public static List<Object> getAllElementFromObject(Object obj) {
        List<Object> result = new ArrayList<Object>();
        if (obj instanceof JAXBElement)
            obj = ((JAXBElement<?>) obj).getValue();

        if (obj instanceof ContentAccessor) {
            List<?> children = ((ContentAccessor) obj).getContent();
            for (Object child : children) {
                result.addAll(getAllElementFromObject(child));
            }
        }
        return result;
    }
}
