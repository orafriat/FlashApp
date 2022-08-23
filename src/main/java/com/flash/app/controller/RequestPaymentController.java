package com.flash.app.controller;

import com.flash.app.dao.RequestPaymentDAO;
import com.flash.app.enitity.RequestPayment;
import com.flash.app.enitity.User;
import com.flash.app.form.CreateRequestPayment;
import com.flash.app.dao.DAOuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Integer.parseInt;

@Controller
public class RequestPaymentController {

    @Autowired
    private DAOuser daOuser;

    @Autowired
    private RequestPaymentDAO requestPaymentDAO;

    @RequestMapping(value = "/newRequestPayment", method = RequestMethod.GET)
    public ModelAndView createRequestPaymentPage() {
        ModelAndView result = new ModelAndView("normaluser/requestPayment");
        result.addObject("form", new CreateRequestPayment());

        return result;
    }

    @RequestMapping(value = "/newRequestPayment", method = RequestMethod.POST)
    public ModelAndView createRequestPaymentSubmit(@Valid CreateRequestPayment form, BindingResult bindingResult) throws Exception {
        ModelAndView result = new ModelAndView("normaluser/requestPayment");
        // form validation
        result.addObject("form", form);

        System.out.println(form);

        if (bindingResult.hasErrors()) {

            List<String> errors = new ArrayList<String>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                System.out.println(error.getField() + " = " + error.getDefaultMessage());
                errors.add(error.getDefaultMessage());
            }

            result.addObject("errorFields", bindingResult.getFieldErrors());
            result.addObject("errors", errors);
            return result;
        }

        // business logic
        User user;

        RequestPayment requestPayment = new RequestPayment();
        user = daOuser.findByEmail(getUserEmail());

        User user1 = daOuser.findAllById( parseInt( form.getToUserId()));

        User user2 = null;
               user2 = user1;

//        System.out.println(user1.getId().toString());
//user1.getId() ! =n
        if( form.getToUserId().equals( user.getId().toString())  ){
            result.addObject("Error", "Please not enter your ID");
        }else if( user2 ==null  ){
            result.addObject("Error", "Please enter valid user ID");
        }else if( !form.getToUserId().equals( user1.getId().toString())  ){
            result.addObject("Error", "Please enter valid user ID");
        }else if(  Double.parseDouble(form.getRequestAmount()) > user.getAmount()){
            result.addObject("Error", "Please enter valid Amount or enter amount less then or equal to your balance!");
        }else {

            requestPayment.setCreationDate(form.getCreationDate());
            requestPayment.setFromUserId(user.getId());
            requestPayment.setToUserId( parseInt( form.getToUserId()));
            Random random = new Random();
            requestPayment.setRequestCode(random.nextInt(999999));
            requestPayment.setStatus("New");
            requestPayment.setRequestApproveDate("00-00-0000");
            requestPayment.setRequestAmount(Double.parseDouble(form.getRequestAmount()));

            requestPaymentDAO.save(requestPayment);
            result.addObject("Error", "Payment Request Sent Successfully!");
        }
        return result;
    }

    @RequestMapping(value = "/requestsSent", method = RequestMethod.GET)
    public ModelAndView requestsSent() {
        ModelAndView response = new ModelAndView();
        response.setViewName("normaluser/RequestSent");
        List<RequestPayment> requestPayments = new ArrayList<RequestPayment>();

        User user;
        user = daOuser.findByEmail(getUserEmail());

            requestPayments = requestPaymentDAO.findByFromUserId(user.getId());


        requestPayments.forEach(
                (n)->System.out.println(n.toString())
        );

        response.addObject("requestPayments", requestPayments);
        response.addObject("search", "yes");
        return response;
    }

    @RequestMapping(value = "/requestsReceived", method = RequestMethod.GET)
    public ModelAndView requestsReceived() {
        ModelAndView response = new ModelAndView();
        response.setViewName("normaluser/RequestReceived");
        List<RequestPayment> requestPayments = new ArrayList<RequestPayment>();

        User user;
        user = daOuser.findByEmail(getUserEmail());

        requestPayments = requestPaymentDAO.findByToUserId(user.getId());


        requestPayments.forEach(
                (n)->System.out.println(n.toString())
        );

        response.addObject("requestPayments", requestPayments);
        response.addObject("search", "yes");
        return response;
    }


    @RequestMapping(value = "/requestsearch", method = RequestMethod.GET)
    public ModelAndView search(@RequestParam(required = false) String search) {
        ModelAndView response = new ModelAndView();
        response.setViewName("normaluser/RequestReceived");
        List<RequestPayment> requestPayments = new ArrayList<RequestPayment>();

        requestPayments = requestPaymentDAO.findByRequestCode(parseInt(search));

        requestPayments.forEach(
                (n)->System.out.println(n.toString())
        );

        response.addObject("requestPayments", requestPayments);
        response.addObject("search", "yes");

        return response;
    }

    @RequestMapping(value = "/fullfillrequest", method = RequestMethod.GET)
    public ModelAndView fullfillrequest(@RequestParam(required = false) int id, int userid) {
        ModelAndView response = new ModelAndView();
        response.setViewName("normaluser/RequestReceived");

        User user;
        user = daOuser.findByEmail(getUserEmail());

        RequestPayment requestPayment;
        requestPayment = requestPaymentDAO.findById(id);

        User user2 = daOuser.findAllById(userid);

        if ( requestPayment.getRequestAmount() <= user2.getAmount() && requestPayment.getStatus().equals("New")){

            user.setAmount(user.getAmount()+requestPayment.getRequestAmount());
            daOuser.save(user);

            user2.setAmount(user2.getAmount()-requestPayment.getRequestAmount());
            daOuser.save(user2);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDateTime now = LocalDateTime.now();

            requestPayment.setStatus("Approved");
            requestPayment.setRequestApproveDate(dtf.format(now));
            requestPaymentDAO.save(requestPayment);
            response.addObject("Error", "Request Approved Successfully!");
        }else {
            response.addObject("Error", "Cant Approve Request. Make sure that enter correct amount or already approved!");
        }


        List<RequestPayment> requestPayments = new ArrayList<RequestPayment>();


        requestPayments = requestPaymentDAO.findByToUserId(user.getId());

        requestPayments.forEach(
                (n)->System.out.println(n.toString())
        );

        response.addObject("requestPayments", requestPayments);
        response.addObject("search", "yes");

        return response;
    }

    public String getUserEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            return username;
        } else {
            String username = principal.toString();
            return "";
        }
    }

}
