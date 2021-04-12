package com.example.task1autodiary.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task1autodiary.R
import com.example.task1autodiary.data.CarListItem
import kotlinx.android.synthetic.main.item_car.view.*

class CarListAdapter(var cars:List<CarListItem>) :
    RecyclerView.Adapter<CarListAdapter.CarsViewHolder>(){

    inner class CarsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_car,parent,false)
        return CarsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarsViewHolder, position: Int) {
        holder.itemView.apply {
            tv_brand.text = cars[position].brand
            tv_model.text = cars[position].model
            tv_year.text = cars[position].year.toString()
            iv_carImage.setImageDrawable(cars[position].image)
        }
    }

    override fun getItemCount(): Int {
        return cars.size
    }
}