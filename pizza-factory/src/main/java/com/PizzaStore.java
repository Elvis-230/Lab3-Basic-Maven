package com.pizzafactory;

class PizzaStore {
    private PizzaFactory factory;
    
    public PizzaStore(PizzaFactory factory) {
        this.factory = factory;
    }
    
    public Pizza orderPizza(String type) {
        Pizza pizza = factory.createPizza(type);
        
        if (pizza != null) {
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
            System.out.println("Order complete: " + pizza.getName());
            System.out.println("================================");
        } else {
            System.out.println("Sorry, we don't make that type of pizza.");
        }
        
        return pizza;
    }
}