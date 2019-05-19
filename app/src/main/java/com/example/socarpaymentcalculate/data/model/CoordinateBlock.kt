package com.example.socarpaymentcalculate.data.model

data class CoordinateBlock private constructor(
    val leftTopPoint: Coordinate,
    val rightBottomPoint: Coordinate
) {

    companion object {

        fun with(leftTopPoint: Coordinate, rightBottomPoint: Coordinate): CoordinateBlock {
            return CoordinateBlock(leftTopPoint, rightBottomPoint)
        }
    }
}