package main.java.com.example.recipebook.repository;

import com.yourpackage.recipebook.model.Recipe;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<main.java.com.example.recipebook.Recipe> findAll();
    // Add custom queries or methods if needed

    main.java.com.example.recipebook.Recipe save(main.java.com.example.recipebook.Recipe recipe);

    Object findById(Long id);
}