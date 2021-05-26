package com.ruben.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

/**
 * Created by ruben.quadros on 26/05/21.
 **/
abstract class BaseUseCase<Request, Response> {

    abstract suspend fun run(request: Request): Response

    fun invoke(
        scope: CoroutineScope,
        request: Request,
        onResponse: (Response) -> Unit
    ) {
        val job = scope.async { run(request) }
        scope.launch {
            if (isActive) {
                onResponse(job.await())
            }
        }
    }

}