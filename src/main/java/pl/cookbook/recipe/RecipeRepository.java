package pl.cookbook.recipe;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findTop3ByOrderByLikesCounterDesc();

    List<Recipe> findAllByOrderByNameAsc();

    List<Recipe> findByCategory_Id(Long id);
}
