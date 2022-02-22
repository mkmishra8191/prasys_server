package com.prasys.request.service;

import com.prasys.auth.service.MyUserDetailsService;
import com.prasys.framework.dto.PlaceOrder;
import com.prasys.framework.entity.*;
import com.prasys.framework.repo.CompanyRepo;
import com.prasys.framework.repo.OrderRepo;
import com.prasys.framework.repo.RequestFlowRepo;
import com.prasys.framework.repo.RequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RequestService {
    @Autowired
    private RequestFlowRepo requestFlowRepo;

    @Autowired
    private RequestRepo requestRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private CompanyRepo companyRepo;
    @Autowired
    private OrderService orderService;

    public boolean createWorkFlow(PurchaseRequisition pr, User user) {
        User temp = user;

        int level = 1;
        if (pr.getRTypeId()==1 && temp!= null) {
            while (!User.getRoless(temp.getRoles()).contains("CEO")) {
                User reportingTo = temp.getReportingTo();
                temp = reportingTo;

                if (User.getRoless(reportingTo.getRoles()).contains("Manager") || User.getRoless(reportingTo.getRoles()).contains("CEO")) {
                    RequestWorkFlow wf = new RequestWorkFlow();
                    wf.setApproverId(reportingTo.getId());
                    wf.setRequestId(pr.getId());
                    wf.setClientId(user.clientId);
                    if(level==1)
                        wf.setStatus("Initiated");
                    if(level!=1)
                        wf.setStatus("Queue");

                    wf.setLevel(level);
                    requestFlowRepo.save(wf);
                    level++;
                }
            }
        }
        return true;
    }

    public boolean updateStatus( Long id){
        RequestWorkFlow wf = requestFlowRepo.getOne(id);

        wf.setStatus("Approved");
        requestFlowRepo.save(wf);

        List<RequestWorkFlow> list = requestFlowRepo.findAllWorkFlow(wf.getRequestId(),"Queue");
           if(list.size()!=0) {
               RequestWorkFlow temp = list.get(0);
               temp.setStatus("Initiated");
               requestFlowRepo.save(temp);
           }
           return true;
    }

    public boolean order(PlaceOrder order) throws MessagingException {
        Integer vendorId = order.getVendorId();
        String email = companyRepo.getOne(Long.valueOf(vendorId)).getEmail();
        Integer requestId = order.getPrId();
        PurchaseRequisition pr = requestRepo.getOne(Long.valueOf(requestId));
        PurchaseOrder po = new PurchaseOrder();
        po.setClientId(MyUserDetailsService.user.getClientId());
        po.setCreatedBy(MyUserDetailsService.user.getId());
        po.setItems(pr.getItems());
        po.setVendorId(vendorId.toString());
        orderRepo.save(po);


        orderService.sendAttach(pr.getItems(),email);
        return true;
    }
}






