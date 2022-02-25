package com.example.basketballscore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {
     private var _localScoreLiveData:MutableLiveData<Int> = MutableLiveData()
     private  var _visitorScoreLiveData:MutableLiveData<Int> = MutableLiveData()

     val localScoreLiveData:LiveData<Int>
     get() = _localScoreLiveData

    val visitorScoreLiveData:LiveData<Int>
        get() = _visitorScoreLiveData

    init {
        resetScores()
    }

    fun resetScores() {
        _localScoreLiveData.value = 0
        _visitorScoreLiveData.value = 0
    }

    fun addPointsToLocal(points: Int) {
        _localScoreLiveData.value = _localScoreLiveData.value?.plus(points)
    }

    fun addPointsToVisitor(points: Int) {
        _visitorScoreLiveData.value = _visitorScoreLiveData.value?.plus(points)
    }

    fun decreaseLocalScore(){
        if (_localScoreLiveData.value!!>0){
            _localScoreLiveData.value=_localScoreLiveData.value?.minus(1)

        }
    }
    fun decreaseVisitorScore(){
        if (_visitorScoreLiveData.value!!>0){
            _visitorScoreLiveData.value=_localScoreLiveData.value?.minus(1)
        }

    }



}