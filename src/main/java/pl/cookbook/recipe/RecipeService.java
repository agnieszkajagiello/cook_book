package pl.cookbook.recipe;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<Recipe> find3MostLikes() {
        return recipeRepository.findTop3ByOrderByLikesCounterDesc();
    }

    public List<Recipe> findAll() {
        return recipeRepository.findAllByOrderByNameAsc();
    }

    public void save(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    public List<Recipe> getRecipes(Long categoryId) {
        if (categoryId != null) {
            return findByCategory(categoryId);
        } else {
            return findAll();
        }
    }

    private List<Recipe> findByCategory(Long id) {
        return recipeRepository.findByCategory_Id(id);
    }

    public void like(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if(recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();
            recipe.setLikesCounter(recipe.getLikesCounter() + 1);
            recipeRepository.save(recipe);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Recipe findById(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if(recipeOptional.isPresent()) {
            return recipeOptional.get();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void delete(Long id) {
        recipeRepository.deleteById(id);
    }
}
