package com.prasys.request.controller;


import com.prasys.auth.service.MyUserDetailsService;
import com.prasys.framework.dto.PlaceOrder;
import com.prasys.framework.entity.Item;
import com.prasys.framework.entity.PurchaseOrder;
import com.prasys.framework.entity.PurchaseRequisition;
import com.prasys.framework.entity.RequestWorkFlow;
import com.prasys.framework.repo.RequestRepo;
import com.prasys.request.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@RestController
@RequestMapping("v1")
public class RequestController {

    @Autowired
    private RequestRepo requestRepo;

    @Autowired
    private RequestService requestService;


    @PostMapping("/create")
    public ResponseEntity create(@RequestBody PurchaseRequisition pr) {
        pr.setClientId(MyUserDetailsService.user.getClientId());
        pr.setCreatedBy(MyUserDetailsService.user.getId().toString());
        requestService.createWorkFlow(requestRepo.save(pr),MyUserDetailsService.user);
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @GetMapping("/updateStatus/{id}")
    public ResponseEntity updateStatus(@PathVariable("id") Long id) {
        requestService.updateStatus(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/placeOrder")
    public ResponseEntity placeOrder(@RequestBody PlaceOrder order) throws MessagingException {
        requestService.order(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/items/{id}")
    public ResponseEntity<List<Item>> getItems(@PathVariable("id") Long id) {
        System.out.println(requestRepo.getOne(id).getItems());
        return new ResponseEntity<>(requestRepo.getOne(id).getItems(), HttpStatus.OK);
    }
}
