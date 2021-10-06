package com.example.home.view

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.base.presentation.BaseViewModel
import com.example.base.subscriber.ResultState
import com.example.domain.entities.PostEntites
import com.example.domain.interactors.PostInteractors
import com.example.domain.interactors.UserInteractors
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.domain.entities.UserDataEntites
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostViewModel : BaseViewModel {

    private var repositoryImpl: PostInteractors
    private var userInteractors: UserInteractors
    lateinit var sharedPreferences: SharedPreferences
    private val gson: Gson = Gson()

    @Inject
    constructor(repositoryImpl: PostInteractors, userInteractors: UserInteractors) : super(null) {
        this.repositoryImpl = repositoryImpl
        this.userInteractors = userInteractors
    }

    constructor(
        repositoryImpl: PostInteractors,
        userInteractors: UserInteractors,
        testScope: CoroutineScope?
    ) : super(testScope) {
        this.repositoryImpl = repositoryImpl
        this.userInteractors = userInteractors
    }

    private val mutableRepo = MutableLiveData<ResultState<List<PostEntites>>>()
    val postEntites: LiveData<ResultState<List<PostEntites>>> = mutableRepo

    init {
        refreshGetUser()
        refreshPostData()
    }

    suspend fun fetchPostData() {
        repositoryImpl
            .execute(coroutineScope)
            .toResult()
            .run {
                val temps = ArrayList<PostEntites>()
                if (this is ResultState.Success) {
                    this.data.forEach { posts ->
                        val user = getUsers?.find{ users ->
                            users.id == posts.userId
                        }
                        posts.user = user
                        temps.add(posts)
                    }
                    mutableRepo.postValue(ResultState.Success(temps))
                }
            }
    }


    fun refreshPostData() {
        GlobalScope.launch {
            fetchPostData()
        }
    }

    fun refreshGetUser() {
        GlobalScope.launch {
            getUser()
        }
    }

    suspend fun getUser() {
        userInteractors
            .execute(coroutineScope)
            .toResult()
            .run {
                if (this is ResultState.Success) {
                    addUsers(this.data)
                }
            }
    }


    fun addUsers(list: List<UserDataEntites>?) {
        sharedPreferences.edit().putString("users", gson.toJson(list)).apply()
    }

    val getUsers: List<UserDataEntites>? get() {
        var contestList: List<UserDataEntites>? = gson.fromJson<List<UserDataEntites>>(sharedPreferences.getString("users", ""), object
            : TypeToken<List<UserDataEntites>?>() {}.type)
        if (contestList == null) contestList = ArrayList()
        return contestList
    }

}