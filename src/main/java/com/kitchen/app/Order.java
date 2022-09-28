package com.kitchen.app;

import java.util.List;

public class Order {
    private int order_id, table_id, waiter_id;
    private List<Integer> items;
    private int priority;
    private long max_wait;
    private long pick_up_time;

    public Order(){ }

    public int getOrderId(){
        return order_id;

    }
    public int getTableId(){
        return table_id;
    }

    public int getWaiterId(){
        return waiter_id;
    }

    public List<Integer> getItems(){
        return items;
    }

    public int getPriority(){
        return priority;
    }
    public long getMaxWait(){
        return max_wait;
    }
    public long getPickUpTime(){
        return pick_up_time;
    }

    public void setOrderId( int order_id){
        this.order_id = order_id;
    }

    public void setTableId( int table_id){
        this.table_id = table_id;
    }

    public void setWaiterId( int waiter_id){
        this.waiter_id = waiter_id;
    }

    public void setItems( List<Integer> items){
        this.items = items;
    }

    public void setPriority( int priority){
        this.priority = priority;
    }

    @Override
    public String toString() {
        return ("id: " + this.getOrderId()+
                "\n table: "+ this.getTableId() +
                "\n waiter: "+ this.getWaiterId() +
                "\n items : " + this.getItems()) +
                "\n priority: " + this.getPriority() +
                "\n max_wait: " + this.getMaxWait();
    }
    
}