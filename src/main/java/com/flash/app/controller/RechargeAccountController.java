package com.flash.app.controller;

import com.flash.app.dao.DAOuser;
import com.flash.app.dao.RequestPaymentDAO;
import com.flash.app.enitity.RequestPayment;
import com.flash.app.enitity.User;
import com.flash.app.form.CreateRequestPayment;
import com.flash.app.form.RechargeAccountForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Integer.parseInt;

@Controller
public class RechargeAccountController {

    @Autowired
    private DAOuser daOuser;

    @Autowired
    private RequestPaymentDAO requestPaymentDAO;

    @RequestMapping(value = "/rechargeAccount", method = RequestMethod.GET)
    public ModelAndView rechargeAccountPage() {

        ModelAndView result ;

        User user;
        user = daOuser.findByEmail(getUserEmail());

        if(user.getType().equals("root")){
             result = new ModelAndView("rootuser/rechargeAccount");
        }else {
             result = new ModelAndView("login/login");
        }
        result.addObject("form", new RechargeAccountForm());
        return result;
    }

    @RequestMapping(value = "/rechargeAccount", method = RequestMethod.POST)
    public ModelAndView createRequestPaymentSubmit(@Valid RechargeAccountForm form, BindingResult bindingResult) throws Exception {
        ModelAndView result = new ModelAndView("rootuser/rechargeAccount");
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
        user = daOuser.findByEmail(getUserEmail());
        User user1 = daOuser.findAllById( parseInt( form.getId()));

        User user2 = null;
        user2 = user1;

//        System.out.println(user1.getId().toString());
//user1.getId() ! =n
        if( form.getId().equals( user.getId().toString())  ){
            result.addObject("Error", "Please not enter your ID");
        }else if( user2 ==null  ){
            result.addObject("Error", "Please enter valid user ID");
        }else if( !form.getId().equals( user1.getId().toString())  ){
            result.addObject("Error", "Please enter valid user ID");
        }else if(  Double.parseDouble(form.getAmount()) > 100000){
            result.addObject("Error", "Please enter less then 100,000 !");
        }else {

            user1.setAmount(user1.getAmount()+ Double.parseDouble(form.getAmount()));
            daOuser.save(user1);
            result.addObject("Error", "Account Recharged Successfully!");
        }
        return result;
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
