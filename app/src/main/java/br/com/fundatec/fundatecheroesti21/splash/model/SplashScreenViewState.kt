package br.com.fundatec.fundatecheroesti21.splash.model

sealed class SplashScreenViewState {
    object ShowHome : SplashScreenViewState()
    object ShowLogin : SplashScreenViewState()
}