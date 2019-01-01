/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cheeseMVC.demo.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author earlcameron
 */
@Entity
public class Menu {
    
    @NotNull
    @Id
    @GeneratedValue
    private int id;
    
    @NotNull
    private String name;
    
    @ManyToMany
    private List<Cheese> cheese;

    public Menu(String name) {
        this.name = name;
    }

    public Menu() { 
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Cheese> getCheese() {
        return cheese;
    }
    
    public void addItem(Cheese item){
        cheese.add(item);
    }

    @Override
    public String toString() {
        return "Menu{" + "id=" + id + ", name=" + name + ", cheese=" + cheese + '}';
    }
    
}
