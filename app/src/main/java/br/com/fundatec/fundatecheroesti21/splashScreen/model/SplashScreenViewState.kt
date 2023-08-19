package br.com.fundatec.fundatecheroesti21.splashScreen.model

sealed class SplashScreenViewState {
    object ShowHome : SplashScreenViewState()
    object ShowLogin : SplashScreenViewState()
}