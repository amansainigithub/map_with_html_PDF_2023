package com.generate.pdf.controllers;

import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/pdf")
public class DownloadHardCoreHTML {

    @GetMapping(value = "/pdfHardCoreHtml" , produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> pdfGenerateDemo(HttpServletResponse response) throws IOException {
        String html="<!doctype html>\r\n"
                + "<html lang=\"en\">\r\n"
                + "<head>\r\n"
                + "    <title>SpringHow html to pdf</title>\r\n"
                + "</head>\r\n"
                + "<body>\r\n"
                + "     <div>\r\n"
                + "        <p >Lorum ipsum some text before image. Lorum ipsum some text before image. Lorum ipsum some text before image. Lorum ipsum some text before image. Lorum ipsum some text before image. Lorum ipsum some text before image. Lorum ipsum some text before image. Lorum ipsum some text before image. </p>\r\n"
                + "        <img src=\"photo.jpg\" alt=\"Orange\">\r\n"
                + "        <p >Lorum ipsum some text after image. Lorum ipsum some text after image. Lorum ipsum some text after image. Lorum ipsum some text after image. Lorum ipsum some text after image. Lorum ipsum some text after image. Lorum ipsum some text after image. Lorum ipsum some text after image. Lorum ipsum some text after image.</p>\r\n"
                + "        <table>\r\n"
                + "            <tr><th>Product</th><th>Quantity</th><th>Price</th><th>Total</th></tr>\r\n"
                + "            <tr><td>Jeans</td><td>2</td><td>10.99</td><td>20.98</td></tr>\r\n"
                + "            <tr><td>Shirt</td><td>2</td><td>7.99</td><td>14.98</td></tr>\r\n"
                + "        </table>\r\n"
                + "     </div>\r\n"
                + "</body>\r\n"
                + "</html>";

        ByteArrayOutputStream target = new ByteArrayOutputStream();
        HtmlConverter.convertToPdf(html, target);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(target.toByteArray());
    }

}
