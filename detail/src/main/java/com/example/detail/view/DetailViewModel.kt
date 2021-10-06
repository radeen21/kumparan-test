package com.example.detail.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.base.presentation.BaseViewModel
import com.example.base.subscriber.ResultState
import com.example.domain.entities.CommentEntites
import com.example.domain.interactors.CommentInteractors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel : BaseViewModel {

    private var commentsInteractors: CommentInteractors

    @Inject
    constructor(commentsInteractors: CommentInteractors) : super(null) {
        this.commentsInteractors = commentsInteractors
    }

    constructor(
        commentsInteractors: CommentInteractors,
        testScope: CoroutineScope?
    ) : super(testScope) {
        this.commentsInteractors = commentsInteractors
    }

    private val mutableRepo = MutableLiveData<ResultState<List<CommentEntites>>>()
    val commentEntites: LiveData<ResultState<List<CommentEntites>>> = mutableRepo

    suspend fun fetchCommentData(postId: Int) {
        commentsInteractors.addParam(CommentInteractors.Params(postId))
            .execute(coroutineScope)
            .toResult()
            .run(mutableRepo::postValue)
    }

    fun refreshCommentData(postId: Int) {
        GlobalScope.launch {
            fetchCommentData(postId)
        }
    }

}