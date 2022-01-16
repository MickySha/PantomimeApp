package com.micky.pantomim.ui.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.micky.pantomim.R
import com.micky.pantomim.databinding.FragmentGameBinding


/**
 * A simple [Fragment] subclass.
 * Use the [GameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFragment : Fragment() {

    lateinit var binding: FragmentGameBinding

    lateinit var viewModel: GameViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGameBinding.inflate(inflater)

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //two important factor that we should add for data binding (below)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.getWords ()

        onClickFun()

        viewModel.nextWord()

        observeLiveData()

        finishBtnVisibility()
    }


    private fun onClickFun() {

//        binding.btnAddWord.setOnClickListener(View.OnClickListener {
//            viewModel.addNewWordFun()
//        })

        binding.btnStartTimer.setOnClickListener(View.OnClickListener {
            viewModel.timerFun()
        })

        binding.btnCorrect.setOnClickListener(View.OnClickListener {
            viewModel.correctAnswerFun()
        })

        binding.btnWrong.setOnClickListener(View.OnClickListener {
            viewModel.wrongAnswerFun()
        })

        binding.btnFinish.setOnClickListener(View.OnClickListener {

            viewModel.stopTimerFun()
            openResultFragment ()
        })
    }

    private fun observeLiveData() {

        viewModel.listFinish.observe(viewLifecycleOwner) {
            finishBtnVisibility()
        }

        viewModel.startTimerFlag.observe(viewLifecycleOwner) {
            timerBtnFun()
        }
    }

    private fun timerBtnFun() {
        if (viewModel.startTimerFlag.value == false) {

            binding.btnStartTimer.setText(getString(R.string.btn_start_timer_txt))
            binding.tvTimer.setText("00:10")
        }
        else {
            binding.btnStartTimer.setText(getString(R.string.btn_stop_timer_txt))
        }
    }

    private fun finishBtnVisibility() {
        if (viewModel.listFinish.value == true)
            binding.btnFinish.visibility = View.VISIBLE
    }

    private fun openResultFragment() {

        findNavController().navigate(
            GameFragmentDirections.actionGameFragmentToResultFragment(
                viewModel.score.value?:0, viewModel.counter.value?:0))
    }

} // end of Class