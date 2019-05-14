package com.example.socarpaymentcalculate.data.remote.reqeust

data class RouteSearchRequestBody constructor(
    val startX: Double,
    val startY: Double,
    val endX: Double,
    val endY: Double,
    val totalValue: Int
)