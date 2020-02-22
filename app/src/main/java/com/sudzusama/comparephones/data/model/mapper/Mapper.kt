package com.sudzusama.comparephones.data.model.mapper

abstract class Mapper<S, R> {
    abstract fun map(source: S): R
    abstract fun reverseMap(source: R): S

    fun map(sourceList: List<S>): List<R> {
        val resultList = ArrayList<R>()
        for (source in sourceList) {
            resultList.add(map(source))
        }
        return resultList
    }

    fun reverseMap(sourceList: List<R>): List<S> {
        val resultList = ArrayList<S>()
        for (source in sourceList) {
            resultList.add(reverseMap(source))
        }
        return resultList
    }

}