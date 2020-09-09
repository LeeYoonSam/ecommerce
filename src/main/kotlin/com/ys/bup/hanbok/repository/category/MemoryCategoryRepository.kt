package com.ys.bup.hanbok.repository.category

import com.ys.bup.hanbok.domain.Category
import org.springframework.stereotype.Repository

@Repository
class MemoryCategoryRepository : CategoryRepository {

    private val store = hashMapOf<Long, Category>()
    private var sequence: Long = 0L

    override fun save(category: Category): Category {
        category.id = ++sequence
        store[category.id] = category
        return category
    }

    override fun findById(id: Long) = store[id]

    override fun findByName(name: String): Category? {
        if(store.values.isNotEmpty()) {
            return store.values.find { it.name == name }
        }

        return null
    }

    override fun findAll() = store.values.toList()

    fun clearStore() = store.clear()
}