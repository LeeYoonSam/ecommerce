package com.ys.bup.hanbok.repository

import com.ys.bup.hanbok.domain.Product
import com.ys.bup.hanbok.repository.product.MemoryProductRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import java.math.BigInteger

class MemoryProductRepositoryTest {
    private val repository: MemoryProductRepository = MemoryProductRepository()

    @AfterEach
    fun afterEach() {
        repository.clearStore()
    }

    @Test
    fun save() {
        val product = Product(name = "과자", price = BigInteger.valueOf(1000), thumbnail = "", images = listOf(), rating = 4.4f, stock = 999).also {
            println(it)
        }

        repository.save(product)

        val result = repository.findById(product.id)
        assertThat(result).isNotNull
        assertThat(result).isEqualTo(product)
    }

    @Test
    fun findById() {
        val product = Product(id = 0, name = "과자", price = BigInteger.valueOf(1000), thumbnail = "", images = listOf(), rating = 4.4f, stock = 999).also {
            println(it)
        }

        repository.save(product)

        val product2 = Product(id = 1, name = "음료수", price = BigInteger.valueOf(2000), thumbnail = "", images = listOf(), rating = 4f, stock = 999).also {
            println(it)
        }

        repository.save(product2)

        val result = repository.findById(product.id)
        assertThat(result).isEqualTo(product)

        val result2 = repository.findById(product2.id)
        assertThat(result2).isEqualTo(product2)
    }

    @Test
    fun findAll() {
        val product = Product(id = 0, name = "과자", price = BigInteger.valueOf(1000), thumbnail = "", images = listOf(), rating = 4.4f, stock = 999).also {
            println(it)
        }

        repository.save(product)

        val product2 = Product(id = 1, name = "음료수", price = BigInteger.valueOf(2000), thumbnail = "", images = listOf(), rating = 4f, stock = 999).also {
            println(it)
        }

        repository.save(product2)

        val result: List<Product> = repository.findAll()
        assertThat(result.size).isEqualTo(2)
    }
}