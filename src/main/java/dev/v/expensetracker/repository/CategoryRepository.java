package dev.v.expensetracker.repository;

import dev.v.expensetracker.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByUserUserIdAndIsDefaultTrue(Long userId);
}
