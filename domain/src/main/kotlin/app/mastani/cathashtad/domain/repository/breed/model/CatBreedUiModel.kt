package app.mastani.cathashtad.domain.repository.breed.model

data class CatBreedUiModel(
    val id: String,
    val name: String,
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
) {
    companion object {
        val PREVIEW = CatBreedUiModel(
            id = "esho",
            name = "Exotic Shorthair",
            description = "The Exotic Shorthair is a gentle friendly cat that has the same personality as the Persian. They love having fun, don't mind the company of other cats and dogs, also love to curl up for a sleep in a safe place. Exotics love their own people, but around strangers they are cautious at first. Given time, they usually warm up to visitors.",
            wikipediaUrl = "https://en.wikipedia.org/wiki/Exotic_Shorthair",
            cfaUrl = "http://cfa.org/Breeds/BreedsCJ/Exotic.aspx",
            vetstreetUrl = "http://www.vetstreet.com/cats/exotic-shorthair",
            vcahospitalsUrl = "https://vcahospitals.com/know-your-pet/cat-breeds/exotic-shorthair",
            temperament = "Affectionate, Sweet, Loyal, Quiet, Peaceful",
            origin = "United States",
            countryCode = "US",
            lifeSpan = "12 - 15",
            indoor = 0,
            lap = 1,
            altNames = "Exotic",
            adaptability = 5,
            affectionLevel = 5,
            childFriendly = 3,
            dogFriendly = 3,
            energyLevel = 3,
            grooming = 2,
            healthIssues = 3,
            intelligence = 3,
            sheddingLevel = 2,
            socialNeeds = 4,
            strangerFriendly = 2,
            vocalisation = 1,
            experimental = 0,
            hairless = 0,
            natural = 0,
            rare = 0,
            rex = 0,
            suppressedTail = 0,
            shortLegs = 0,
            hypoallergenic = 0
        )
    }
}