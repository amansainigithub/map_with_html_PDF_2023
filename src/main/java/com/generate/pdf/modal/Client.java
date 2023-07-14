package com.generate.pdf.modal;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class Client {

    private String name;

    private String logo;

    private String invoiceNumber;

    private String dateTime;

    private String clientName;

    private String clientAddress;

    private String clientCity;

    private String clientEmail;

    private String clientMobile;

    private String clientDueDate;

    private String clientAmount;

    private String companyName;

    private String clientState;

    private String clientPincode;

    private String pdfName;

    private String companyLogo;

    private List<CompanyDetails> companyDetails;

    private List<ClientOrder> clientOrders;

}
