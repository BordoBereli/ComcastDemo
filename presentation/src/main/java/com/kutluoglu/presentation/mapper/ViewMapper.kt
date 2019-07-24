package com.kutluoglu.presentation.mapper

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving and putting of models from presentation layer
 *
 * @param <D> the domain model input type
 * @param <V> the view model return type
 */

interface ViewMapper<D, V> {

    fun mapToView(type: D): V

    fun mapFromView(type: V): D
}