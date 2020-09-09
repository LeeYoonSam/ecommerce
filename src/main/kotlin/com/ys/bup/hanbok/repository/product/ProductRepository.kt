package com.ys.bup.hanbok.repository.product

import com.ys.bup.hanbok.domain.Product
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository {
    fun save(product: Product): Product
    fun findById(id: Long): Product?
    fun findByName(name: String): Product?
    fun findAll(): List<Product>
}