package com.example.minimoneybox.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.minimoneybox.LoginActivity

import com.example.minimoneybox.R
import com.example.minimoneybox.api.NetworkLayer
import com.example.minimoneybox.model.UserPlan

class UserPlanFragment : Fragment() {

    private lateinit var tv_plan_name: TextView
    private lateinit var tv_plan_value: TextView
    private lateinit var tv_moneybox: TextView
    private lateinit var btn_add: Button
    private val investmentValue = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_user_plan, container, false)

        val bundle = arguments
        if (bundle != null) {
            setUpViews(view, bundle)
        }

        return view
    }

    private fun setUpViews(view: View, bundle: Bundle) {

        val userPlan = UserPlan(
            id = bundle.getInt("id"),
            name = bundle.getString("name") as String,
            planValue = bundle.getString("value") as String,
            moneyBox = bundle.getString("moneybox") as String
        )

        tv_plan_name = view.findViewById(R.id.tv_plan_name)
        tv_plan_value = view.findViewById(R.id.tv_plan_value)
        tv_moneybox = view.findViewById(R.id.tv_moneybox)
        btn_add = view.findViewById(R.id.btn_add)

        tv_plan_name.text = userPlan.name
        tv_plan_value.text = userPlan.planValue
        tv_moneybox.text = userPlan.moneyBox

        btn_add.text = resources.getString(R.string.add_btn)+resources.getString(R.string.pounds)+investmentValue

        btn_add.setOnClickListener {
            NetworkLayer.oneOffPaymentsApiCall(investmentValue.toFloat(), userPlan.id, ::handleResponse)
        }
    }

    private fun handleResponse(moneybox: Float) {
        tv_moneybox.text =
            resources.getString(R.string.moneybox) + resources.getString(R.string.pounds) + moneybox.toString()
    }

}
