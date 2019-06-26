package com.jaafar.data.common.model;

/**
 * Database base data model
 *
 * @author jaafaree
 * @create 2019/6/16 22:54
 */
public class BaseModel {

    private String transactionId;
    private Long count = -1L;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
