package com.example.android.weatherapp.presentation.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import com.example.android.weatherapp.domain.entity.City
import com.example.android.weatherapp.presentation.details.DefaultDetailsComponent
import com.example.android.weatherapp.presentation.favourite.DefaultFavouriteComponent
import com.example.android.weatherapp.presentation.search.DefaultSearchComponent
import com.example.android.weatherapp.presentation.search.OpenReason
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.serialization.Serializable

class DefaultRootComponent @AssistedInject constructor(
    private val favouriteComponentFactory: DefaultFavouriteComponent.Factory,
    private val detailsComponentFactory: DefaultDetailsComponent.Factory,
    private val searchComponentFactory: DefaultSearchComponent.Factory,
    @Assisted("componentContext") componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val childStack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.Favourite,
        handleBackButton = true,
        childFactory = ::createChild
    )

    private fun createChild(
        config: Config,
        componentContext: ComponentContext
    ): RootComponent.Child {
        return when (config) {
            is Config.Details -> {
                val component = detailsComponentFactory.create(
                    city = config.city,
                    onBackClicked = {
                        navigation.pop()
                    },
                    componentContext = componentContext
                )
                RootComponent.Child.Details(component)
            }

            is Config.Favourite -> {
                val component = favouriteComponentFactory.create(
                    onCityItemClicked = {
                        navigation.pushNew(Config.Details(it))
                    },
                    onAddFavouriteClicked = {
                        navigation.pushNew(Config.Search(OpenReason.AddToFavourite))
                    },
                    onSearchClicked = {
                        navigation.pushNew(Config.Search(OpenReason.RegularSearch))
                    },
                    componentContext = componentContext
                )
                RootComponent.Child.Favourite(component)
            }

            is Config.Search -> {
                val component = searchComponentFactory.create(
                    openReason = config.openReason,
                    onBackClicked = {
                        navigation.pop()
                    },
                    onCitySavedToFavourite = {
                        navigation.pop()
                    },
                    onForecastForCityRequested = {
                        navigation.pushNew(Config.Details(it))
                    },
                    componentContext = componentContext
                )
                RootComponent.Child.Search(component)
            }
        }
    }

    @Serializable
    sealed interface Config {

        @Serializable
        data object Favourite : Config

        @Serializable
        data class Search(val openReason: OpenReason) : Config

        @Serializable
        data class Details(val city: City) : Config
    }

    @AssistedFactory
    interface Factory {

        fun create(
            @Assisted("componentContext") componentContext: ComponentContext
        ): DefaultRootComponent
    }
}