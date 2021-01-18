package com.cred.assignment.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.cred.assignment.R
import com.cred.assignment.databinding.FragmentSelectMoneyBinding
import com.cred.assignment.utility.OnSwipeTouchListener
import dagger.hilt.android.AndroidEntryPoint
import me.tankery.lib.circularseekbar.CircularSeekBar

@AndroidEntryPoint
class SelectMoneyFragment : Fragment(), View.OnClickListener,
    CircularSeekBar.OnCircularSeekBarChangeListener, OnSwipeTouchListener.onSwipeListener {
    private lateinit var mBinding: FragmentSelectMoneyBinding
    private lateinit var navController: NavController
    private var onSwipeTouchListener: OnSwipeTouchListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSelectMoneyBinding.inflate(inflater, container, false).also {
            mBinding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        onSwipeTouchListener =
            context?.let { OnSwipeTouchListener(it, mBinding.topViewCl, this) }!!
        mBinding.seekBar.max = 1000000f
        mBinding.seekBar.setOnSeekBarChangeListener(this)

        mBinding.btnProceedToEmi.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        mBinding.tvAmount.text = getString(R.string.rupee) + mBinding.seekBar.progress.toInt()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_proceed_to_emi -> {
                goToEMIFragment()
            }
        }
    }

    private fun goToEMIFragment() {
        val action =
            SelectMoneyFragmentDirections.actionSelectMoneyFragmentToSelectEMIFragment(
                mBinding.tvAmount.text.toString()
            )
        navController.navigate(action)
    }

    override fun onProgressChanged(
        circularSeekBar: CircularSeekBar?,
        progress: Float,
        fromUser: Boolean
    ) {
        mBinding.tvAmount.text = getString(R.string.rupee) + progress.toInt().toString()
    }

    override fun onStopTrackingTouch(seekBar: CircularSeekBar?) {

    }

    override fun onStartTrackingTouch(seekBar: CircularSeekBar?) {
    }

    override fun swipeRight() {

    }

    override fun swipeUp() {
        goToEMIFragment()
    }

    override fun swipeBottom() {
    }

    override fun swipeLeft() {
    }


}