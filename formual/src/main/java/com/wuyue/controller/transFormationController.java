/*
package com.wuyue.controller;

import fmath.conversion.ConvertFromLatexToMathML;
import fmath.conversion.ConvertFromMathMLToLatex;
import org.springframework.web.bind.annotation.*;


@RestController
public class transFormationController {

    @RequestMapping("/latexToMathML")
    @ResponseBody
    public String latexToMathML(@RequestBody String formula){

        if(formula==null||"".equals(formula.trim())){
            return "";
        }
        String a = " ";
        String mathMl = ConvertFromLatexToMathML.convertToMathML(formula);
        System.out.println(mathMl);
        return mathMl;
    }

    @RequestMapping("/mathMLToLatex")
    @ResponseBody
    public String mathToLatex(@RequestBody String formula){
        if(formula==null||"".equals(formula.trim())){
            return "";
        }
        String latex = ConvertFromMathMLToLatex.convertToLatex(formula);
        System.out.println(latex);
        return latex;
    }

}
*/
