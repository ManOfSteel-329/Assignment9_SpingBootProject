package com.coderscampus.assignment9;

import jakarta.annotation.PostConstruct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

@Service
public class RecipeService {
    private static final Logger LOGGER = Logger.getLogger(RecipeService.class.getName());
    private List<Recipe> recipes = new ArrayList<>();

    @PostConstruct
    public void loadRecipes() {
        // example of how to parse a CSV file
        try {
            // example of how to parse a CSV file
            Reader in = new FileReader("recipes.txt");
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withIgnoreSurroundingSpaces()
                    .withHeader()
                    .withSkipHeaderRecord()
                    .withEscape('\\')
                    .parse(in);

            for (CSVRecord record : records) {
                int cookingMinutes = Integer.parseInt(record.get("Cooking Minutes"));
                boolean dairyFree = Boolean.parseBoolean(record.get("Dairy Free"));
                boolean glutenFree = Boolean.parseBoolean(record.get("Gluten Free"));
                String instructions = record.get("Instructions").trim();
                double preparationMinutes = Double.parseDouble(record.get("Preparation Minutes"));
                double pricePerServing = Double.parseDouble(record.get("Price Per Serving"));
                int readyInMinutes = Integer.parseInt(record.get("Ready In Minutes"));
                int servings = Integer.parseInt(record.get("Servings"));
                double spoonacularScore = Double.parseDouble(record.get("Spoonacular Score"));
                String title = record.get("Title");
                boolean vegan = Boolean.parseBoolean(record.get("Vegan"));
                boolean vegetarian = Boolean.parseBoolean(record.get("Vegetarian"));

                Recipe recipe = new Recipe();
                recipe.setCookingMinutes(cookingMinutes);
                recipe.setDairyFree(dairyFree);
                recipe.setGlutenFree(glutenFree);
                recipe.setInstructions(instructions);
                recipe.setPreparationMinutes(preparationMinutes);
                recipe.setPricePerServing(pricePerServing);
                recipe.setReadyInMinutes(readyInMinutes);
                recipe.setServings(servings);
                recipe.setSpoonacularScore(spoonacularScore);
                recipe.setTitle(title);
                recipe.setVegan(vegan);
                recipe.setVegetarian(vegetarian);

                recipes.add(recipe);
            }

        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "File not found");
            throw new RuntimeException(e);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IOException");
            throw new RuntimeException(e);
        }

    }


    public List<Recipe> getAllRecipes() {
        return recipes;
    }

    // The “gluten-free” endpoint will only return a subset of the full Collection where glutenFree is true
    public List<Recipe> getGlutenFreeRecipes() {
        List<Recipe> glutenFreeRecipes = new ArrayList<>();
        for (Recipe recipe: recipes) {
            if (recipe.getGlutenFree()) {
                glutenFreeRecipes.add(recipe);
            }
        }

        return glutenFreeRecipes;
    }

    //    The “vegan” endpoint will only return a subset of the full Collection where vegan is true
    public List<Recipe> getVeganRecipes() {
        List<Recipe> veganRecipes = new ArrayList<>();
        for (Recipe recipe: recipes) {
            if (recipe.getVegan()) {
                 veganRecipes.add(recipe);
            }
        }

        return veganRecipes;
    }

    // The “vegan-and-gluten-free” endpoint will only return a subset of the full Collection where glutenFree is true and vegan is true
    public List<Recipe> getVeganAndGlutenFree() {
        List<Recipe> veganAndGlutenFreeRecipes = new ArrayList<>();
        for (Recipe recipe: recipes) {
            if (recipe.getVegan() && recipe.getGlutenFree()) {
                veganAndGlutenFreeRecipes.add(recipe);
            }
        }

        return veganAndGlutenFreeRecipes;
    }

    // The “vegetarian” endpoint will only return a subset of the full Collection where vegetarian is true
    public List<Recipe> getVegetarian (){
         List<Recipe> vegetarianRecipe = new ArrayList<>();
         for (Recipe recipe : recipes){
             if (recipe.getVegetarian()){
                 vegetarianRecipe.add(recipe);
             }
         }

         return  vegetarianRecipe;
    }

}
