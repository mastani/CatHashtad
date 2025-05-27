package app.mastani.cathashtad.data.datasource.remote.breed.model

import com.google.gson.annotations.SerializedName

data class CatBreedDto(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String? = null,
    @SerializedName("cfa_url") val cfaUrl: String? = null,
    @SerializedName("vetstreet_url") val vetstreetUrl: String? = null,
    @SerializedName("vcahospitals_url") val vcahospitalsUrl: String? = null,
    @SerializedName("temperament") val temperament: String? = null,
    @SerializedName("origin") val origin: String? = null,
    @SerializedName("country_codes") val countryCodes: String? = null,
    @SerializedName("country_code") val countryCode: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("life_span") val lifeSpan: String? = null,
    @SerializedName("indoor") val indoor: Int? = null,
    @SerializedName("lap") val lap: Int? = null,
    @SerializedName("alt_names") val altNames: String? = null,
    @SerializedName("adaptability") val adaptability: Int? = null,
    @SerializedName("affection_level") val affectionLevel: Int? = null,
    @SerializedName("child_friendly") val childFriendly: Int? = null,
    @SerializedName("dog_friendly") val dogFriendly: Int? = null,
    @SerializedName("energy_level") val energyLevel: Int? = null,
    @SerializedName("grooming") val grooming: Int? = null,
    @SerializedName("health_issues") val healthIssues: Int? = null,
    @SerializedName("intelligence") val intelligence: Int? = null,
    @SerializedName("shedding_level") val sheddingLevel: Int? = null,
    @SerializedName("social_needs") val socialNeeds: Int? = null,
    @SerializedName("stranger_friendly") val strangerFriendly: Int? = null,
    @SerializedName("vocalisation") val vocalisation: Int? = null,
    @SerializedName("experimental") val experimental: Int? = null,
    @SerializedName("hairless") val hairless: Int? = null,
    @SerializedName("natural") val natural: Int? = null,
    @SerializedName("rare") val rare: Int? = null,
    @SerializedName("rex") val rex: Int? = null,
    @SerializedName("suppressed_tail") val suppressedTail: Int? = null,
    @SerializedName("short_legs") val shortLegs: Int? = null,
    @SerializedName("wikipedia_url") val wikipediaUrl: String? = null,
    @SerializedName("hypoallergenic") val hypoallergenic: Int? = null,
    @SerializedName("image") val image: BreedImage? = null
)

data class BreedImage(
    @SerializedName("width") val width: Int? = null,
    @SerializedName("height") val height: Int? = null,
    @SerializedName("url") val url: String? = null,
)