package com.pizzafactory;

class GlutenFreePizza extends Pizza {
    public GlutenFreePizza() {
        name = "Gluten-Free Connecticut Pizza";
        dough = "gluten-free cauliflower";
        sauce = "light marinara";
        toppings = new String[]{"dairy-free cheese", "fresh basil"};
    }
    
    @Override
    public void bake() {
        System.out.println("Baking " + name + " for 20 minutes at 375°F (special gluten-free temperature)");
    }
}