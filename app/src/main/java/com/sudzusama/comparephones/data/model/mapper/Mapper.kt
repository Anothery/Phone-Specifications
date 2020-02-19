package com.sudzusama.comparephones.data.model.mapper

interface Mapper<S, R> {
    fun map(source: S): R
}