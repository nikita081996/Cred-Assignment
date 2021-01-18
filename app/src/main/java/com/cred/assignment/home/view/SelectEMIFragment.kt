package com.cred.assignment.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.cred.assignment.R
import com.cred.assignment.callback.ItemClickListener
import com.cred.assignment.databinding.FragmentSelectEmiBinding
import com.cred.assignment.home.adapter.SelectPlanAdapter
import com.cred.assignment.home.model.Plan
import com.cred.assignment.utility.OnSwipeTouchListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectEMIFragment : Fragment(), View.OnClickListener, ItemClickListener<Plan>,
    OnSwipeTouchListener.onSwipeListener {
    private lateinit var mBinding: FragmentSelectEmiBinding
    private lateinit var navController: NavController
    private var adapter: SelectPlanAdapter? = null
    private var dataList = ArrayList<Plan>()
    private var selectedPlan: Plan? = null
    private var onSwipeTouchListener: OnSwipeTouchListener? = null

    private var amount: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSelectEmiBinding.inflate(inflater, container, false).also {
            mBinding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        onSwipeTouchListener =
            context?.let { OnSwipeTouchListener(it, mBinding.topViewCl, this) }!!
        if (arguments != null) {
            val args = SelectEMIFragmentArgs.fromBundle(requireArguments())
            amount = args.amount
        }
        populateData()
        setupRecyclerView()
        mBinding.tvCreditAmount.text = amount
        mBinding.btnSelectBank.setOnClickListener(this)
    }

    private fun setupRecyclerView() {
        adapter = SelectPlanAdapter(this)
        mBinding.rvCard.adapter = adapter
        adapter?.submitList(dataList)

    }

    private fun populateData() {
        dataList = ArrayList()
        for (i in 0..5) {
            val plan = Plan(
                12, (2345 * (i+1)).toString(), i == 1, i == 0,
                if (i % 3 == 0)
                    R.color.color1
                else if (i % 3 == 1)
                    R.color.color2
                else
                    R.color.color3
            )
            if (i == 0) {
                selectedPlan = plan
            }
            dataList.add(plan)
        }

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_select_bank -> {
                goToBankFragment()
            }
        }
    }

    private fun goToBankFragment() {
        selectedPlan?.let {
            val action =
                amount?.let { it1 ->
                    SelectEMIFragmentDirections.actionSelectEMIFragmentToSelectBankFragment(
                        selectedPlan,
                        it1
                    )
                }
            action?.let { it1 -> navController.navigate(it1) }
        }
    }

    override fun onRecyclerItemClicked(position: Int, view: View, data: Plan) {
        for (i in 0 until dataList.size) {
            dataList[i].selected = i == position
        }
        adapter?.notifyDataSetChanged()
        selectedPlan = data
    }

    override fun swipeRight() {

    }

    override fun swipeUp() {
        goToBankFragment()
    }

    override fun swipeBottom() {
        navController.popBackStack()
    }

    override fun swipeLeft() {
    }


}