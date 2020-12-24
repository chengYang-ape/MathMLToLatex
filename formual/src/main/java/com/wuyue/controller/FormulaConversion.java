package com.wuyue.controller;

import fmath.conversion.ConvertFromLatexToMathML;
import fmath.conversion.ConvertFromMathMLToLatex;
import org.springframework.web.bind.annotation.*;

@RestController
public class FormulaConversion {
    @PostMapping("/LatexToMathML")
    public String LatexToMathML(String inStr) {
        try {
            if (inStr == null || "".equals(inStr.trim())) {
                return "";
            }
            if (!inStr.startsWith("$")) {
                inStr = "$" + inStr;
            }
            if (!inStr.endsWith("$")) {
                inStr = inStr + "$";
            }
            //去掉空格
            inStr = inStr.replaceAll("[ ]", "");
            String mathml = ConvertFromLatexToMathML.convertToMathML(inStr);
            if (null != mathml) {
                mathml = mathml.replaceFirst("<math ", "<math xmlns=\"http://www.w3.org/1998/Math/MathML\" ");
            }
            return mathml;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @PostMapping("/MathMLToLatex")
    public  String MathMLToLatex(String inStr) {
        try{
        if(inStr==null||"".equals(inStr.trim())){
            return "";
        }
        //替换无关属性
        inStr = inStr.replaceFirst("<math(.+)?>", "<math>");
        String latex = ConvertFromMathMLToLatex.convertToLatex(inStr);
        return latex;
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    @RequestMapping("/")
    @ResponseBody
    public String hello(){
        return "hello";
    }
}