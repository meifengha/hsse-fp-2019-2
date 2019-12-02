package ilgulee.com.tictactoe.screen.result

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResultViewModel(winner: String) : ViewModel() {

    private val _winner: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    val winner: LiveData<String>
        get() = _winner

    private val _eventPlayAgain: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val eventPlayAgain: LiveData<Boolean>
        get() = _eventPlayAgain

    init {
        Log.i("ResultViewModel", "Winner is $winner")
        _winner.value = winner
    }

    fun onPlayAgain() {
        _eventPlayAgain.value = true
    }

    fun onPlayAgainComplete() {
        _eventPlayAgain.value = false
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("ResultViewModel", "ResultViewModel destroyed")
    }
}