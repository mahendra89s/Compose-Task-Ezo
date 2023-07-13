package com.example.ezo.presentation.atm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AtmScreenViewModel : ViewModel() {

    private val _state: MutableStateFlow<AtmScreenUIState> = MutableStateFlow(AtmScreenUIState())
    val state: StateFlow<AtmScreenUIState> = _state.asStateFlow()

    fun handleEvent(
        events: AtmScreenEvents
    ) {
        when (events) {
            is AtmScreenEvents.AmountDeposited -> {
                depositMoney(
                    noOf2000Notes = events.noOf2000Notes,
                    noOf500Notes = events.noOf500Notes,
                    noOf200Notes = events.noOf200Notes,
                    noOf100Notes = events.noOf100Notes,
                )
            }

            is AtmScreenEvents.AmountWithdrawal -> {
                withDrawMoney(events.amount)
            }
        }
    }

    private fun depositMoney(
        noOf2000Notes: Long = 0,
        noOf500Notes: Long = 0,
        noOf200Notes: Long = 0,
        noOf100Notes: Long = 0
    ) {
        var data = _state.value
        data = data.copy(
            denomination2000 = Denomination(
                note = data.denomination2000.note,
                noOfNotes = data.denomination2000.noOfNotes + noOf2000Notes,
                totalAmount = (data.denomination2000.noOfNotes + noOf2000Notes) * 2000
            ),
            denomination500 = Denomination(
                note = data.denomination500.note,
                noOfNotes = data.denomination500.noOfNotes + noOf500Notes,
                totalAmount = (data.denomination500.noOfNotes + noOf500Notes) * 500
            ),
            denomination200 = Denomination(
                note = data.denomination200.note,
                noOfNotes = data.denomination200.noOfNotes + noOf200Notes,
                totalAmount = (data.denomination200.noOfNotes + noOf200Notes) * 200
            ),
            denomination100 = Denomination(
                note = data.denomination100.note,
                noOfNotes = data.denomination100.noOfNotes + noOf100Notes,
                totalAmount = (data.denomination100.noOfNotes + noOf100Notes) * 100
            ),
        )
        data = data.copy(
            totalAmount = data.denomination2000.totalAmount + data.denomination500.totalAmount + data.denomination200.totalAmount + data.denomination100.totalAmount
        )
        val logMsg = data.logMsg
        logMsg.add(0,"Amount deposited\nAvailable Denomination :" +
                " ${data.denomination2000.note} = ${data.denomination2000.noOfNotes} ," +
                " ${data.denomination500.note} = ${data.denomination500.noOfNotes} ," +
                " ${data.denomination200.note} = ${data.denomination200.noOfNotes} ," +
                " ${data.denomination100.note} = ${data.denomination100.noOfNotes}" )
        _state.value = data.copy(
            logMsg = logMsg
        )
    }

    private fun withDrawMoney(
        amt: Long
    ) {
        var amount = amt
        var data = AtmScreenUIState()
        data = data.copy(
            totalAmount = _state.value.totalAmount,
            denomination2000 = _state.value.denomination2000,
            denomination500 = _state.value.denomination500,
            denomination200 = _state.value.denomination200,
            denomination100 = _state.value.denomination100,
            logMsg = _state.value.logMsg
        )

        if(amount > data.totalAmount){
            return
        }
        val data1 = recursion(data.denomination2000,amount)
        amount = data1.first
        data.denomination2000 = data1.second

        val data2 = recursion(data.denomination500,amount)
        amount = data2.first
        data.denomination500 = data2.second

        val data3 = recursion(data.denomination200,amount)
        amount = data3.first
        data.denomination200 = data3.second

        val data4 = recursion(data.denomination100,amount)
        amount = data4.first
        data.denomination100 = data4.second

        data.totalAmount = data.denomination2000.noOfNotes*2000 + data.denomination500.noOfNotes*500+ data.denomination200.noOfNotes*200+data.denomination100.noOfNotes*100
        if(amount == 0L){
            val msg = data.logMsg
            msg.add(0,"Amount Withdrawal : Rs. $amt \nAvailable Denomination :" +
                    " ${data.denomination2000.note} = ${data.denomination2000.noOfNotes} ," +
                    " ${data.denomination500.note} = ${data.denomination500.noOfNotes} ," +
                    " ${data.denomination200.note} = ${data.denomination200.noOfNotes} ," +
                    " ${data.denomination100.note} = ${data.denomination100.noOfNotes}")
            _state.value = data.copy(
                logMsg = msg
            )
        }else{
            val msg = data.logMsg
            msg.add(0,"Amount not withdrawn \nAvailable Denomination :" +
                    " ${data.denomination2000.note} = ${_state.value.denomination2000.noOfNotes}  " +
                    " ${data.denomination500.note} = ${_state.value.denomination500.noOfNotes}  " +
                    " ${data.denomination200.note} = ${_state.value.denomination200.noOfNotes}  " +
                    " ${data.denomination100.note} = ${_state.value.denomination100.noOfNotes}")
            _state.value = _state.value.copy(
                logMsg = msg
            )
        }

    }

    private fun recursion(
        data : Denomination,
        amount : Long
    ) : Pair<Long,Denomination>{
        if(amount == 0L){
            return Pair(amount,data)
        }

        if(amount < data.note.toLong() || data.noOfNotes<=0){
            return Pair(amount,data)
        }

        return recursion(
            amount = amount - data.note.toLong(),
            data = data.copy(
                noOfNotes = data.noOfNotes-1,
                totalAmount = data.totalAmount-data.note.toLong()
            )
        )

    }


}