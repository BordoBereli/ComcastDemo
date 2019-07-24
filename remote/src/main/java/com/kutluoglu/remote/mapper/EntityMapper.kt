package com.kutluoglu.remote.mapper

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer data source layers
 *
 * @param <R> the remote model input type
 * @param <D> the entity model output type
 */

interface EntityMapper<in R, out D> {
    fun mapFromRemote(type: R): D
}