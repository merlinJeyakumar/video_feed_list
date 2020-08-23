package com.merlin.training_mvvm.support.inline

fun <T> Collection<T>.filterNotIn(collection: Collection<T>): Collection<T> {
    val set = collection.toSet()
    return filterNot { set.contains(it) }
}