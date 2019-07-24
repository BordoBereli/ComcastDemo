package com.kutluoglu.domain.interactor

import com.kutluoglu.domain.executor.PostExecutionThread
import com.kutluoglu.domain.executor.ThreadExecutor
import com.kutluoglu.domain.interactor.baseUseCases.SingleUseCase
import com.kutluoglu.domain.model.ContentDetail
import com.kutluoglu.domain.repository.ComcastRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by F.K. on 2019-07-16.
 *
 */

open class GetContentDetailUseCase @Inject constructor(
    private val repository: ComcastRepository,
    threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread
) : SingleUseCase<ContentDetail, String>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseSingle(title: String?): Single<ContentDetail> {
        return repository.getContentDetailBy(title)
    }
}