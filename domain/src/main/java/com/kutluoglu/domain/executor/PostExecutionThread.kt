package com.kutluoglu.domain.executor

import io.reactivex.Scheduler

/**
 * Created by F.K. on 2019-07-16.
 *
 */

interface PostExecutionThread {
    val scheduler: Scheduler
}