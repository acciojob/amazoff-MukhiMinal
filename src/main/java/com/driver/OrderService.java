package com.driver;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    OrderRespository orderRespository=new OrderRespository();
    public String addOrder(Order orderId){
        String result=orderRespository.addOrder(orderId);
        return result;
    }
    public String addDeliveryPartner(String deliveryPartnerId){
        String result=orderRespository.addDeliveryPartner(deliveryPartnerId);
        return result;
    }
    public String addOrderPartnerPair(String orderId,String deliveryPartnerId){
        String result= orderRespository.addOrderPartnerPair(orderId,deliveryPartnerId);
        return result;
    }
    public Order getOrderById(String orderId){
        Order result=orderRespository.getOrderById(orderId);
        return result;
    }
    public DeliveryPartner getPartnerById(String deliveryPartnerId){
        DeliveryPartner result=orderRespository.getPartnerId(deliveryPartnerId);
        return result;
    }
    public int getOrderCountByPartnerId(String deliveryPartnerId){
        int result=orderRespository.getOrderCountByPartnerId(deliveryPartnerId);
        return result;
    }
    public List<String> getOrdersByPartnerId (String deliveryPartnerId){
        List<String> result=orderRespository.getOrderByPartnerId(deliveryPartnerId);
        return result;
    }
    public List<String> getAllOrders(){
        List<String> result=orderRespository.getAllOrders();
        return result;
    }
    public int getCountOfUnassignedOrders(){
        int countOfOrders=orderRespository.getCountOfUnassignedOrders();
        return countOfOrders;
    }
    public int getOrdersLeftAfterGivenTimeByPartnerId(String time,String deliveryPartnerId){
        int countOfOrders=orderRespository.getOrdersLeftAfterGivenTimeByPartnerId(time,deliveryPartnerId);
        return countOfOrders;
    }
    public String getLastDeliveryTimeByPartnerId(String deliveryPartnerId){
        String time=orderRespository.getLastDeliveryTimeByPartnerId(deliveryPartnerId);
        return time;
    }
    public String deletePartnerById(String deliveryPartnerId){
        String result=orderRespository.deletePartnerById(deliveryPartnerId);
        return result;
    }
    public String deleteOrderById(String orderId){
        String result=orderRespository.deleteOrderById(orderId);
        return result;
    }

}
