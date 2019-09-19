package com.example.minimoneybox.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.minimoneybox.LoginActivity
import com.example.minimoneybox.adapter.MarginItemDecoration
import com.example.minimoneybox.R
import com.example.minimoneybox.adapter.PlanListAdapter
import com.example.minimoneybox.api.NetworkLayer
import com.example.minimoneybox.model.UserPlan

class UserAccountsFragment : Fragment() {

    private lateinit var tv_welcome: TextView
    private lateinit var tv_total_plan_value: TextView
    private lateinit var lv_plan_list: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_accounts, container, false)

        setUpViews(view)

        return view
    }

    private fun setUpAdapter() {
        val userPlanArray: ArrayList<UserPlan> = getPlanList()

        viewManager = LinearLayoutManager(context)
        viewAdapter = PlanListAdapter(userPlanArray, ::partItemClicked)
    }

    private fun getPlanList(): ArrayList<UserPlan> {
        val userPlanArray: ArrayList<UserPlan> = ArrayList()

        for (plan in NetworkLayer.investorProductsDto.plans) {

            val userPlan = UserPlan(
                id = plan.id,
                name = plan.product.friendlyName,
                planValue = resources.getString(R.string.plan_value) + resources.getString(R.string.pounds) + plan.planValue.toString(),
                moneyBox = resources.getString(R.string.moneybox) + resources.getString(R.string.pounds) + plan.moneyBox.toString()
            )

            userPlanArray.add(userPlan)
        }
        return userPlanArray
    }

    private fun setUpViews(view: View) {
        tv_welcome = view.findViewById(R.id.tv_welcome)
        tv_total_plan_value = view.findViewById(R.id.tv_total_plan_value)

        tv_welcome.text =
            resources.getString(R.string.welcome) + NetworkLayer.loginDto.user.firstName + resources.getString(
                R.string.exclamation_mark
            )
        tv_total_plan_value.text =
            resources.getString(R.string.total_plan_value) + resources.getString(R.string.pounds) + NetworkLayer.investorProductsDto.totalPlanValue.toString()

        lv_plan_list = view.findViewById<RecyclerView>(R.id.rv_plan_list).apply {
            setHasFixedSize(true)

            layoutManager = viewManager
            adapter = viewAdapter

            addItemDecoration(
                MarginItemDecoration(
                    resources.getDimension(R.dimen.activity_vertical_margin).toInt()
                )
            )
        }
    }

    private fun partItemClicked(plan: UserPlan) {
        val userPlanFragment = UserPlanFragment()
        val bundle = Bundle()
        bundle.putInt("id", plan.id)
        bundle.putString("name", plan.name)
        bundle.putString("value", plan.planValue)
        bundle.putString("moneybox", plan.moneyBox)
        userPlanFragment.arguments = bundle
        (context as LoginActivity).addFragment(userPlanFragment)
    }

    fun updateAdapter() {
        NetworkLayer.getInvestorProductsApiCall {

            (viewAdapter as PlanListAdapter).refreshDataset(getPlanList())
        }
    }
}
