package com.example.ncltest.models

import kotlinx.serialization.Serializable

@Serializable
data class ShipResponse(
	val code: String? = null,
	val stateroomMetas: List<StateroomMetasItem?>? = null,
	val recategorizationDate: String? = null,
	val shipFacts: ShipFacts? = null,
	val imagePath: List<String?>? = null,
	val wesbCode: String? = null,
	val allowedGuestCount: Int? = null,
	val shipName: String? = null,
	val recategorizationDefaultView: String? = null,
	val shipDescriptionHtml: String? = null,
	val whatsIncluded: List<String?>? = null,
	val features: String? = null,
	val legends: List<LegendsItem?>? = null,
	val highlights: List<String?>? = null,
	val accessCode: String? = null,
	val bgeImagePath: String? = null,
	val name: String? = null,
	val onboardPhones: List<OnboardPhonesItem?>? = null,
	val shipDescription: String? = null
) {
    val formattedShipName: String
        get() = "Ship Name: $shipName"

    val formattedPaxCapacity: String
        get() = "Passenger Capacity: ${shipFacts?.passengerCapacity}"

    val formattedCrew: String
        get() = "Crew: ${shipFacts?.crew}"

    val formattedInauguralDate: String
        get() = "Inaugural Date: ${shipFacts?.inauguralDate}"
}

@Serializable
data class OnboardPhonesItem(
	val phone: String? = null,
	val name: String? = null,
	val locationSharingEnabled: Boolean? = null,
	val venueCode: String? = null,
	val mobileName: String? = null,
	val token: String? = null
)

@Serializable
data class OverviewImage(
	val imagePath: String? = null,
	val alt: String? = null,
	val title: String? = null
)

@Serializable
data class StateroomMetasItem(
	val code: String? = null,
	val stateroomSubMetas: List<StateroomSubMetasItem?>? = null,
	val genericCode: String? = null,
	val imagePath: List<String?>? = null,
	val featureHighlights: String? = null,
	val description: String? = null,
	val weight: String? = null,
	val overviewImage: OverviewImage? = null,
	val shortDescription: String? = null,
	val categorizationVersion: String? = null,
	val includedFeatures: List<String?>? = null,
	val accessCode: String? = null,
	val name: String? = null,
	val featuresHighlights: String? = null,
	val thumbnailImage: String? = null
)

@Serializable
data class ImageLinksItem(
	val imageLink: String? = null,
	val imageHeadLine: String? = null,
	val legendWeight: String? = null
)

@Serializable
data class StateroomSubMetasItem(
	val code: String? = null,
	val occupancy: String? = null,
	val description: String? = null,
	val balconySize: String? = null,
	val body: String? = null,
	val imageLinks: List<ImageLinksItem?>? = null,
	val guaranteeMessage: String? = null,
	val approximateSize: String? = null,
	val featuresAndHighlights: String? = null,
	val marketingTagLine: String? = null,
	val stateroomCategories: List<StateroomCategoriesItem?>? = null,
	val accessCode: String? = null,
	val floorPlanLink: String? = null,
	val name: String? = null,
	val thumbnailImage: String? = null
)

@Serializable
data class LegendsItem(
	val legendImageLink: String? = null,
	val name: String? = null,
	val description: String? = null,
	val legendWeight: String? = null
)

@Serializable
data class StateroomCategoriesItem(
	val colorSwatch: String? = null,
	val code: String? = null,
	val mandatoryCabin: Boolean? = null,
	val name: String? = null,
	val positionInShip: String? = null,
	val comment: String? = null,
	val decks: String? = null,
	val categoryID: String? = null
)

@Serializable
data class ShipFacts(
	val passengerCapacity: String? = null,
	val overallLength: String? = null,
	val cruiseSpeed: String? = null,
	val maxBeam: String? = null,
	val engines: String? = null,
	val remodeledDate: String? = null,
	val draft: String? = null,
	val inauguralDate: String? = null,
	val grossRegisterTonnage: String? = null,
	val crew: String? = null
)

