package com.pizzafactory;

public class PizzaFactoryDemo {
    public static void main(String[] args) {
        // Create factory and store
        PizzaFactory factory = new PizzaFactory();
        PizzaStore store = new PizzaStore(factory);
        
        System.out.println("Welcome to Connecticut's Famous Pizza Store!");
        System.out.println("==========================================");
        
        // Demonstrate ordering different types of pizzas
        System.out.println("\n--- Customer 1 orders Cheese Pizza ---");
        Pizza cheesePizza = store.orderPizza("cheese");
        
        System.out.println("\n--- Customer 2 orders Greek Pizza ---");
        Pizza greekPizza = store.orderPizza("greek");
        
        System.out.println("\n--- Customer 3 orders Pepperoni Pizza ---");
        Pizza pepperoniPizza = store.orderPizza("pepperoni");
        
        System.out.println("\n--- Customer 4 orders Gluten-Free Pizza ---");
        Pizza glutenFreePizza = store.orderPizza("glutenfree");
        
        System.out.println("\n--- Customer 5 tries to order Unknown Pizza ---");
        Pizza unknownPizza = store.orderPizza("hawaiian");
        
        // Demonstrate Liskov Substitution Principle
        System.out.println("\n--- Demonstrating Liskov Substitution Principle ---");
        demonstrateLiskovPrinciple(factory);
    }
    
    // Method to demonstrate Liskov Substitution Principle
    // Any Pizza subclass can be substituted for the base Pizza class
    private static void demonstrateLiskovPrinciple(PizzaFactory factory) {
        System.out.println("Creating different pizza types and treating them uniformly:");
        
        String[] pizzaTypes = {"cheese", "greek", "pepperoni", "glutenfree"};
        
        for (String type : pizzaTypes) {
            Pizza pizza = factory.createPizza(type);
            if (pizza != null) {
                // This works because all subclasses can be substituted for Pizza
                processPizza(pizza);
            }
        }
    }
    
    // This method accepts any Pizza object (demonstrating LSP)
    private static void processPizza(Pizza pizza) {
        System.out.println("Processing: " + pizza.getName());
        // All Pizza subclasses can be used here without knowing their specific type
        pizza.prepare();
        System.out.println("--- End Processing ---\n");
    }
}