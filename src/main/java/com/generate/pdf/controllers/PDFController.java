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
public class PDFController {

    @Autowired
    ResourceLoader resourceLoader;

    String htmlData = "";

    long grandTotal = 0l;


    @GetMapping(value = "/pdfGenerate" , produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> pdfGenerate(HttpServletResponse response) throws IOException {
        Map<Object,Object> nodeMap = new HashMap<>();
        Client client =  SetHardCoreDataInObject.setData();
      try {
          ObjectMapper objectMapper = new ObjectMapper();
          String convertResult ="";
          String htmlConvertString = "";

          StringBuilder builder=new StringBuilder();

          //Read Topper Header
          Resource invoice1 = resourceLoader.getResource("classpath:invoice1.html");
          byte[] copyToByteArray1 = FileCopyUtils.copyToByteArray(invoice1.getInputStream());
          htmlConvertString = new String(copyToByteArray1, StandardCharsets.UTF_8);
          Map<Object,Object> mapNode1 =  objectMapper.convertValue(client,Map.class);
          convertResult =  converter(htmlConvertString,mapNode1);
          builder.append(convertResult);

          //Invoice 2 Dynamically TR and Rendering Data
          Resource invoice2 = resourceLoader.getResource("classpath:invoice2.html");
          byte[] copyToByteArray2 = FileCopyUtils.copyToByteArray(invoice2.getInputStream());
          htmlConvertString = new String(copyToByteArray2, StandardCharsets.UTF_8);
            int count = 1 ;
          for(ClientOrder CO : client.getClientOrders())
          {
              long fTotal = Long.parseLong(CO.getRate())*Long.parseLong(CO.getAmount())*Long.parseLong(CO.getTotal());
              CO.setFinalTotal(String.valueOf(fTotal));

              //Grand Total
              grandTotal +=fTotal;

                CO.setCount(count+"");
              Map<Object,Object> mapNode2 =  objectMapper.convertValue(CO,Map.class);
              convertResult =  converter(htmlConvertString,mapNode2);
              builder.append(convertResult);

              count++;
          }

          //Invoice 3 Grand Total
          Resource invoice3 = resourceLoader.getResource("classpath:invoice3.html");
          byte[] copyToByteArray3 = FileCopyUtils.copyToByteArray(invoice3.getInputStream());
          htmlConvertString = new String(copyToByteArray3, StandardCharsets.UTF_8);
          nodeMap.put("AllTotal",String.valueOf(grandTotal));
          convertResult =  converter(htmlConvertString,nodeMap);
          builder.append(convertResult);
          nodeMap.clear();

          //Invoice 4
          Resource invoice4 = resourceLoader.getResource("classpath:invoice4.html");
          byte[] copyToByteArray4 = FileCopyUtils.copyToByteArray(invoice4.getInputStream());
          htmlConvertString= new String(copyToByteArray4, StandardCharsets.UTF_8);
          builder.append(htmlConvertString);



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
            e.getMessage();
            return null;
      }
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




    @GetMapping(value = "/pdfHtmlGenerate")
    public String pdfHtmlGenerate(HttpServletResponse response) throws IOException {
        Map<Object, Object> nodeMap = new HashMap<>();
        Client client = SetHardCoreDataInObject.setData();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String convertResult = "";
            String htmlConvertString = "";

            StringBuilder builder = new StringBuilder();

            //Read Topper Header
            Resource invoice1 = resourceLoader.getResource("classpath:invoice1.html");
            byte[] copyToByteArray1 = FileCopyUtils.copyToByteArray(invoice1.getInputStream());
            htmlConvertString = new String(copyToByteArray1, StandardCharsets.UTF_8);
            Map<Object, Object> mapNode1 = objectMapper.convertValue(client, Map.class);
            convertResult = converter(htmlConvertString, mapNode1);
            builder.append(convertResult);

            //Invoice 2 Dynamically TR and Rendering Data
            Resource invoice2 = resourceLoader.getResource("classpath:invoice2.html");
            byte[] copyToByteArray2 = FileCopyUtils.copyToByteArray(invoice2.getInputStream());
            htmlConvertString = new String(copyToByteArray2, StandardCharsets.UTF_8);
            int count = 1;
            for (ClientOrder CO : client.getClientOrders()) {
                long fTotal = Long.parseLong(CO.getRate()) * Long.parseLong(CO.getAmount()) * Long.parseLong(CO.getTotal());
                CO.setFinalTotal(String.valueOf(fTotal));

                //Grand Total
                grandTotal += fTotal;

                CO.setCount(count + "");
                Map<Object, Object> mapNode2 = objectMapper.convertValue(CO, Map.class);
                convertResult = converter(htmlConvertString, mapNode2);
                builder.append(convertResult);

                count++;
            }

            //Invoice 3 Grand Total
            Resource invoice3 = resourceLoader.getResource("classpath:invoice3.html");
            byte[] copyToByteArray3 = FileCopyUtils.copyToByteArray(invoice3.getInputStream());
            htmlConvertString = new String(copyToByteArray3, StandardCharsets.UTF_8);
            nodeMap.put("AllTotal", String.valueOf(grandTotal));
            convertResult = converter(htmlConvertString, nodeMap);
            builder.append(convertResult);
            nodeMap.clear();

            //Invoice 4
            Resource invoice4 = resourceLoader.getResource("classpath:invoice4.html");
            byte[] copyToByteArray4 = FileCopyUtils.copyToByteArray(invoice4.getInputStream());
            htmlConvertString = new String(copyToByteArray4, StandardCharsets.UTF_8);
            builder.append(htmlConvertString);


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
            return builder.toString();
        } catch (Exception e) {
            return e.toString();
        }
    }





}
