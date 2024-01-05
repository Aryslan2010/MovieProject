package com.example.newproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterView(private val imagePhoneList: List<String>
): RecyclerView.Adapter<AdapterView.ViewHolder>(){
    override fun getItemCount(): Int = imagePhoneList.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view = LayoutInflater.
        from(parent.context)
            .inflate(R.layout.text_images,parent,false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder,position: Int){
        val imageURL = imagePhoneList[position]
        holder.bind(imageURL)
    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(imageURL: String){
            itemView.findViewById<TextView>(R.id.text_images_view).text = imageURL

        }
    }
}

/*val imageView = itemView.findViewById<ImageView>(R.id.imageView)
            Glide.with(imageView)
                .load(imageURL)
                .into(imageView)*/