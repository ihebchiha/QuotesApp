package com.ihebchiha.hiltapp.networking.mapper

/*
    This interface is made to map data retrieved from network call.
    I: is the response class (Basically, it's a model which contains all the information came from he network call)
    O: is the model that we want to use within the app
    This mapper is generic to fit all responses
 */
interface DomainMapper<O> {
    fun  map(): O
}