package cheeseMVC.demo.models;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;

/**
 *
 * @author monstercameron
 */
public class addMenuItemform {
    
    @NotNull
    private int menuId;
    
    @NotNull
    private int cheeseId;
    
    private Menu menu;
    private Iterable<Cheese> cheese = new ArrayList<>();

    public addMenuItemform() {
    }

    public addMenuItemform(Menu menu, Iterable<Cheese> cheese) {
        this.menu = menu;
        this.cheese = cheese;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getCheeseId() {
        return cheeseId;
    }

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Iterable<Cheese> getCheese() {
        return cheese;
    }

    public void addCheese(Cheese cheese) {
    }

    @Override
    public String toString() {
        return "addMenuItemform{" + "menuId=" + menuId + ", cheeseId=" + cheeseId + ", menu=" + menu + ", cheese=" + cheese + '}';
    }
}
