package com.sebqv97.virginmediachallenge.util

sealed class UiState{
    class Success<T>(val data :T) : UiState()
    class Failure<T>(val message:T):UiState()
    object Loading : UiState()
}
