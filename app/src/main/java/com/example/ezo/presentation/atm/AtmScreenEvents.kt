package com.example.ezo.presentation.atm

sealed class AtmScreenEvents {

    data class AmountDeposited(
        val noOf2000Notes : Long = 0,
        val noOf500Notes : Long = 0,
        val noOf200Notes : Long = 0,
        val noOf100Notes : Long = 0
    ) : AtmScreenEvents()

    data class AmountWithdrawal(
        val amount : Long
    ) : AtmScreenEvents()

}