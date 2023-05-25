package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public OrderService(){

    }
    public String addOrder(Order order){
        String ans=orderRepository.addOrder(order);
        return ans;
    }

    public String addPartner(String partnerId){
        String ans=orderRepository.addPartner(partnerId);
        return ans;
    }

    public String addOrderPartnerPair(String orderId, String partnerId){
        return orderRepository.addOrderPartnerPair(orderId,partnerId);
    }

    public Order getOrderById(String orderId){
        List<Order>orders=orderRepository.getAllOrders();
        for (Order order:orders){
            if (order.getId().equals(orderId))
                return order;
        }
        return null;
    }

    public DeliveryPartner getPartnerById(String partnerId){
      List<DeliveryPartner>deliveryPartners=orderRepository.getAllPartners();
      for (DeliveryPartner deliveryPartner:deliveryPartners){
          if (deliveryPartner.getId().equals(partnerId))
              return deliveryPartner;
      }
      return null;
    }

    public int getOrderCountByPartnerId(String partnerId) {
        int count=0;
        List<String>partner=orderRepository.getAllOrdersPartner();
        for (String id:partner)
            if (id.equals(partnerId))
                count++;
        return count;
    }


    public List<String> getOrdersByPartnerId(String partnerId){
        List<String>orders=orderRepository.getOrdersByPartnerId(partnerId);
        return orders;
    }

    public List<String> getAllOrders(){
        List<String>orders=orderRepository.getAllOrdersId();
        return orders;
    }

    public int getCountOfUnassignedOrders(){
        return orderRepository.getCountOfUnassignedOrders();
    }

    public int getOrdersLeftAfterGivenTimeByPartnerId(String time,String partnerId){
      int hours=Integer.parseInt(time.substring(0,2));
      int minutes=Integer.parseInt(time.substring(3));
      int time1=hours*60+minutes;
      List<String>orders=orderRepository.getOrdersByPartnerId(partnerId);
      List<Order>orders1=orderRepository.getAllOrders();
        HashSet<String>hs=new HashSet<>();
        for(String id:orders)hs.add(id);
      int count=0;
      for (Order order:orders1){
          if (hs.contains(order.getId())&&order.getDeliveryTime()>time1)
              count++;
      }
      return count;
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId){
        List<String>orders=orderRepository.getOrdersByPartnerId(partnerId);
        List<Order>orders1=orderRepository.getAllOrders();
        int time=0;
        HashSet<String>hs=new HashSet<>();
        for(String id:orders)hs.add(id);
        for (Order order:orders1){
            if(order.getDeliveryTime()>time)
                time=order.getDeliveryTime();
        }
        String ans="";
        char h=(char)(time/60);
        char m=(char)(time%60);
        if(h>'9' && m>'9')
            ans+=h+':'+m;
        else if(h>'9' && m<'9')
            ans+=h+":0"+m;
        else
            ans+='0'+h+':'+m;
        return ans;
    }

    public String deletePartnerById(String partnerId){
        return orderRepository.deletePartnerById(partnerId);
    }

    public String deleteOrderById(String orderId) {
        return orderRepository.deleteOrderById(orderId);
    }
}
