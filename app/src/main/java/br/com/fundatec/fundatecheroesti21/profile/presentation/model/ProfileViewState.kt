package br.com.fundatec.fundatecheroesti21.profile.presentation.model

import br.com.fundatec.fundatecheroesti21.login.presentation.model.LoginViewState

sealed class ProfileViewState {

    object ShowHomeScreen : ProfileViewState()

    object ShowNameErrorMessage : ProfileViewState()

    object ShowEmailErrorMessage : ProfileViewState()

    object ShowPasswordErrorMessage : ProfileViewState()

    object ShowEmailPasswordErrorMessage : ProfileViewState()

    object ShowLoading : ProfileViewState()

    object ShowErrorMessage : ProfileViewState()
}