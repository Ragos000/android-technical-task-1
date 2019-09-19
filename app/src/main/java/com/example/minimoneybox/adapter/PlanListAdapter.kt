package com.example.minimoneybox.adapter

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.minimoneybox.R
import com.example.minimoneybox.model.UserPlan

class PlanListAdapter(
    private var userPlans: ArrayList<UserPlan>,
    private val clickListener: (UserPlan) -> Unit
) : RecyclerView.Adapter<PlanListAdapter.PlanViewHolder>() {

    class PlanViewHolder(val constraintLayout: ConstraintLayout) : RecyclerView.ViewHolder(constraintLayout){
        fun bind(userPlan: UserPlan, clickListener: (UserPlan) -> Unit) {
            (constraintLayout.getViewById(R.id.tv_plan_name) as TextView).text = userPlan.name
            (constraintLayout.getViewById(R.id.tv_plan_value) as TextView).text = userPlan.planValue
            (constraintLayout.getViewById(R.id.tv_moneybox) as TextView).text = userPlan.moneyBox
            constraintLayout.setOnClickListener { clickListener(userPlan)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): PlanViewHolder {

        val constraintLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.plan_list, parent, false) as ConstraintLayout

        return PlanViewHolder(constraintLayout)
    }

    override fun onBindViewHolder(holder: PlanViewHolder, position: Int) {
        holder.bind(userPlans.get(position), clickListener)
    }

    override fun getItemCount() = userPlans.size

    public fun refreshDataset(userPlans: ArrayList<UserPlan>){
        this.userPlans = userPlans
        notifyDataSetChanged()
    }
}