/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sendmail;

import java.util.List;

/**
 *
 * @author dangminhtien
 */
public class CheckoutEmail implements Email {

    public static class OrderConfirm {

        public String productTitle;
        public double price;
        public int quantity;

        public OrderConfirm(String productTitle, double price, int quantity) {
            this.productTitle = productTitle;
            this.price = price;
            this.quantity = quantity;
        }
        
    }

    private List<OrderConfirm> orders;
    private double total;
    private String receiverEmail;

    public CheckoutEmail(List<OrderConfirm> orders, double total, String receiver) {
        this.orders = orders;
        this.total = total;
        this.receiverEmail = receiver;
    }

    @Override
    public String getSubject() {
        return "Confirm order";
    }

    @Override
    public String getReceiverEmail() {
        return receiverEmail;
    }

    @Override
    public String getMessage() {
        return "";
    }

    @Override
    public String getHtml() {
        String html = "<h1>Prj confirm your order</h1> <br/>";
        double total = 0d;
        for (int i = 0; i < orders.size(); i++) {
            OrderConfirm order = orders.get(i);
            double singleOrderPrice = order.price * order.quantity;
            total += singleOrderPrice;
            html += "<h2>" + order.productTitle + ": " + order.quantity + " x " + order.price + " = " + singleOrderPrice + "</h2> </br>";
        }
        html += "</br>" + "<h1> total: " + Math.round(total) + "</h1>";
        return html;
    }

}
