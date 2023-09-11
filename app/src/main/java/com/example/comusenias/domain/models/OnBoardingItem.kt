package com.example.comusenias.domain.models

import com.example.comusenias.R

class OnBoardingItem(
        val text:Int,
        val image:Int,
    ) {
    companion object{
            fun get():List<OnBoardingItem>{
                return listOf(
                    OnBoardingItem(R.string.onBoardingText1,R.drawable.onboarding1),
                    OnBoardingItem(R.string.onBoardingText2,R.drawable.onboarding2),
                    OnBoardingItem(R.string.onBoardingText3,R.drawable.onboarding3),
                )
            }
        }
}