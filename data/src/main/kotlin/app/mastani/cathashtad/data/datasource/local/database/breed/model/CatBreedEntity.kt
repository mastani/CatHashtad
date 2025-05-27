package app.mastani.cathashtad.data.datasource.local.database.breed.model

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CAT_BREEDS_TABLE_NAME = "cat_breeds"

@Entity(tableName = CAT_BREEDS_TABLE_NAME)
data class CatBreedEntity(
    @PrimaryKey
    val id: String,
    val name: String?,
    val description: String? = null,
    val wikipediaUrl: String? = null,
    val cfaUrl: String? = null,
    val vetstreetUrl: String? = null,
    val vcahospitalsUrl: String? = null,
    val temperament: String? = null,
    val origin: String? = null,
    val countryCode: String? = null,
    val lifeSpan: String? = null,
    val indoor: Int? = null,
    val lap: Int? = null,
    val altNames: String? = null,
    val adaptability: Int? = null,
    val affectionLevel: Int? = null,
    val childFriendly: Int? = null,
    val dogFriendly: Int? = null,
    val energyLevel: Int? = null,
    val grooming: Int? = null,
    val healthIssues: Int? = null,
    val intelligence: Int? = null,
    val sheddingLevel: Int? = null,
    val socialNeeds: Int? = null,
    val strangerFriendly: Int? = null,
    val vocalisation: Int? = null,
    val experimental: Int? = null,
    val hairless: Int? = null,
    val natural: Int? = null,
    val rare: Int? = null,
    val rex: Int? = null,
    val suppressedTail: Int? = null,
    val shortLegs: Int? = null,
    val hypoallergenic: Int? = null,
    val imageWidth: Int? = null,
    val imageHeight: Int? = null,
    val imageUrl: String? = null
)