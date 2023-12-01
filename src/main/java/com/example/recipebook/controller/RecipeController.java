package main.java.com.example.recipebook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//
import com.example.demo.Recipe;
import com.example.demo.service.RecipeService;

@RestController
@RequestMapping("/api/recipes")
@CrossOrigin(origins = "http://localhost:3000")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @PostMapping("/save")
    public ResponseEntity<Recipe> saveRecipe(@RequestBody Recipe recipe) {
        try {
            // Log request details
            System.out.println("Received POST request to save recipe: " + recipe);

            Recipe savedRecipe = recipeService.saveRecipe(recipe);

            // Log successful response
            System.out.println("Recipe saved successfully: " + savedRecipe);

            return new ResponseEntity<>(savedRecipe, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log any exceptions
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        List<Recipe> recipes = recipeService.getAllRecipes();
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @PostMapping("/favorite/{id}")
    public ResponseEntity<Recipe> toggleFavoriteStatus(
            @PathVariable Long id,
            @RequestParam boolean isFavorite) {
        Recipe updatedRecipe = recipeService.saveFavoriteStatus(id, isFavorite);
        if (updatedRecipe != null) {
            return new ResponseEntity<>(updatedRecipe, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Handle this case based on your requirements
        }
    }

    @Controller
    public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
        /**
         * @return
         */
        @GetMapping("/error")
        public String handleError() {
            // Provide your custom error handling logic or return a specific error page
            return "error";
        }

        public String getErrorPath() {
            return "/error";
        }
    }

    // Add more endpoints as needed
}
