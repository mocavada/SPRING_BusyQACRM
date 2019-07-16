package com.busyqa.crm.controller;

import com.busyqa.crm.model.clients.DTOClient;
import com.busyqa.crm.model.finance.*;
import com.busyqa.crm.service.FinanceService;
import com.busyqa.crm.service.MailService;
import com.busyqa.crm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(path = "/audit")
public class AuditRestController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private FinanceService financeService;

    @Autowired
    private MailService mailService;

    // STUDENT SERVICE
    ///////////////////
    @GetMapping("/usersList/{type}")
    public List<DTOClient> getLeadListByDType(@PathVariable("type") String type) {
        return this.studentService.getAllByDtype(type);
    }

    @GetMapping("/student/{email}")
    public DTOClient getStudentByEmail(@PathVariable("email") String email) {
        return this.studentService.getStudentByEmail(email);
    }

    @PutMapping("/updateStudent/{email}")
    public ResponseEntity<DTOClient> updateStudentByEmail(@PathVariable("email") String email, @RequestBody DTOClient leadRequest) {
        return studentService.updateStudent(email,leadRequest);
    }


    // FINANCE --- DISCOUNT
    ///////////////////
    @PostMapping("/addDiscount")
    public ResponseEntity<Void> addDiscount(@RequestBody Discount discount, UriComponentsBuilder builder) {

        boolean flag = financeService.addDiscount(discount);

        if (!flag) return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/addDiscount").buildAndExpand(discount.getDiscountId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


    @GetMapping("/discountList")
    public ResponseEntity<List<Discount>> getAllDiscount() {
        List<Discount> list = financeService.getAllDiscount();
        return new ResponseEntity<List<Discount>>(list, HttpStatus.OK);
    }

    // FINANCE --- LATE FEE
    ///////////////////
    @PostMapping("/addLateFee")
    public ResponseEntity<Void> addLateFee(@RequestBody LateFee lateFee, UriComponentsBuilder builder) {

        boolean flag = financeService.addLateFee(lateFee);

        if (!flag) return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/addLateFee").buildAndExpand(lateFee.getLateFeeId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @GetMapping("/lateFeeList")
    public ResponseEntity<List<LateFee>> getAllLateFee() {
        List<LateFee> list = financeService.getAllLateFee();
        return new ResponseEntity<List<LateFee>>(list, HttpStatus.OK);
    }

    // FINANCE --- PAYMENT
    ///////////////////
    @GetMapping("/paymentList")
    public ResponseEntity<List<Payment>> getAllPayment() {
        List<Payment> list = financeService.getAllPayment();
        return new ResponseEntity<List<Payment>>(list, HttpStatus.OK);
    }


    @GetMapping("/studentPayments/{id}")
    public ResponseEntity<List<Payment>> getAllStudentPayments(@PathVariable("id") long id) {
        List<Payment> list = financeService.getAllPaymentsByStudent(id);
        return new ResponseEntity<List<Payment>>(list, HttpStatus.OK);
    }

    // FINANCE --- PAYMENT PLAN
    ///////////////////
    @PostMapping("/addPaymentPlan")
    public ResponseEntity<Void> addPaymentPlan(@RequestBody PaymentPlan paymentPlan, UriComponentsBuilder builder) {

        boolean flag = financeService.addPaymentPlan(paymentPlan);

        if (!flag) return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/addPaymentPlan").buildAndExpand(paymentPlan.getPaymentPlanId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


    @GetMapping("/paymentPlanList")
    public ResponseEntity<List<PaymentPlan>> getAllPaymenyPlan() {
        List<PaymentPlan> list = financeService.getAllPaymentPlan();
        return new ResponseEntity<List<PaymentPlan>>(list, HttpStatus.OK);
    }

    // FINANCE --- REGISTRATION FEE
    ///////////////////
    @PostMapping("/addRegistrationFee")
    public ResponseEntity<Void> addRegistrationFee(@RequestBody RegistrationFee registrationFee, UriComponentsBuilder builder) {

        boolean flag = financeService.addRegistrationFee(registrationFee);

        if (!flag) return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/addRegistrationFee").buildAndExpand(registrationFee.getRegistrationFeeId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


    @GetMapping("/registrationFeeList")
    public ResponseEntity<List<RegistrationFee>> getAllRegistrationFee() {
        List<RegistrationFee> list = financeService.getAllRegistrationFee();
        return new ResponseEntity<List<RegistrationFee>>(list, HttpStatus.OK);
    }


    // FINANCE --- TAX
    ///////////////////
    @PostMapping("/addTax")
    public ResponseEntity<Void> addTax(@RequestBody Tax tax, UriComponentsBuilder builder) {

        boolean flag = financeService.addTax(tax);

        if (!flag) return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/addTax").buildAndExpand(tax.getTaxId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


    @GetMapping("/taxList")
    public ResponseEntity<List<Tax>> getAllTax() {
        List<Tax> list = financeService.getAllTax();
        return new ResponseEntity<List<Tax>>(list, HttpStatus.OK);
    }











}
