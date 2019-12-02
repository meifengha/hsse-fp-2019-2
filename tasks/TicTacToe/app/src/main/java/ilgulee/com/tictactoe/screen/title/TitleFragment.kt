package ilgulee.com.tictactoe.screen.title


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import ilgulee.com.tictactoe.R
import ilgulee.com.tictactoe.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentTitleBinding>(
            inflater,
            R.layout.fragment_title,
            container,
            false
        )
        binding.playGameButton.setOnClickListener {
            it.findNavController()
                .navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment())
        }
        return binding.root
    }


}
