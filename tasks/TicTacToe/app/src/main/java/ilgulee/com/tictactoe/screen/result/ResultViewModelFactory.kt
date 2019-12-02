package ilgulee.com.tictactoe.screen.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class ResultViewModelFactory(private val winner: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultViewModel::class.java)) {
            return ResultViewModel(winner) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}