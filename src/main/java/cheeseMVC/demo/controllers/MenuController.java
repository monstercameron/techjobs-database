/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cheeseMVC.demo.controllers;

import cheeseMVC.demo.models.Menu;
import cheeseMVC.demo.models.addMenuItemform;
import cheeseMVC.demo.models.data.CheeseDao;
import cheeseMVC.demo.models.data.MenuDao;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author monstercameron
 */

@Controller
@RequestMapping(value = "menu")
public class MenuController {
    
    @Autowired
    MenuDao menuDao;
    
    @Autowired
    CheeseDao cheeseDao;
    
    @RequestMapping(value = "")
    public String index(Model model){
        model.addAttribute("menus", menuDao.findAll());
        return "menu/index";
    }
    
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addMenu(Model model){
        model.addAttribute("menu", new Menu());
        return "menu/add";
    }
    
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addMenu(  Model model,
                            @ModelAttribute @Valid Menu menu,
                            Errors errors){
        
        if(errors.hasErrors()){
            model.addAttribute("menu", menu);
            return "menu/add";
        }else{
            menuDao.save(menu);
        }
        
        return "redirect:view/" + menu.getId();
    }
    
    @RequestMapping(value = "view/{menuId}")
    public String viewMenu(Model model,
                            @PathVariable int menuId){
        
        if(menuId != 0){
            model.addAttribute("menu", menuDao.getById(menuId));
        }else{
            return "redirect:";
        }
        
        return "menu/view";
    }
    
    @RequestMapping(value = "add-item/{menuId}", method = RequestMethod.GET)
    public String addItem(  Model model,
                            @PathVariable int menuId){
        
        System.out.println(menuDao.getById(menuId).getName());
        
        model.addAttribute("form", new addMenuItemform(menuDao.getById(menuId), cheeseDao.findAll()));
        model.addAttribute("title", "Add item to menu: " + menuDao.getById(menuId).getName());
        return "menu/additem";
    }
    
    @RequestMapping(value = "add-item", method = RequestMethod.POST)
    public String addItem(  Model model,
                            @ModelAttribute @Valid addMenuItemform form,
                            Errors errors){
        
        if(errors.hasErrors()){
            model.addAttribute("form", form);
            return "menu/additem";
        }
        
        Menu menu = menuDao.getById(form.getMenuId());
        menu.addItem(cheeseDao.getById(form.getCheeseId()));
        menuDao.save(menu);
        
        return "redirect:view/" + menu.getId();
    }
    
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", menuDao.findAll());
        model.addAttribute("title", "Remove Category");
        return "cheese/remove";
    }
    
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {

        for (int cheeseId : cheeseIds) {
            menuDao.deleteById(cheeseId);
        }

        return "redirect:";
    }
}
