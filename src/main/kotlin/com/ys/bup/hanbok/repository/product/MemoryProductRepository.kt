package com.ys.bup.hanbok.repository.product

import com.ys.bup.hanbok.domain.Product
import org.springframework.stereotype.Repository

@Repository
class MemoryProductRepository : ProductRepository {

    private val store = hashMapOf<Long, Product>()
    private var sequence: Long = 0L

    override fun save(product: Product): Product {
        product.id = ++sequence
        store[product.id] = product
        return product
    }

    override fun findById(id: Long) = store[id]

    override fun findByName(name: String): Product? {
        if(store.values.isNotEmpty()) {
            return store.values.find { it.name == name }
        }

        return null
    }

    override fun findAll() = store.values.toList()

    fun clearStore() = store.clear()
}