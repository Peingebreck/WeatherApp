package com.example.android.weatherapp.presentation.root

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.example.android.weatherapp.presentation.details.DetailsContent
import com.example.android.weatherapp.presentation.favourite.FavouriteContent
import com.example.android.weatherapp.presentation.search.SearchContent
import com.example.android.weatherapp.presentation.ui.theme.WeatherAppTheme

@Composable
fun RootContent(component: RootComponent) {

    WeatherAppTheme {
        Children(
            stack = component.childStack
        ) {
            when (val instance = it.instance) {
                is RootComponent.Child.Details -> {
                    DetailsContent(component = instance.component)
                }

                is RootComponent.Child.Favourite -> {
                    FavouriteContent(component = instance.component)
                }

                is RootComponent.Child.Search -> {
                    SearchContent(component = instance.component)
                }
            }
        }
    }
}