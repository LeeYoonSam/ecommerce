package com.ys.bup.hanbok.controller

import com.ys.bup.hanbok.domain.Product
import com.ys.bup.hanbok.service.ProductService
import com.ys.bup.hanbok.utils.CommonConfig
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class ProductController(private val productService: ProductService) {

    @GetMapping("${CommonConfig.API}${CommonConfig.PATH_PRODUCT}list")
    @ResponseBody
    fun getProducts(model: Model): List<Product> = productService.findProducts()
}