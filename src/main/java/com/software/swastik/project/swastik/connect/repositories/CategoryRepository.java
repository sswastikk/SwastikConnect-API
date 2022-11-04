package com.software.swastik.project.swastik.connect.repositories;

import com.software.swastik.project.swastik.connect.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
