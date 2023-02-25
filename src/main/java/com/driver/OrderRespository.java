package com.driver;

import java.util.*;

public class OrderRespository {
    HashMap<String,Order> orderdb=new HashMap<>();
    HashMap<String,DeliveryPartner> deliveryPartnerdb=new HashMap<>();
    HashMap<String,List<String>> orderToPartnerdb=new HashMap<>();
    HashMap<String,String> orderAssigneddb=new HashMap<>();

    public String addOrder(Order order){
        orderdb.put(order.getId(),order);
        return "added successfully";
    }
    public String addDeliveryPartner(String deliveryPartnerId){
        DeliveryPartner deliveryPartner=new DeliveryPartner(deliveryPartnerId);
        deliveryPartnerdb.put(deliveryPartnerId,deliveryPartner);
        return "added successfully";
    }
    public String addOrderPartnerPair(String orderId,String  deliveryPartnerId){
        List<String> list=orderToPartnerdb.getOrDefault(deliveryPartnerId,new ArrayList<>());
        list.add(orderId);
        orderToPartnerdb.put(deliveryPartnerId,list);
        orderAssigneddb.put(orderId,deliveryPartnerId);
        DeliveryPartner deliveryPartner=deliveryPartnerdb.get(deliveryPartnerId);
        deliveryPartner.setNumberOfOrders(list.size());
        return "added sucessfully";
    }
    public Order getOrderById(String orderId){
        for(String s:orderdb.keySet()){
            if(s.equals(orderId)){
                return orderdb.get(s);
            }
        }
        return null;
    }
    public DeliveryPartner getPartnerId(String deliveryPartnerId){
        if(deliveryPartnerdb.containsKey(deliveryPartnerId)){
            return deliveryPartnerdb.get(deliveryPartnerId);
        }
        return null;
    }
    public int getOrderCountByPartnerId(String deliveryPartnerId){
   int orders=orderToPartnerdb.getOrDefault(deliveryPartnerId,new ArrayList<>()).size();
        return orders;

    }
    public List<String> getOrderByPartnerId(String deliveryPartnerId){
        List<String> orders=orderToPartnerdb.getOrDefault(deliveryPartnerId,new ArrayList<>());
        return orders;

    }
    public List<String> getAllOrders(){
        List<String> orders=new ArrayList<>();
        for(String s:orderdb.keySet()){
            orders.add(s);
        }
        return orders;
    }
    public int getCountOfUnassignedOrders(){
        int countOfOrders=orderdb.size()-orderAssigneddb.size();
        return countOfOrders;
    }
    public int getOrdersLeftAfterGivenTimeByPartnerId(String time,String deliveryPartnerId){
        int countOfOrders=0;
        List<String> list=orderToPartnerdb.get(deliveryPartnerId);
        int deliveryTime=Integer.parseInt(time.substring(0,2))*60+Integer.parseInt(time.substring(3));
        for(String s:list){
            Order order=orderdb.get(s);
            if(order.getDeliveryTime()>deliveryTime){
                countOfOrders++;
            }
        }
        return countOfOrders;
    }
    public String getLastDeliveryTimeByPartnerId(String deliveryPartnerId){
        String time="";
        List<String> list=orderToPartnerdb.get(deliveryPartnerId);
        int deliveryTime=0;
        for(String s:list){
            Order order=orderdb.get(s);
            deliveryTime=Math.max(deliveryTime,order.getDeliveryTime());

        }
        int hour=deliveryTime/60;
        String sHour="";
        if(hour<10){
            sHour="0"+String.valueOf(hour);
        }
        else{
            sHour=String.valueOf(hour);
        }
        int min=deliveryTime%60;
        String sMin="";
        if(min<60){
            sMin="0"+String.valueOf(min);
        }
        else{
            sMin=String.valueOf(min);
        }
        time=sHour+":"+sMin;
        return time;
    }
    public String deletePartnerById(String deliveryPartnerId){
        deliveryPartnerdb.remove(deliveryPartnerId);
        List<String> list=orderToPartnerdb.getOrDefault(deliveryPartnerId,new ArrayList<>());
        ListIterator<String> itr=list.listIterator();
        while(itr.hasNext()){
            String s=itr.next();
            orderAssigneddb.remove(s);
        }
        orderToPartnerdb.remove(deliveryPartnerId);
        return "Deleted Successfully";
    }
    public String deleteOrderById(String orderId){
        orderdb.remove(orderId);
        String partnerId=orderAssigneddb.get(orderId);
        orderAssigneddb.remove(orderId);
        List<String> list=orderToPartnerdb.get(partnerId);
        ListIterator<String> itr=list.listIterator();
        while(itr.hasNext()){
            String s=itr.next();
            if(s.equals(orderId)){
                itr.remove();
            }
        }
        orderToPartnerdb.put(partnerId,list);
        return "Deleted Successfully";
    }


}
