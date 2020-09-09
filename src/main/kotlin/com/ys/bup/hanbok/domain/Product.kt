package com.ys.bup.hanbok.domain

import java.math.BigInteger

data class Product(
        var id: Long = -1,

        /**
         * 상품 이름
         */
        val name: String,

        /**
         * 상품 가격
         */
        val price: BigInteger = BigInteger.ZERO,

        /**
         * 썸네일 이미지
         */
        val thumbnail: String,

        /**
         * 상품 이미지 리스트
         */
        val images: List<String>,

        /**
         * 할인율
         */
        val saleRate: Int = 0,

        /**
         * 평점
         */
        val rating: Float = 0f,

        /**
         * 재고 수량
         */
        val stock: Int = 0
)