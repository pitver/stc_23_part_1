package ru.vershinin.lesson24.controller.fasade;

/**
 * OrderServiceFacadeImpl
 *
 * имитация реализации паттерна фасад
 *
 * @author Вершинин Пётр
 */
public class OrderServiceFacadeImpl implements OrderServiceFacade{
    public boolean placeOrder(){
        boolean orderFulfilled=false;

        if(InventoryService.isAvailable())
        {
            System.out.println("Product is available.");
            boolean paymentConfirmed= PaymentService.makePayment();
            if(paymentConfirmed){
                System.out.println("Payment confirmed...");
                ShippingService.shipProduct();
                System.out.println("Product shipped...");
                orderFulfilled=true;
            }
        }
        return orderFulfilled;
    }
}
