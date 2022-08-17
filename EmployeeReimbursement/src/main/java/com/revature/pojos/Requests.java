package com.revature.pojos;

public class Requests {
    private Integer request_id;
    private Integer user_id;
    private String reason_for_reimbursement;
    private String reason_for_request;
    private double amount_requested;
    private boolean approved_denied;

    public Requests() {
    }

    public Requests(Integer request_id, Integer user_id, String reason_for_reimbursement, String reason_for_request, double amount_requested, boolean approved_denied) {
        this.request_id = request_id;
        this.user_id = user_id;
        this.reason_for_reimbursement = reason_for_reimbursement;
        this.reason_for_request = reason_for_request;
        this.amount_requested = amount_requested;
        this.approved_denied = approved_denied;
    }
    public Requests(Integer user_id, String reason_for_reimbursement, String reason_for_request, double amount_requested, boolean approved_denied) {
        this.request_id = null;
        this.user_id = user_id;
        this.reason_for_reimbursement = reason_for_reimbursement;
        this.reason_for_request = reason_for_request;
        this.amount_requested = amount_requested;
        this.approved_denied = approved_denied;
    }

    public Integer getRequest_id() {
        return request_id;
    }

    public void setRequest_id(Integer request_id) {
        this.request_id = request_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getReason_for_reimbursement() {
        return reason_for_reimbursement;
    }

    public void setReason_for_reimbursement(String reason_for_reimbursement) {
        this.reason_for_reimbursement = reason_for_reimbursement;
    }

    public String getReason_for_request() {
        return reason_for_request;
    }

    public void setReason_for_request(String reason_for_request) {
        this.reason_for_request = reason_for_request;
    }

    public double getAmount_requested() {
        return amount_requested;
    }

    public void setAmount_requested(double amount_requested) {
        this.amount_requested = amount_requested;
    }

    public boolean isApproved_denied() {
        return approved_denied;
    }

    public void setApproved_denied(boolean approved_denied) {
        this.approved_denied = approved_denied;
    }
}
