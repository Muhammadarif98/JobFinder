package com.example.domain.model

data class ApiResponse(
    val offers: List<Offer>,
    val vacancies: List<Vacancy>
)

data class Offer(
    val id: String,
    val title: String,
    val link: String,
    val button: Button?
)

data class Button(
    val text: String
)

data class Vacancy(
    val id: String,
    val lookingNumber: Int?, // Количество просматривающих (может быть null)
    val title: String,
    val address: Address,
    val company: String,
    val experience: Experience,
    val publishedDate: String,
    val isFavorite: Boolean,
    val salary: Salary,
    val schedules: List<String>,
    val appliedNumber: Int?, // Количество откликов (может быть null)
    val description: String,
    val responsibilities: String,
    val questions: List<String?>
)

data class Address(
    val town: String,
    val street: String? = null,
    val house: String? = null
)

data class Experience(
    val previewText: String,
    val text: String
)

data class Salary(
    val short: String?, // Краткое описание зарплаты (может быть null)
    val full: String // Полное описание зарплаты
)