package com.ys.bup.hanbok.service

import com.ys.bup.hanbok.domain.Product
import com.ys.bup.hanbok.repository.product.MemoryProductRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigInteger

class ProductServiceTest {
    lateinit var productService: ProductService
    lateinit var productRepository: MemoryProductRepository

    @BeforeEach
    fun beforeEach() {
        productRepository = MemoryProductRepository()
        productService = ProductService(productRepository)
    }

    @AfterEach
    fun afterEach() {
        productRepository.clearStore()
    }

    @Test
    fun 상품_추가() {
        // given
        val product = Product(name = "과자", price = BigInteger.valueOf(1000), thumbnail = "", images = listOf(), rating = 4.4f, stock = 999)

        // when
        val saveId = productService.save(product)

        // then
        val findItem = productService.findOne(saveId)
        assertThat(findItem?.name).isEqualTo(product.name)
    }

    @Test
    fun 중복_상품_예외() {
        // given
        val product = Product(name = "과자", price = BigInteger.valueOf(1000), thumbnail = "", images = listOf(), rating = 4.4f, stock = 999)
        val product2 = Product(name = "과자", price = BigInteger.valueOf(2000), thumbnail = "", images = listOf(), rating = 4f, stock = 999)

        // when
        productService.save(product)
        val e = assertThrows(IllegalStateException::class.java) { productService.save(product2) }

        // then
        assertThat(e.message).isEqualTo("이미 존재하는 상품 입니다.")
    }

    @Test
    fun 상품_리스트_조회() {
        // given
        val product = Product(name = "과자", price = BigInteger.valueOf(1000), thumbnail = "", images = listOf(), rating = 4.4f, stock = 999)
        productService.save(product)
        val product2 = Product(name = "음료수", price = BigInteger.valueOf(2000), thumbnail = "", images = listOf(), rating = 4f, stock = 999)
        productService.save(product2)

        // when
        val result = productService.findProducts()

        // then
        assertThat(result.size).isEqualTo(2)
    }

    @Test
    fun 상품_조회() {
        // given
        val product = Product(name = "과자", price = BigInteger.valueOf(1000), thumbnail = "", images = listOf(), rating = 4.4f, stock = 999)
        val saveId = productService.save(product)

        // when
        val result = productService.findOne(saveId)

        // then
        assertThat(result).isNotNull
        assertThat(result).isEqualTo(product)
    }
}