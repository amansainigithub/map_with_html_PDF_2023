package com.generate.pdf.modal;

import lombok.Data;

@Data
public class ClientOrder {

    private long id;

    private String itemName;

    private String itemDesc;

    private String rate;

    private String amount;

    private String total;

    private String subTotal;

    private String tax;

    private String finalTotal;
    private String count;


}
