package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRepository {

    Map<String,Order> orderDb=new HashMap<>();
    Map<String,DeliveryPartner>partnerDb=new HashMap<>();
    Map<String,String>orderPartnerDb=new HashMap<>();
    public String addOrder(Order order){
        String id=order.getId();
        orderDb.put(id,order);
        return "Order added successfully";
    }

    public String addPartner(String partnerId){
        DeliveryPartner deliveryPartner=new DeliveryPartner(partnerId);
        partnerDb.put(partnerId,deliveryPartner);
        return "Partner added successfully";
    }

    public String addOrderPartnerPair(String orderId, String partnerId){
      orderPartnerDb.put(orderId,partnerId);
      return "Order partner added successfully";
    }

    public List<Order> getAllOrders(){
      List<Order>orders=new ArrayList<>();
      for(Order order:orderDb.values())
          orders.add(order);
      return orders;
    }

    public List<DeliveryPartner> getAllPartners(){
        List<DeliveryPartner>deliveryPartners=new ArrayList<>();
        for(DeliveryPartner deliveryPartner:partnerDb.values())
            deliveryPartners.add(deliveryPartner);
        return deliveryPartners;
    }


    public List<String> getAllOrdersPartner(){
        List<String>partner=new ArrayList<>();
        for(String id:orderPartnerDb.values())
            partner.add(id);
        return partner;
    }

    public List<String> getOrdersByPartnerId(String partnerId){
     List<String>orders=new ArrayList<>();
     for(Map.Entry<String,String>entry:orderPartnerDb.entrySet()){
         if(entry.getValue().equals(partnerId))
             orders.add(entry.getKey());
     }
     return orders;
    }


    public List<String> getAllOrdersId(){
        List<String>orders=new ArrayList<>();
        for (String id:orderDb.keySet())
            orders.add(id);
        return orders;
    }

    public int getCountOfUnassignedOrders(){
      int count=0;
      for (String id:orderDb.keySet()){
          if(!orderPartnerDb.containsKey(id))
              count++;
      }
      return count;
    }

    public String deletePartnerById(String partnerId){
      partnerDb.remove(partnerId);
      for(Map.Entry<String,String>entry:orderPartnerDb.entrySet()){
          if (entry.getValue().equals(partnerId))
              orderPartnerDb.remove(entry.getKey());
      }
      return "Partner removed successfully";
    }

    public String deleteOrderById(String orderId) {
      orderDb.remove(orderId);
      orderPartnerDb.remove(orderId);
      return "Order deleted successfully";
    }
}
