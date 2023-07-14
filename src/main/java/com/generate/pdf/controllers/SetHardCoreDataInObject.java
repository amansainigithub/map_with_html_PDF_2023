package com.generate.pdf.controllers;

import com.generate.pdf.modal.Client;
import com.generate.pdf.modal.ClientOrder;
import com.generate.pdf.modal.CompanyDetails;

import java.util.ArrayList;
import java.util.List;

public class SetHardCoreDataInObject {

    public static Client setData()
    {
        Client client = new Client();
        client.setClientName("Rajeev Mishra");
        client.setCompanyName("NUMCODE PREFIX SOLUTION");
        client.setLogo("NO-LOGO");
        client.setClientPincode("201303");
        client.setClientState("Uttar Pradesh");
        client.setInvoiceNumber("IN-457812010");
        client.setDateTime("2023-30-01");
        client.setClientAddress("TOWER-EXPRESS TRADE, 1, 1A, Slip Rd, Film City");
        client.setClientCity("NOIDA");
        client.setPdfName("#INVOICE NUM-CODE");
        client.setClientEmail("Client@gmail.com");
        client.setClientMobile("91+ 7894875414");
        client.setDateTime("2023-12-07");
        client.setClientDueDate("2023-15-29");
        client.setClientAmount("45777");
        client.setCompanyLogo("https://www.freepnglogos.com/uploads/google-logo-png/google-logo-png-webinar-optimizing-for-success-google-business-webinar-13.png");


        List<CompanyDetails> companyDetailsList = new ArrayList<>();
        CompanyDetails companyDetails = new CompanyDetails();
        companyDetails.setCompanyName("Numerics Keys Solution");
        companyDetails.setCompanyAddress("Epitome, Cyber City, 10th Floor, Tower B & C, DLF");
        companyDetails.setCompanyCity("Gurugram");
        companyDetails.setCompanyPincode("247001");
        companyDetails.setCompanyIp("127.45.41.103");
        companyDetails.setCompanyWebsite("www.prefix-suffix.com");
        companyDetailsList.add(companyDetails);

        //Set Company Details list
        client.setCompanyDetails(companyDetailsList);


        //First Item
        List<ClientOrder> clientOrderList = new ArrayList<>();
        ClientOrder clientOrder1 = new ClientOrder();
        clientOrder1.setId(124578455l);
        clientOrder1.setItemName("Slimmest Phone in Segment");
        clientOrder1.setItemDesc("Enjoy smooth multitasking and effortless app");
        clientOrder1.setRate("8999");
        clientOrder1.setTax("4%");
        clientOrder1.setSubTotal("220");
        clientOrder1.setAmount("4");
        clientOrder1.setTotal(String.valueOf(Integer.parseInt(clientOrder1.getRate()) * Integer.parseInt(clientOrder1.getAmount())));

        //Second Item
        ClientOrder clientOrder2 = new ClientOrder();
        clientOrder2.setId(12445666l);
        clientOrder2.setItemName("Fastrack New Limitless FS1");
        clientOrder2.setItemDesc("Biggest 1.95” Horizon Curve Display: Fastrack Limitless FS1");
        clientOrder2.setRate("15999");
        clientOrder2.setTax("6%");
        clientOrder2.setSubTotal("334");
        clientOrder2.setAmount("3");
        clientOrder2.setTotal(String.valueOf(Integer.parseInt(clientOrder2.getRate()) * Integer.parseInt(clientOrder2.getAmount())));

        //Second Item
        ClientOrder clientOrder3 = new ClientOrder();
        clientOrder3.setId(12445666l);
        clientOrder3.setItemName("Titan Neo Analog Dial Men's Watch");
        clientOrder3.setItemDesc("Dial Color: Champagne, Case Shape: Round,");
        clientOrder3.setRate("13200");
        clientOrder3.setTax("7%");
        clientOrder3.setSubTotal("447");
        clientOrder3.setAmount("2");
        clientOrder3.setTotal(String.valueOf(Integer.parseInt(clientOrder3.getRate()) * Integer.parseInt(clientOrder3.getAmount())));

        //Second Item
        ClientOrder clientOrder4 = new ClientOrder();
        clientOrder4.setId(12445666l);
        clientOrder4.setItemName("Boat induction watch Model Angry");
        clientOrder4.setItemDesc("Champ For all new things");
        clientOrder4.setRate("4500");
        clientOrder4.setTax("16%");
        clientOrder4.setSubTotal("100");
        clientOrder4.setAmount("3");
        clientOrder4.setTotal(String.valueOf(Integer.parseInt(clientOrder3.getRate()) * Integer.parseInt(clientOrder3.getAmount())));


        //Second Item
        ClientOrder clientOrder5 = new ClientOrder();
        clientOrder5.setId(12440066l);
        clientOrder5.setItemName("Neck Band for noise B Atom 150");
        clientOrder5.setItemDesc("make to World Global in Coll Purchase Things");
        clientOrder5.setRate("7998");
        clientOrder5.setTax("26%");
        clientOrder5.setSubTotal("2");
        clientOrder5.setAmount("7");
        clientOrder5.setTotal(String.valueOf(Integer.parseInt(clientOrder3.getRate()) * Integer.parseInt(clientOrder3.getAmount())));

        //Add Client Object to List
        clientOrderList.add(clientOrder1);
        clientOrderList.add(clientOrder2);
        clientOrderList.add(clientOrder3);
        clientOrderList.add(clientOrder4);
        clientOrderList.add(clientOrder5);

        //Set Client Order List to Client
        client.setClientOrders(clientOrderList);


        // System.out.println("Client Data " + client);
        return client;
    }
}
