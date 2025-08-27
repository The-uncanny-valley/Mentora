package com.uncannyvalley.mentora.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uncannyvalley.mentora.domain.model.Course
import com.uncannyvalley.mentora.domain.repository.CourseRepository
import com.uncannyvalley.mentora.domain.usecase.GetCoursesUseCase
import kotlinx.coroutines.launch

class CourseViewModel(
    private val getCoursesUseCase: GetCoursesUseCase,
    private val repository: CourseRepository
) : ViewModel() {

    private val _courses = MutableLiveData<List<Course>>()
    val courses: LiveData<List<Course>> = _courses

    fun loadCourses() {
        viewModelScope.launch {
            try {
                val data = getCoursesUseCase()
                _courses.value = data
            } catch (e: Exception) {
                // handle error
            }
        }
    }

    fun toggleLike(course: Course) {
        repository.toggleLike(course.id)
        
        val currentList = _courses.value ?: return
        val updatedList = currentList.map {
            if (it.id == course.id) it.copy(hasLike = !it.hasLike) else it
        }
        _courses.value = updatedList  // LiveData updates -> Adapter redraws
        Log.d("CourseVM", "Toggled like for ${course.id}, new state = ${!course.hasLike}")
    }
}