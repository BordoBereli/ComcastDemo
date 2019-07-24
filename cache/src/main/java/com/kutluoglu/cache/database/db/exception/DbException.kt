package com.kutluoglu.cache.database.db.exception

/**
 * Created by F.K. on 2019-07-16.
 *
 */

class DbException : Exception() {
    override val message: String?
        get() = "There is an error on Demo DB operation: " + super.message
}