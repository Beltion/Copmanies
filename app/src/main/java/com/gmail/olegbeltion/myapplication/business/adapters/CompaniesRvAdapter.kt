package com.gmail.olegbeltion.myapplication.business.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gmail.olegbeltion.core.business.entities.Company
import com.gmail.olegbeltion.myapplication.R
import com.gmail.olegbeltion.myapplication.framework.Common

class CompaniesRvAdapter(
    private val companies: ArrayList<Company>,
    private val listener: OnCompaniesRvAdapterListener
): RecyclerView.Adapter<CompaniesRvAdapter.CompanyVh>() {

    class CompanyVh(view: View): RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.name_company_item)
        val img = view.findViewById<ImageView>(R.id.img_company_item)

        fun onItemClick(companyID: Int, listener: OnCompaniesRvAdapterListener){
            itemView.setOnClickListener {
                listener.onItemClick(companyID)
            }
        }

    }

    interface OnCompaniesRvAdapterListener {
        fun onItemClick(companyID: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyVh {
        val ctx = parent.context
        val inflater = LayoutInflater.from(ctx)
        val card = inflater.inflate(R.layout.company_item, parent, false)
        return CompanyVh(card)
    }

    override fun onBindViewHolder(holder: CompanyVh, position: Int) {
        holder.name.text = companies[position].name
        val imgUrl = Common.BASE_URL + companies[position].img

        Glide.with(holder.itemView.context).asDrawable()
            .load(imgUrl)
            .override(300, 300)
            .fitCenter()
            .placeholder(R.drawable.ic_baseline_corporate)
            .error(R.drawable.ic_baseline_corporate)
            .into(holder.img)

        holder.onItemClick(companies[position].id, listener)
    }

    override fun getItemCount(): Int = companies.size


}