 
package com.alomsoft.capp.controller;

import com.alomsoft.capp.domain.Contact;
import com.alomsoft.capp.service.ContactService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Mohammed
 */
@Controller

public class ContactController {
    @Autowired
    private ContactService contactService;
    
    /**
     * This method for contact form
     * @param m
     * @return 
     */
    @RequestMapping(value = "/user/contact_form")
    public String contactForm(Model m){
        Contact contact = new Contact();
        m.addAttribute("command", contact);
        return "contact_form";//JSP form view
    }
    
    /**
     * this method for edit contact
     * @param m
     * @param session
     * @param contactId
     * @return 
     */
    
     @RequestMapping(value = "/user/edit_contact")
    public String preparedEidtForm(Model m, HttpSession session, @RequestParam("cid") Integer contactId){
        session.setAttribute("aContactId", contactId);
        Contact c = contactService.findById(contactId);
        m.addAttribute("command", c);
        return "contact_form";//JSP form view
    }
    
    /**
     * This method is for save or update contact operation both task will handle
     * @param c
     * @param m
     * @param session
     * @return 
     */
    
    @RequestMapping(value = "/user/save_contact")
    public String saveOrUpdateContact(@ModelAttribute("command") Contact c, Model m, HttpSession session){
        Integer contactId = (Integer) session.getAttribute("aContactId");
        if(contactId == null){
            //save task
           try {
                Integer userId = (Integer) session.getAttribute("userId");
                c.setUserId(userId);//FK ;this is logged in user id
                contactService.save(c);
                return "redirect:clist?act=sv";//redirect user to contact list url
        } catch (Exception e) {
                e.printStackTrace();
                m.addAttribute("err","Failed to save contact");
                return "contact_form";
        } 
        } else{
            //update task
            try {
                c.setContactId(contactId);//PK 
                contactService.update(c);
                session.removeAttribute("aContactId");
                return "redirect:clist?act=ed";//redirect user to contact list url
        } catch (Exception e) {
                e.printStackTrace();
                m.addAttribute("err","Failed to Edit contact");
                return "contact_form";
        } 
            
        }
    }
    
    /**
     * This method is for save operation
     * @param c
     * @param m
     * @param session
     * @return 
     */
    /*
    @RequestMapping(value = "/user/save_contact")
    public String saveContact(@ModelAttribute("command") Contact c, Model m, HttpSession session){
        
        try {
            Integer userId = (Integer) session.getAttribute("userId");
            c.setUserId(userId);//FK ;this is logged in user id
            contactService.save(c);
            return "redirect:clist?act=sv";//redirect user to contact list url
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("err","Failed to save contact");
            return "contact_form";
        }
    }*/
    
    /**
     * This method for user contact list display
     * @param m
     * @param session
     * @return 
     */
    
    @RequestMapping(value = "/user/clist") 
    public String contactList(Model m, HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        m.addAttribute("contactList", contactService.findUserContact(userId));
        return "clist";//JSP page
    }
    
    /**
     * This method is for contact search operation
     * @param m
     * @param session
     * @param freeText
     * @return 
     */
    @RequestMapping(value = "/user/contact_search") 
    public String contactSearch(Model m, HttpSession session, @RequestParam("freeText")String freeText){
        Integer userId = (Integer) session.getAttribute("userId");
        m.addAttribute("contactList", contactService.findUserContact(userId, freeText));
        return "clist";//JSP page
    }
    /**
     * This method for delete user contact list
     * @param contactId
     * @return 
     */
    
    @RequestMapping(value = "/user/del_contact")
    public String deleteContact(@RequestParam("cid") Integer contactId){
        contactService.delete(contactId);
        return "redirect:clist?act=del";
    }
    
    /**
     * This method is for bulk contact
     * @param contactIds
     * @return 
     */
     @RequestMapping(value = "/user/bulk_cdelete")
    public String deleteBulkContact(@RequestParam("cid") Integer[] contactIds){
        contactService.delete(contactIds);
        return "redirect:clist?act=del";
    }
    
}
