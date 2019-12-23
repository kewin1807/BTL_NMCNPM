package javaCode.yourcart.beans;

public  class Order {
    private int user_id;
    private int order_id;
    private String date;
    private int status_id;
    private String address;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }




    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}


