package com.kutluoglu.data.mapper

/**
 * Created by F.K. on 2019-07-16.
 *
 */

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving and putting of models from data source layers to domain layer
 *
 * @param <E> the data(entity) model input type
 * @param <D> the domain model return type
 */

interface Mapper<E, D> {
    fun mapFromEntityToDomainModel(type: E) : D
    fun mapToEntityFromDomainModel(type: D) : E
}