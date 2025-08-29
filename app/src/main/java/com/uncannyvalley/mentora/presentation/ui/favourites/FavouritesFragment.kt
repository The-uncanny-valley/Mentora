package com.uncannyvalley.mentora.presentation.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uncannyvalley.mentora.R
import com.uncannyvalley.mentora.presentation.adapter.CourseAdapter
import com.uncannyvalley.mentora.presentation.viewmodel.CourseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouritesFragment : Fragment() {

    private val viewModel: CourseViewModel by viewModel()
    private lateinit var adapter: CourseAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.fragment_favourites,
            container,
            false
        )
        recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view_favourites)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = CourseAdapter(
            onLearnMoreClick = { course -> {} },
            onFavouriteClick = { course -> viewModel.toggleLike(course) }
        )

        recyclerView.adapter = adapter

        viewModel.courses.observe(viewLifecycleOwner) { courses ->
            val likedCourses = courses.filter { it.hasLike }
            adapter.submitList(likedCourses)
        }

        viewModel.loadCourses()
    }
}