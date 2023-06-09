package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order() {
    }



    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        this.id=id;
        int hours=Integer.parseInt(deliveryTime.substring(0,2));
        int minutes=Integer.parseInt(deliveryTime.substring(3));
        int dTime=hours*60 +minutes;
        this.deliveryTime=dTime;
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}

    public void setId(String id) {
        this.id = id;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
