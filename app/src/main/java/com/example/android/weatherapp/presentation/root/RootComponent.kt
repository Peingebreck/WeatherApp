package com.example.android.weatherapp.presentation.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.example.android.weatherapp.presentation.details.DetailsComponent
import com.example.android.weatherapp.presentation.favourite.FavouriteComponent
import com.example.android.weatherapp.presentation.search.SearchComponent

interface RootComponent {

    val childStack: Value<ChildStack<*, Child>>

    sealed interface Child {

        data class Favourite(val component: FavouriteComponent) : Child

        data class Details(val component: DetailsComponent) : Child

        data class Search(val component: SearchComponent) : Child
    }
}