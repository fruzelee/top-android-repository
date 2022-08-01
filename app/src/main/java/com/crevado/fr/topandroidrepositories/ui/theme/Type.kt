package com.crevado.fr.topandroidrepositories.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.crevado.fr.topandroidrepositories.R

// Set of Material typography styles to start with
val MyCustomFont = FontFamily(
    Font(R.font.font_jost),
    Font(R.font.font_jost_bold, FontWeight.Bold)
)

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = MyCustomFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
// Other default text styles to override
    button = TextStyle(
        fontFamily = MyCustomFont,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = MyCustomFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    h1 = TextStyle(
        fontFamily = MyCustomFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp
    ),
    h2 = TextStyle(
        fontFamily = MyCustomFont,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ),
    h3 = TextStyle(
        fontFamily = MyCustomFont,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    h4 = TextStyle(
        fontFamily = MyCustomFont,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    h5 = TextStyle(
        fontFamily = MyCustomFont,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    h6 = TextStyle(
        fontFamily = MyCustomFont,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    )

)
