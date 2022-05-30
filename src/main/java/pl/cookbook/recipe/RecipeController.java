package pl.cookbook.recipe;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.cookbook.category.Category;
import pl.cookbook.category.CategoryService;

import java.util.List;
import java.util.Optional;

@Controller
public class RecipeController {

    private final RecipeService recipeService;
    private final RecipeRepository recipeRepository;
    private final CategoryService categoryService;

    public RecipeController(RecipeService recipeService, RecipeRepository recipeRepository, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.recipeRepository = recipeRepository;
        this.categoryService = categoryService;
    }

    @GetMapping("/all-recipes")
    public String list(Model model) {
        model.addAttribute("recipes", recipeService.findAll());
        return "list";
    }

    @GetMapping("/recipe/{id}")
    public String recipe(@PathVariable Long id, Model model) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if (recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();
            model.addAttribute("recipes", recipe);
            return "recipe";
        } else {
            return "error";
        }
    }

    @GetMapping("/recipe/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Recipe recipe = recipeService.findById(id);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("recipe", recipe);
        return "updateRecipe";
    }

    @GetMapping("/recipe/{id}/remove")
    public String deleteRecipe(@PathVariable Long id) {
        recipeService.delete(id);
        return "redirect:/delete-success";
    }

    @PostMapping("/recipe/{id}/like")
    public String like(@PathVariable Long id) {
        recipeService.like(id);
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String listByCategory(Model model, @RequestParam(name = "id", required = false) Long categoryId) {
        model.addAttribute("recipes", recipeService.getRecipes(categoryId));
        return "list";
    }

    @GetMapping("/add-recipe")
    public String add(Model model) {
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("categories", categoryService.findAll());
        return "updateRecipe";
    }

    @PostMapping("/save")
    public String addRecipe(Recipe recipe) {
        recipeService.save(recipe);
        return "redirect:/all-recipes";
    }
}