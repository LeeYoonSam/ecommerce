package com.ys.bup.hanbok.service

import com.ys.bup.hanbok.domain.Category
import com.ys.bup.hanbok.repository.MemoryCategoryRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CategoryServiceTest {
    lateinit var categoryService: CategoryService
    lateinit var categoryRepository: MemoryCategoryRepository

    @BeforeEach
    fun beforeEach() {
        categoryRepository = MemoryCategoryRepository()
        categoryService = CategoryService(categoryRepository)
    }

    @AfterEach
    fun afterEach() {
        categoryRepository.clearStore()
    }

    @Test
    fun 카테고리_추가() {
        // given
        val category = Category(name = "한복")

        // when
        val saveId = categoryService.save(category)

        // then
        val findCategory = categoryService.findOne(saveId)
        assertThat(findCategory?.name).isEqualTo(category.name)
    }

    @Test
    fun 중복_카테고리_예외() {
        // given
        val category = Category(name = "한복")
        val category2 = Category(name = "한복")

        // when
        categoryService.save(category)
        val e = assertThrows(IllegalStateException::class.java) { categoryService.save(category2) }

        // then
        assertThat(e.message).isEqualTo("이미 존재하는 카테고리 입니다.")
    }

    @Test
    fun 카테고리_리스트_조회() {
        // given
        val category = Category(name = "한복")
        categoryService.save(category)

        val category2 = Category(name = "패턴")
        categoryService.save(category2)

        // when
        val result = categoryService.findCategories()

        // then
        assertThat(result.size).isEqualTo(2)
    }

    @Test
    fun 카테고리_지정_조회() {
        // given
        val category = Category(name = "한복")
        val saveId = categoryService.save(category)

        // when
        val result = categoryService.findOne(saveId)

        // then
        assertThat(result).isNotNull
        assertThat(result).isEqualTo(category)
    }
}