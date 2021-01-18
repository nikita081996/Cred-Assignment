package com.cred.assignment.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.cred.assignment.R
import com.cred.assignment.databinding.FragmentSelectBankBinding
import com.cred.assignment.home.model.Plan
import com.cred.assignment.utility.OnSwipeTouchListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectBankFragment : Fragment(), View.OnClickListener,
    OnSwipeTouchListener.onSwipeListener {
    private lateinit var mBinding: FragmentSelectBankBinding
    private lateinit var navController: NavController
    private var onSwipeTouchListener: OnSwipeTouchListener? = null
    private var plan: Plan? = null
    private var amount: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSelectBankBinding.inflate(inflater, container, false).also {
            mBinding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        onSwipeTouchListener =
            context?.let { OnSwipeTouchListener(it, mBinding.topViewCl, this) }!!
        if (arguments != null) {
            val args = SelectBankFragmentArgs.fromBundle(requireArguments())
            plan = args.plan
            amount = args.amount
        }
        populateView()
        mBinding.btnKyc.setOnClickListener(this)
    }

    private fun populateView() {
        mBinding.tvCreditAmount.text = amount
        mBinding.tvEmiAmount.text = getString(R.string.rupee) + plan?.amount + " /mo"
        mBinding.tvEmiMonthVal.text = plan?.month.toString() + " months"
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_kyc -> {
                Toast.makeText(context, "KYC done", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun swipeRight() {

    }

    override fun swipeUp() {
    }

    override fun swipeBottom() {
        navController.popBackStack()
    }

    override fun swipeLeft() {
    }

}