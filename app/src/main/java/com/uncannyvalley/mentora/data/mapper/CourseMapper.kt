package com.uncannyvalley.mentora.data.mapper

import com.uncannyvalley.mentora.data.api.CourseDto
import com.uncannyvalley.mentora.data.db.CourseEntity
import com.uncannyvalley.mentora.domain.model.Course
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

fun CourseDto.toDomain(): Course {
    return Course(
        id = id,
        title = title,
        description = text,
        price = price.replace(" ", "").toIntOrNull() ?: 0,
        rate = rate.toDoubleOrNull() ?: 0.0,
        startDate = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE),
        hasLike = hasLike,
        publishDate = LocalDate.parse(publishDate, DateTimeFormatter.ISO_DATE)
    )
}

fun Course.toEntity(): CourseEntity {
    return CourseEntity(
        id = id,
        title = title,
        text = description,
        price = price,
        rate = rate,
        startDate = Date.from(
            startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()
        ),
        hasLike = hasLike,
        publishDate = Date.from(
            publishDate.atStartOfDay(ZoneId.systemDefault()).toInstant()
        )
    )
}

fun CourseEntity.toDomain(): Course {
    return Course(
        id = id,
        title = title,
        description = text,
        price = price,
        rate = rate,
        startDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
        hasLike = hasLike,
        publishDate = publishDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    )
}

fun CourseDto.toEntity(): CourseEntity {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val start = LocalDate.parse(startDate, formatter)
    val publish = LocalDate.parse(publishDate, formatter)

    return CourseEntity(
        id = id,
        title = title,
        text = text,
        price = price.replace(" ", "").toIntOrNull() ?: 0,
        rate = rate.toDoubleOrNull() ?: 0.0,
        startDate = Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant()),
        hasLike = hasLike,
        publishDate = Date.from(publish.atStartOfDay(ZoneId.systemDefault()).toInstant())
    )
}