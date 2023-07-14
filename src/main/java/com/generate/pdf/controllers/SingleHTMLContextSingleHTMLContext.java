package com.generate.pdf.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.generate.pdf.modal.Client;
import com.generate.pdf.modal.ClientOrder;
import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pdf")
public class SingleHTMLContextSingleHTMLContext {

    @Autowired
    ResourceLoader resourceLoader;

    String htmlData = "";

    long grandTotal = 0l;


    @GetMapping(value = "/pdfGenerateSingleHtmlContext" , produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> pdfGenerate(HttpServletResponse response) throws IOException {
        Map<Object,Object> nodeMap = new HashMap<>();
        Client client =  SetHardCoreDataInObject.setData();
        String htmlConvertString = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String convertResult ="";
            StringBuilder builder=new StringBuilder();

            //Read Topper Header
            Resource invoice1 = resourceLoader.getResource("classpath:SingleHtmlContext.html");
            byte[] copyToByteArray1 = FileCopyUtils.copyToByteArray(invoice1.getInputStream());
            htmlConvertString = new String(copyToByteArray1, StandardCharsets.UTF_8);


            //Splitting the HTML String
            String splitHtml[] = htmlConvertString.split("<--BR-->");


                //Put Dynamically Date in HTMl with Split Array Split[0] Position
                if(!splitHtml[0].isEmpty())
                {
                    Map<Object,Object> mapNode1 =  objectMapper.convertValue(client,Map.class);
                    convertResult =  converter(splitHtml[0],mapNode1);
                    builder.append(convertResult);
                }

                //Put Dynamically Date in HTMl with Split Array Split[1] Position
                if(!splitHtml[1].isEmpty())
                {
                    int count = 1 ;
                    for(ClientOrder CO : client.getClientOrders())
                    {
                        long fTotal = Long.parseLong(CO.getRate())*Long.parseLong(CO.getAmount())*Long.parseLong(CO.getTotal());
                        CO.setFinalTotal(String.valueOf(fTotal));

                        //Grand Total
                        grandTotal +=fTotal;

                        CO.setCount(count+"");
                        Map<Object,Object> mapNode2 =  objectMapper.convertValue(CO,Map.class);
                        convertResult =  converter(splitHtml[1],mapNode2);
                        builder.append(convertResult);
                        count++;
                    }
                }

                //Put Dynamically Date in HTMl with Split Array Split[2] Position
                if(!splitHtml[2].isEmpty())
                {
                    convertResult =  converter(splitHtml[2],nodeMap);
                    builder.append(convertResult);
                    nodeMap.clear();
                }

                //Put Dynamically Date in HTMl with Split Array Split[3] Position
                if(!splitHtml[3].isEmpty()){
                    builder.append(splitHtml[3]);
                }

            System.out.println("===============Download Starting ====== Prepare to Fly==========================");
            ByteArrayOutputStream target = new ByteArrayOutputStream();
            HtmlConverter.convertToPdf(builder.toString(), target);

            //Direct Download PDF
//        ServletOutputStream outputStream = response.getOutputStream();
//        response.setContentType("APPLICATION/OCTET-STREAM");
//        response.setHeader("Content-Disposition","attachment; filename=\"" + "newPDF.pdf" + "\"");
//        outputStream.write(target.toByteArray());
//        return null;

            //Show PDF VIEW MODE
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(target.toByteArray());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        return ResponseEntity.ok("");
    }


    public static String converter(String htmlData , Map<Object,Object> map)
    {
        for (Object key : map.keySet()) {
            System.out.println("Key :: "+key);
            try {
                String value = (String) map.get(key);
                System.out.println("Value :: "+value);
                value = value == null ? "null" : value;
                htmlData = htmlData.replace("{{" + key + "}}", value);
            }
            catch (Exception e)
            {
                e.getMessage();
            }
        }
        return htmlData;
    }
}
