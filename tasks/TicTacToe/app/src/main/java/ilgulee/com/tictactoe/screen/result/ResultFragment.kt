package ilgulee.com.tictactoe.screen.result


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import ilgulee.com.tictactoe.R
import ilgulee.com.tictactoe.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    private lateinit var viewModel: ResultViewModel
    private lateinit var viewModelFactory: ResultViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentResultBinding>(
            inflater,
            R.layout.fragment_result,
            container,
            false
        )
        Log.i("in ResultFragment", "calling ViewModelFactory")
        viewModelFactory = ResultViewModelFactory(ResultFragmentArgs.fromBundle(arguments!!).winner)
        Log.i("in ResultFragment", "calling ViewModel")
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ResultViewModel::class.java)
        Log.i("in ResultFragment", "Test winner is ${viewModel.winner}")

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.eventPlayAgain.observe(
            this,
            Observer { playAgain -> if (playAgain) navigateToTitle() })

        return binding.root
    }

    private fun navigateToTitle() {
        findNavController().navigate(ResultFragmentDirections.actionRestart())
        viewModel.onPlayAgainComplete()
    }

}
