package com.ys.bup.hanbok.controller

import com.ys.bup.hanbok.domain.Category
import com.ys.bup.hanbok.service.CategoryService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody

const val PATH_CATEGORY ="category/"
const val API_CATEGORY ="api/"

@Controller
class CategoryController(private val categoryService: CategoryService) {

    @GetMapping("${PATH_CATEGORY}new")
    fun createForm() = "${PATH_CATEGORY}createCategoryForm"

    @PostMapping("${PATH_CATEGORY}new")
    fun create(form: CategoryForm): String? {
        val category = Category(name = form.name)
        categoryService.save(category)

        return "redirect:/"
    }

    @GetMapping("${API_CATEGORY}list")
    @ResponseBody
    fun getCategories(model: Model): List<Category> {
        val categories = categoryService.findCategories()
        return categories
    }
}