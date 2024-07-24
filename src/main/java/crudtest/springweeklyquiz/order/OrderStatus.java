package crudtest.springweeklyquiz.order;

public enum OrderStatus {
    RECEIVED("접수"),
    COMPLETED("완료"),
    CANCELED("취소");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }
}
