package cheeseMVC.demo.controllers;

import cheeseMVC.demo.models.Category;
import cheeseMVC.demo.models.data.CategoryDao;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author earlcameron
 */
@RequestMapping(value = "category")
@Controller
public class CategoryController {
    
    
    @Autowired
    private CategoryDao categoryDao;
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getCategories(   Model model){
        
        model.addAttribute("categories", categoryDao.findAll());
        return "category/index";
    }
    
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addCategories(    Model model){
        
        model.addAttribute("categories", new Category());
        return "category/add";
    }  
    
    
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addCategories(    @ModelAttribute @Valid Category cat,
                                    Errors errors,
                                    Model model){
        
        if(errors.hasErrors()){
            model.addAttribute("categories", cat);
            return "category/add";
        }
        
        categoryDao.save(cat);
        return "redirect:";
    }
    
    
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", categoryDao.findAll());
        model.addAttribute("title", "Remove Category");
        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {

        for (int cheeseId : cheeseIds) {
            categoryDao.deleteById(cheeseId);
        }

        return "redirect:";
    }
}
