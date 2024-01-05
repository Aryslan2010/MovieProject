package com.example.newproject
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.Rotate
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


class ImagesActivity : AppCompatActivity() {
        private val recyclerView: RecyclerView by lazy { findViewById(R.id.recycler_item) }

        override fun onStart() {
            super.onStart()
            val profileName = intent?.getStringArrayListExtra("listDownload")
            val listproverka2: List<String> = profileName?.toList() ?: listOf("","")
            val adapter: AdapterView1 = AdapterView1(listproverka2)
            recyclerView.adapter = adapter
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.recycler_activ)


        }

        class AdapterView1(
            private val imagePhoneList: List<String>
        ) : RecyclerView.Adapter<AdapterView1.ViewHolder>() {
            override fun getItemCount(): Int = imagePhoneList.size
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.text_images, parent, false)
                return ViewHolder(view)
            }

            override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                val imageURL = imagePhoneList[position]
                holder.bind(imageURL)
            }

            class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
                fun bind(imageURL: String) {

                    itemView.findViewById<TextView>(R.id.text_images_view).text = imageURL
                    val imageView = itemView.findViewById<ImageView>(R.id.poster_image_view)
                    Glide.with(imageView)
                        .load(Uri.parse("https://image.tmdb.org/t/p/original/${imageURL}"))
                        .fitCenter()
                        .apply(RequestOptions().transform(CenterCrop(), Rotate(270)))
                        .into(imageView)
                }
            }
        }

    }
