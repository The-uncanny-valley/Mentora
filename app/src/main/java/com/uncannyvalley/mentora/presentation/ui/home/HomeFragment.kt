package com.uncannyvalley.mentora.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.uncannyvalley.mentora.R
import com.uncannyvalley.mentora.presentation.adapter.CourseAdapter
import com.uncannyvalley.mentora.presentation.viewmodel.CourseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private val viewModel: CourseViewModel by viewModel()
    private lateinit var adapter: CourseAdapter

    private var sortDescending = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout
        return inflater.inflate(
            R.layout.fragment_home,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_courses)
        val btnSort = view.findViewById<MaterialButton>(R.id.btn_sort)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = CourseAdapter(
            // TODO: handle learn more
            onLearnMoreClick = { course -> {} },
            onFavouriteClick = { course ->
                viewModel.toggleLike(course)
            }
        )

        recyclerView.adapter = adapter

        viewModel.courses.observe(viewLifecycleOwner) { courses ->
            val display = if (sortDescending) {
                courses.sortedByDescending { it.publishDate }
            } else {
                courses
            }
            adapter.submitList(display)
        }

        btnSort?.setOnClickListener {
            sortDescending = !sortDescending
            viewModel.courses.value?.let { courses ->

                val sorted = if (sortDescending) {
                    courses.sortedByDescending { it.publishDate }
                } else {
                    courses.sortedBy { it.publishDate }
                }
                // Force list refresh
                adapter.submitList(null) {
                    adapter.submitList(sorted)
                }
            }
        }
        viewModel.loadCourses()
    }
}