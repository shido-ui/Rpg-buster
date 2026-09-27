package com.rpgaihub.app.presentation.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes

val RpgShapes = Shapes(
    extraSmall = RoundedCornerShape(Dimensions.cornerSmall / 2),
    small = RoundedCornerShape(Dimensions.cornerSmall),
    medium = RoundedCornerShape(Dimensions.cornerMedium),
    large = RoundedCornerShape(Dimensions.cornerLarge),
    extraLarge = RoundedCornerShape(Dimensions.cornerPill)
)
