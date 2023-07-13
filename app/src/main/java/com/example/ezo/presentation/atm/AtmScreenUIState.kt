package com.example.ezo.presentation.atm

data class AtmScreenUIState(
    var totalAmount: Long = 0,
    var denomination2000: Denomination = Denomination("2000", 0, 0),
    var denomination500: Denomination = Denomination("500", 0, 0),
    var denomination200: Denomination = Denomination("200", 0, 0),
    var denomination100: Denomination = Denomination("100", 0, 0),
    var logMsg : MutableList<String> = mutableListOf<String>()
)

data class Denomination(
    var note: String = "",
    var noOfNotes: Long = 0,
    var totalAmount: Long = 0,
)