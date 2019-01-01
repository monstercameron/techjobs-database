package cheeseMVC.demo.controllers;

import cheeseMVC.demo.models.Cheese;
import cheeseMVC.demo.models.data.CategoryDao;
import cheeseMVC.demo.models.data.CheeseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("cheese")
public class CheeseController {

    @Autowired
    private CheeseDao cheeseDao;

    @Autowired
    private CategoryDao categoryDao;

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "My Cheeses");

        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese());
        model.addAttribute("categories", categoryDao.findAll());
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute @Valid Cheese newCheese,
            Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            model.addAttribute(newCheese);
            model.addAttribute("categories", categoryDao.findAll());
            return "cheese/add";
        }

        cheeseDao.save(newCheese);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "Remove Cheese");
        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {

        for (int cheeseId : cheeseIds) {
            cheeseDao.deleteById(cheeseId);
        }

        return "redirect:";
    }

    @RequestMapping(value = "category/{category}", method = RequestMethod.GET)
    public String filterByCategory(Model model,
            @PathVariable("category") String category) {

        if (categoryDao.findFirstByName(category) == null) {
            return "redirect:/cheese";
        }

        model.addAttribute("cheeses", categoryDao.findFirstByName(category).getCheeses());
        model.addAttribute("title", "Cheeses by Category: " + category);

        return "cheese/index";
    }

    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
    public String editCheese(Model model,
            @PathVariable int cheeseId) {

        Cheese cheese = cheeseDao.getById(cheeseId);
        model.addAttribute(cheese);
        model.addAttribute("title", "Edit Cheese");
        model.addAttribute("categories", categoryDao.findAll());

        return "cheese/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String editCheese(   Model model,
                                @ModelAttribute @Valid Cheese cheese,
                                Errors errors) {

        if(errors.hasErrors()){
            model.addAttribute(cheese);
            model.addAttribute("title", "Edit Cheese");
            model.addAttribute("categories", categoryDao.findAll());
            return "cheese/edit";
        }
        
        System.out.println(cheese.toString());
        
        //Cheese fromDb = cheeseDao.getById();
        //fromDb.setCategory(cheese.getCategory());
        //fromDb.setDescription(cheese.getDescription());
        //fromDb.setName(cheese.getName());
        //System.out.println(fromDb);
        //cheeseDao.save(fromDb);

        return "redirect:";
    }
}
