package com.merlin.training_mvvm.domain.models

data class Incident(
    val address: String = "",
    val description: String = "",
    val id: Int = 0,
    val location_description: String = "",
    val location_type: String = "",
    val media: Media = Media(),
    val occurred_at: Int = 0,
    val source: Source = Source(),
    val title: String = "",
    val type: String = "",
    val type_properties: String = "",
    val updated_at: Int = 0,
    val url: String = ""
)

data class Source(
    val api_url: String = "",
    val html_url: String = "",
    val name: String = ""
)


data class Media(
    val image_url: String = "",
    val image_url_thumb: String = ""
)

data class MovieModel(
    val incidents: List<Incident>
)