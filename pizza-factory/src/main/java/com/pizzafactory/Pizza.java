package com.pizzafactory;

abstract class Pizza {
    protected String name;
    protected String dough;
    protected String sauce;
    protected String[] toppings;
    
    public void prepare() {
        System.out.println("Preparing " + name);
        System.out.println("Tossing " + dough + " dough...");
        System.out.println("Adding " + sauce + " sauce...");
        System.out.println("Adding toppings:");
        for (String topping : toppings) {
            System.out.println("   " + topping);
        }
    }
    
    public void bake() {
        System.out.println("Baking " + name + " for 25 minutes at 350°F");
    }
    
    public void cut() {
        System.out.println("Cutting " + name + " into diagonal slices");
    }
    
    public void box() {
        System.out.println("Placing " + name + " in official PizzaStore box");
    }
    
    public String getName() {
        return name;
    }
}