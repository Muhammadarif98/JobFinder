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
    val title: String,
    val company: String,
    val address: Address,
    val experience: Experience,
    val publishedDate: String,
    val isFavorite: Boolean
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