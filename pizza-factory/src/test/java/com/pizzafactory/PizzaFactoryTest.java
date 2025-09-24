package com.pizzafactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PizzaFactoryTest {
    private PizzaFactory factory;
    private PizzaStore store;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;
    
    @Before
    public void setUp() {
        factory = new PizzaFactory();
        store = new PizzaStore(factory);
        // Capture console output for testing
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }
    
    @After
    public void tearDown() {
        System.setOut(originalOut);
    }
    
    @Test
    public void testCreateCheesePizza() {
        Pizza pizza = factory.createPizza("cheese");
        assertNotNull("Cheese pizza should not be null", pizza);
        assertTrue("Should be instance of CheesePizza", pizza instanceof CheesePizza);
        assertEquals("Connecticut Style Cheese Pizza", pizza.getName());
    }
    
    @Test
    public void testCreateGreekPizza() {
        Pizza pizza = factory.createPizza("greek");
        assertNotNull("Greek pizza should not be null", pizza);
        assertTrue("Should be instance of GreekPizza", pizza instanceof GreekPizza);
        assertEquals("Connecticut Greek Pizza", pizza.getName());
    }
    
    @Test
    public void testCreatePepperoniPizza() {
        Pizza pizza = factory.createPizza("pepperoni");
        assertNotNull("Pepperoni pizza should not be null", pizza);
        assertTrue("Should be instance of PepperoniPizza", pizza instanceof PepperoniPizza);
        assertEquals("Connecticut Pepperoni Pizza", pizza.getName());
    }
    
    @Test
    public void testCreateGlutenFreePizza() {
        Pizza pizza = factory.createPizza("glutenfree");
        assertNotNull("Gluten-free pizza should not be null", pizza);
        assertTrue("Should be instance of GlutenFreePizza", pizza instanceof GlutenFreePizza);
        assertEquals("Gluten-Free Connecticut Pizza", pizza.getName());
    }
    
    @Test
    public void testCreateUnknownPizza() {
        Pizza pizza = factory.createPizza("hawaiian");
        assertNull("Unknown pizza type should return null", pizza);
    }
    
    @Test
    public void testCaseInsensitivePizzaCreation() {
        Pizza pizza1 = factory.createPizza("CHEESE");
        Pizza pizza2 = factory.createPizza("Greek");
        Pizza pizza3 = factory.createPizza("pEpPeRoNi");
        Pizza pizza4 = factory.createPizza("GlutenFree");
        
        assertNotNull("CHEESE should work", pizza1);
        assertNotNull("Greek should work", pizza2);
        assertNotNull("pEpPeRoNi should work", pizza3);
        assertNotNull("GlutenFree should work", pizza4);
    }
    
    @Test
    public void testPizzaStoreDependency() {
        assertNotNull("Pizza store should have factory", store);
        // Test that store can order pizza
        Pizza pizza = store.orderPizza("cheese");
        assertNotNull("Store should be able to order pizza", pizza);
    }
    
    @Test
    public void testPizzaPreparation() {
        Pizza pizza = factory.createPizza("cheese");
        pizza.prepare();
        String output = outputStream.toString();
        assertTrue("Should contain preparation message", output.contains("Preparing"));
        assertTrue("Should contain pizza name", output.contains("Connecticut Style Cheese Pizza"));
    }
    
    @Test
    public void testPizzaBaking() {
        Pizza pizza = factory.createPizza("cheese");
        pizza.bake();
        String output = outputStream.toString();
        assertTrue("Should contain baking message", output.contains("Baking"));
        assertTrue("Should contain temperature", output.contains("350°F"));
    }
    
    @Test
    public void testGlutenFreePizzaSpecialBaking() {
        Pizza pizza = factory.createPizza("glutenfree");
        pizza.bake();
        String output = outputStream.toString();
        assertTrue("Should contain special baking message", output.contains("375°F"));
        assertTrue("Should mention gluten-free", output.contains("gluten-free"));
    }
    
    @Test
    public void testPizzaCutting() {
        Pizza pizza = factory.createPizza("greek");
        pizza.cut();
        String output = outputStream.toString();
        assertTrue("Should contain cutting message", output.contains("Cutting"));
        assertTrue("Should mention diagonal slices", output.contains("diagonal slices"));
    }
    
    @Test
    public void testPizzaBoxing() {
        Pizza pizza = factory.createPizza("pepperoni");
        pizza.box();
        String output = outputStream.toString();
        assertTrue("Should contain boxing message", output.contains("Placing"));
        assertTrue("Should mention PizzaStore box", output.contains("PizzaStore box"));
    }
    
    @Test
    public void testCompleteOrderProcess() {
        Pizza pizza = store.orderPizza("cheese");
        assertNotNull("Ordered pizza should not be null", pizza);
        
        String output = outputStream.toString();
        assertTrue("Should show preparation", output.contains("Preparing"));
        assertTrue("Should show baking", output.contains("Baking"));
        assertTrue("Should show cutting", output.contains("Cutting"));
        assertTrue("Should show boxing", output.contains("Placing"));
        assertTrue("Should show completion", output.contains("Order complete"));
    }
    
    @Test
    public void testLiskovSubstitutionPrinciple() {
        // Test that all pizza types can be treated as Pizza objects
        Pizza[] pizzas = {
            factory.createPizza("cheese"),
            factory.createPizza("greek"),
            factory.createPizza("pepperoni"),
            factory.createPizza("glutenfree")
        };
        
        // All should be Pizza instances and have the same interface
        for (Pizza pizza : pizzas) {
            assertNotNull("Pizza should not be null", pizza);
            assertTrue("Should be Pizza instance", pizza instanceof Pizza);
            
            // All should have these methods available (LSP compliance)
            assertNotNull("Should have name", pizza.getName());
            
            // Should be able to call all methods without knowing specific type
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        }
    }
    
    @Test
    public void testFactoryReturnTypes() {
        // Verify factory returns correct concrete types
        Pizza cheese = factory.createPizza("cheese");
        Pizza greek = factory.createPizza("greek");
        Pizza pepperoni = factory.createPizza("pepperoni");
        Pizza glutenFree = factory.createPizza("glutenfree");
        
        assertEquals("CheesePizza", cheese.getClass().getSimpleName());
        assertEquals("GreekPizza", greek.getClass().getSimpleName());
        assertEquals("PepperoniPizza", pepperoni.getClass().getSimpleName());
        assertEquals("GlutenFreePizza", glutenFree.getClass().getSimpleName());
    }
}