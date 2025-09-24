package com.pizzafactory;

class PizzaFactory {
    public Pizza createPizza(String type) {
        Pizza pizza = null;
        
        switch (type.toLowerCase()) {
            case "cheese":
                pizza = new CheesePizza();
                break;
            case "greek":
                pizza = new GreekPizza();
                break;
            case "pepperoni":
                pizza = new PepperoniPizza();
                break;
            case "glutenfree":
                pizza = new GlutenFreePizza();
                break;
            default:
                System.out.println("Unknown pizza type: " + type);
                return null;
        }
        
        return pizza;
    }
}