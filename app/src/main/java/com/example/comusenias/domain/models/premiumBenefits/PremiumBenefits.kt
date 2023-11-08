package com.example.comusenias.domain.models.premiumBenefits

import com.example.comusenias.R
import com.example.comusenias.R.drawable.game
import com.example.comusenias.R.drawable.not_ads
import com.example.comusenias.R.drawable.premium
import com.example.comusenias.R.drawable.specialist
import com.example.comusenias.presentation.ui.theme.PREMIUM_FOUR_DESCRIPTION
import com.example.comusenias.presentation.ui.theme.PREMIUM_FOUR_TITLE
import com.example.comusenias.presentation.ui.theme.PREMIUM_ONE_DESCRIPTION
import com.example.comusenias.presentation.ui.theme.PREMIUM_ONE_TITLE
import com.example.comusenias.presentation.ui.theme.PREMIUM_THREE_DESCRIPTION
import com.example.comusenias.presentation.ui.theme.PREMIUM_THREE_TITLE
import com.example.comusenias.presentation.ui.theme.PREMIUM_TWO_DESCRIPTION
import com.example.comusenias.presentation.ui.theme.PREMIUM_TWO_TITLE

class PremiumBenefits (
    val title:String,
    val text: String,
    val image: Int,
) {
    companion object {
        fun get(): List<PremiumBenefits> {
            return listOf(
                PremiumBenefits(
                    title = PREMIUM_ONE_TITLE,
                    text = PREMIUM_ONE_DESCRIPTION,
                    image = game
                ),
                PremiumBenefits(
                    title = PREMIUM_TWO_TITLE,
                    text  = PREMIUM_TWO_DESCRIPTION,
                    image = specialist
                ),
                PremiumBenefits(
                    title = PREMIUM_THREE_TITLE,
                    text  = PREMIUM_THREE_DESCRIPTION,
                    image = not_ads
                ),
                PremiumBenefits(
                    title = PREMIUM_FOUR_TITLE,
                    text  = PREMIUM_FOUR_DESCRIPTION,
                    image = premium
                )
            )
        }
    }
}