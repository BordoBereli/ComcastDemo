package com.kutluoglu.domain.interactor

import com.kutluoglu.domain.executor.PostExecutionThread
import com.kutluoglu.domain.executor.ThreadExecutor
import com.kutluoglu.domain.interactor.baseUseCases.SingleUseCase
import com.kutluoglu.domain.model.Content
import com.kutluoglu.domain.repository.ComcastRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by F.K. on 2019-07-16.
 *
 */
open class GetContentsUseCase @Inject constructor(
    private val repository: ComcastRepository,
    threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread
) : SingleUseCase<List<Content>, Void>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseSingle(params: Void?): Single<List<Content>> {
        return repository.getContentList()
    }
}