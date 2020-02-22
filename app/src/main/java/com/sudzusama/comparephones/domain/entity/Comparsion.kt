package com.sudzusama.comparephones.domain.entity

data class Comparsion(
    val comparsionId : Int,
    val firstDevice: Device,
    val secondDevice: Device
)