package com.ys.bup.hanbok.repository

import com.ys.bup.hanbok.domain.Category
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

class MemoryCategoryRepositoryTest {
    private val repository: MemoryCategoryRepository = MemoryCategoryRepository()

    @AfterEach
    fun afterEach() {
        repository.clearStore()
    }

    @Test
    fun save() {
        val category = Category(name = "한복").also {
            println(it)
        }

        repository.save(category)

        val result = repository.findById(category.id)
        assertThat(result).isNotNull
        assertThat(result).isEqualTo(category)
    }

    @Test
    fun findByName() {
        val category = Category(name = "한복").also {
            println(it)
        }

        repository.save(category)

        val category2 = Category(name = "패턴").also {
            println(it)
        }

        repository.save(category2)

        val result = repository.findByName("한복")
        assertThat(result).isEqualTo(category)
    }

    @Test
    fun findAll() {
        val category = Category(name = "한복").also {
            println(it)
        }

        repository.save(category)

        val category2 = Category(name = "패턴").also {
            println(it)
        }

        repository.save(category2)

        val result: List<Category> = repository.findAll()
        assertThat(result.size).isEqualTo(2)
    }
}