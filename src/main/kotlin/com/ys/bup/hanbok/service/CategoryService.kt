package com.ys.bup.hanbok.service

import com.ys.bup.hanbok.domain.Category
import com.ys.bup.hanbok.repository.category.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(private val categoryRepository: CategoryRepository) {
    /**
     * 카테고리 추가
     */
    fun save(category: Category): Long {
        validateDuplicateCategory(category)   // 중복 회원 검증
        categoryRepository.save(category)
        return category.id
    }

    private fun validateDuplicateCategory(category: Category) {
        category.name.let {
            if(it == null) {
                throw IllegalStateException("카테고리 이름이 없습니다.")
            }

            // 카테고리가 있으면 중복 처리
            val result = categoryRepository.findByName(it)
            result?.let {
                throw IllegalStateException("이미 존재하는 카테고리 입니다.")
            }
        }
    }

    /**
     * 카테고리 조회
     */
    fun findCategories(): List<Category> = categoryRepository.findAll()

    fun findOne(categoryId: Long) = categoryRepository.findById(categoryId)
}