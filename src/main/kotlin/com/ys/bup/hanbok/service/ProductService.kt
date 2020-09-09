package com.ys.bup.hanbok.service

import com.ys.bup.hanbok.domain.Product
import com.ys.bup.hanbok.repository.product.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {
    /**
     * 상품 추가
     */
    fun save(product: Product): Long {
        validateDuplicateProduct(product)
        productRepository.save(product)
        return product.id
    }

    private fun validateDuplicateProduct(product: Product) {
        product.name.let {
            if(it == null) {
                throw IllegalStateException("상품 이름이 없습니다.")
            }

            // 카테고리가 있으면 중복 처리
            val result = productRepository.findByName(it)
            result?.let {
                throw IllegalStateException("이미 존재하는 상품 입니다.")
            }
        }
    }

    /**
     * 상품 조회
     */
    fun findProducts(): List<Product> = productRepository.findAll()

    fun findOne(productId: Long) = productRepository.findById(productId)
}