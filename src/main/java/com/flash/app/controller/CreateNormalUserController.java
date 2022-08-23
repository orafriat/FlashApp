package com.flash.app.controller;

import com.flash.app.form.CreateNormalUserForm;
import com.flash.app.enitity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CreateNormalUserController {

    @Autowired
    private com.flash.app.dao.DAOuser DAOuser;

    @RequestMapping(value = "/createNormalUser", method = RequestMethod.GET)
    public ModelAndView createUserPage() {
        ModelAndView result = new ModelAndView("rootuser/registerNormalUser");
        result.addObject("form", new CreateNormalUserForm());

        return result;
    }

    @RequestMapping(value = "/createNormalUser", method = RequestMethod.POST)
    public ModelAndView createUserSubmit(@Valid CreateNormalUserForm form, BindingResult bindingResult) throws Exception {
        ModelAndView result = new ModelAndView("rootuser/registerNormalUser");
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
        User user = new User();

        //User existingUser = userDao.findById(1).get();
        //userDao.delete(user);
        Optional<User> optionalUser = Optional.ofNullable(DAOuser.findByEmail(form.getEmail()));
        User tempUser = null;
        if (optionalUser.isPresent()) {
            result.addObject("errorFields", bindingResult.getFieldErrors());
            result.addObject("errors", "EMAIL ALREADY IN USE");

            return result;
        }

        user.setEmail(form.getEmail());
        user.setPassword(form.getPassword());
        user.setFullName(form.getFullName());
        user.setPhone(form.getPhone());
        user.setAddress(form.getAddress());
        user.setAmount(Double.parseDouble(form.getAmount()));
        user.setType("normal user");
        DAOuser.save(user);
        // goto the next page
        return result;
    }

}
