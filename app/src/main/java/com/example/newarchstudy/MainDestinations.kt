package com.example.newarchstudy

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface MainDestinations {
    val route: String
}

object LatestNews : MainDestinations {
    override val route = "latestNews"



    val routeWithArgs = "${route}/"
}


object DetailHeadlines : MainDestinations {
    override val route = "detailedNews"
    const val arg_name  = "arg_name"
    const val arg_image = "arg_image"
    const val arg_description = "arg_description"
    const val arg_url = "arg_url"


    val arguments = listOf(
        navArgument(arg_name) { type = NavType.StringType },
        navArgument(arg_image) { type = NavType.StringType },
        navArgument(arg_description) { type = NavType.StringType },
        navArgument(arg_url) { type = NavType.StringType }
    )

    val routeWithArgs = "${route}/{${arg_description}}/{${arg_image}}/{${arg_name}}/{${arg_url}}"
}