package com.busyqa.crm.model.util;

public enum EnumList {
    /** USER STATE*/
    EMPLOYEE, CLIENT,

    /** CLIENT STATUS*/
    Course_Interested, Send_Details, For_Payment, To_Student,

    /** PAYMENT STATUS*/
    UNPAID,PAID,PAID_WITH_LATE_FEE,

    /** PAYMENT PLAN STATUS*/
    CONFIRMED,UNCONFIRMED,

    /** PAYMENT PLAN*/
    ONE_TIME_CREDIT_CARD,
    ONE_TIME_DEBIT_CARD_OR_CASH,
    ONE_TIME_EMAIL_MONEY,
    AUTOMATED_WEEKLY,
    AUTOMATED_BIWEEKLY,


}
