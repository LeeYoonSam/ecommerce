package com.ys.bup.hanbok.repository.category

import com.ys.bup.hanbok.domain.Category

interface CategoryRepository {
    fun save(category: Category): Category
    fun findById(id: Long): Category?
    fun findByName(name: String): Category?
    fun findAll(): List<Category>
}