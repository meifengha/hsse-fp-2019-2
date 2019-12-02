package ilgulee.com.tictactoe.screen.game


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import ilgulee.com.tictactoe.R
import ilgulee.com.tictactoe.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    private lateinit var viewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentGameBinding>(
            inflater,
            R.layout.fragment_game,
            container,
            false
        )
        Log.i("in GameFragment", "calling ViewModelProviders")
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.result.observe(this, Observer { result ->
            if (result) {
                onEndGame()
            }
        })

        binding.endGameButton.setOnClickListener {
            viewModel.changeTurn()
            onEndGame()
        }

        viewModel.numberOfEmptyCells.observe(this, Observer { emptyCell ->
            if (emptyCell == 0) {
                val action = GameFragmentDirections.actionGameFragmentToResultFragment()
                action.winner = "Nobody"
                NavHostFragment.findNavController(this).navigate(action)
            }
        })

        return binding.root
    }

    private fun onEndGame() {
        onGameFinished()
    }

    private fun onGameFinished() {
        val action = GameFragmentDirections.actionGameFragmentToResultFragment()
        action.winner = viewModel.turn.value.toString()
        NavHostFragment.findNavController(this).navigate(action)
    }
}
