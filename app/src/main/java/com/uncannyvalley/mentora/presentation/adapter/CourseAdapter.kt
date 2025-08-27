package com.uncannyvalley.mentora.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.uncannyvalley.mentora.R
import com.uncannyvalley.mentora.domain.model.Course
import java.time.format.DateTimeFormatter

class CourseAdapter(
    private val onLearnMoreClick: (Course) -> Unit,
    private val onFavouriteClick: (Course) -> Unit
) : ListAdapter<Course, CourseAdapter.CourseViewHolder>(DiffCallback()) {

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageCover: ImageView = itemView.findViewById(R.id.image_view_course_cover)
        private val btnFavourite: ImageButton = itemView.findViewById(R.id.btn_favourite)
        private val rateText: TextView = itemView.findViewById(R.id.text_view_course_rate)
        private val dateText: TextView = itemView.findViewById(R.id.text_view_date)
        private val titleText: TextView = itemView.findViewById(R.id.text_view_course_title)
        private val descriptionText: TextView = itemView.findViewById(R.id.text_view_course_description)
        private val priceText: TextView = itemView.findViewById(R.id.text_view_course_price)
        private val btnLearnMore: LinearLayout = itemView.findViewById(R.id.btn_learn_more)

        fun bind(
            course: Course,
            onLearnMoreClick: (Course) -> Unit,
            onFavouriteClick: (Course) -> Unit
        ) {
            // TODO: Load image
            imageCover.setImageResource(R.drawable.placeholder)

            rateText.text = course.rate.toString()
            dateText.text = course.startDate.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"))
            titleText.text = course.title
            descriptionText.text = course.description
            priceText.text = if (course.price == 0) "Free" else "${course.price} ₽"

            // Set bookmark icon
            val iconRes = if (course.hasLike) {
                R.drawable.ic_bookmark_saved
            } else {
                R.drawable.ic_bookmark
            }
            btnFavourite.setImageResource(iconRes)
            Log.d("CourseAdapter", "Binding course ${course.id} with hasLike=${course.hasLike}")

            btnLearnMore.setOnClickListener { onLearnMoreClick(course) }
            btnFavourite.setOnClickListener { onFavouriteClick(course) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Course>() {
        override fun areItemsTheSame(oldItem: Course, newItem: Course) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Course, newItem: Course) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_course, parent, false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(getItem(position), onLearnMoreClick, onFavouriteClick)
    }
}