package com.example.detail.view.profile

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.base.presentation.BaseViewModel
import com.example.base.subscriber.ResultState
import com.example.domain.entities.*
import com.example.domain.interactors.AlbumInteractors
import com.example.domain.interactors.CommentInteractors
import com.example.domain.interactors.PhotoInteractors
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel : BaseViewModel {

    private var albumInteractors: AlbumInteractors
    private var photoInteractors: PhotoInteractors
    lateinit var sharedPreferences: SharedPreferences
    private val gson: Gson = Gson()


    @Inject
    constructor(
        albumInteractors: AlbumInteractors,
        photoInteractors: PhotoInteractors
    ) : super(null) {
        this.albumInteractors = albumInteractors
        this.photoInteractors = photoInteractors
    }

    constructor(
        albumInteractors: AlbumInteractors,
        photoInteractors: PhotoInteractors,
        testScope: CoroutineScope?
    ) : super(testScope) {
        this.albumInteractors = albumInteractors
        this.photoInteractors = photoInteractors
    }

    private val mutableRepo = MutableLiveData<ResultState<List<AlbumDataEntities>>>()
    val albumEntites: LiveData<ResultState<List<AlbumDataEntities>>> = mutableRepo

    private val mutablePhotRepo = MutableLiveData<ResultState<List<PhotoDataEntities>>>()
    val photoEntites: LiveData<ResultState<List<PhotoDataEntities>>> = mutablePhotRepo

    suspend fun fetchAlbumData(userId: Int) {
        albumInteractors.addParam(AlbumInteractors.Params(userId))
            .execute(coroutineScope)
            .toResult()
            .run {
                val temps = ArrayList<AlbumDataEntities>()
                if (this is ResultState.Success) {
                    this.data.forEach { albums ->
                        val photo = getPhotos?.filter { photos ->
                            photos?.albumId == albums.id
                        }?.toMutableList()
                        albums.photo = photo ?: mutableListOf()
                        temps.add(albums)
                    }
                    mutableRepo.postValue(ResultState.Success(temps))
                }
            }
    }

    fun refreshAlbumData(userId: Int) {
        GlobalScope.launch {
            fetchAlbumData(userId)
        }
    }

    suspend fun fetchPhotData() {
        photoInteractors
            .execute(coroutineScope)
            .toResult()
            .run {
                if (this is ResultState.Success) {
                    addPhotos(this.data)
                }
            }
    }

    fun refreshPhotoData() {
        GlobalScope.launch {
            fetchPhotData()
        }
    }


    fun addPhotos(list: List<PhotoDataEntities>?) {
        sharedPreferences.edit().putString("photo", gson.toJson(list)).apply()
    }

    val getPhotos: MutableList<PhotoDataEntities?>?
        get() {
            var contestList: MutableList<PhotoDataEntities?> = gson.fromJson<MutableList<PhotoDataEntities?>>(
                sharedPreferences.getString("photo", "{}"),
                object
                    : TypeToken<MutableList<PhotoDataEntities>>() {}.type
            )
            if (contestList == null) contestList = ArrayList()
            return contestList
        }
}