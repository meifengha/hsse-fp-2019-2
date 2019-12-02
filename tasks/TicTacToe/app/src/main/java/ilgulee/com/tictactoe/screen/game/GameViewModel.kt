package ilgulee.com.tictactoe.screen.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private val temp = mutableListOf<Char>()
    private val _cells = MutableLiveData<List<Char>>()
    val cells: LiveData<List<Char>> = _cells

    private val _turn = MutableLiveData<Char>()
    val turn: LiveData<Char> = _turn

    private val _result = MutableLiveData<Boolean>(false)
    val result: LiveData<Boolean> = _result

    private val _numberOfEmptyCells = MutableLiveData(9)
    val numberOfEmptyCells: LiveData<Int> = _numberOfEmptyCells

    init {
        initializeCells()

        randomFirstTurn()

        Log.i("GameViewModel", "GameViewModel created")
    }

    private fun initializeCells() {
        for (i in 0..8) {
            temp.add(i, ' ')
        }
        _cells.value = temp
    }

    private fun randomFirstTurn() {
        _turn.value = when ((1..2).shuffled().last()) {
            1 -> 'X'
            else -> 'O'
        }
    }

    fun fillCell(index: Int) {
        if (temp[index] == ' ' || temp[index].toString().trim().isEmpty()) {
            _turn.value?.let { temp.set(index, it) }
            _cells.value = temp
            if (_numberOfEmptyCells.value!! <= 5) {
                onGameResultCheck()
                Log.i("result", "checked")
            }
            _numberOfEmptyCells.value = _numberOfEmptyCells.value?.minus(1)
            Log.i("empty cells", _numberOfEmptyCells.value.toString())
            changeTurn()
            Log.i("turn", "changed")
        }
    }

    fun changeTurn() {
        _turn.value = when (_turn.value) {
            'X' -> 'O'
            else -> 'X'
        }
    }

    private fun onGameResultCheck() {
        if (_cells.value?.get(0) == _turn.value && _cells.value?.get(1) == _turn.value && _cells.value?.get(
                2
            ) == _turn.value
        ) {
            _result.value = true
        }
        if (_cells.value?.get(3) == _turn.value && _cells.value?.get(4) == _turn.value && _cells.value?.get(
                5
            ) == _turn.value
        ) {
            _result.value = true
        }
        if (_cells.value?.get(6) == _turn.value && _cells.value?.get(7) == _turn.value && _cells.value?.get(
                8
            ) == _turn.value
        ) {
            _result.value = true
        }
        if (_cells.value?.get(0) == _turn.value && _cells.value?.get(3) == _turn.value && _cells.value?.get(
                6
            ) == _turn.value
        ) {
            _result.value = true
        }
        if (_cells.value?.get(1) == _turn.value && _cells.value?.get(4) == _turn.value && _cells.value?.get(
                7
            ) == _turn.value
        ) {
            _result.value = true
        }
        if (_cells.value?.get(2) == _turn.value && _cells.value?.get(5) == _turn.value && _cells.value?.get(
                8
            ) == _turn.value
        ) {
            _result.value = true
        }
        if (_cells.value?.get(0) == _turn.value && _cells.value?.get(4) == _turn.value && _cells.value?.get(
                8
            ) == _turn.value
        ) {
            _result.value = true
        }
        if (_cells.value?.get(2) == _turn.value && _cells.value?.get(4) == _turn.value && _cells.value?.get(
                6
            ) == _turn.value
        ) {
            _result.value = true
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed")
    }

}