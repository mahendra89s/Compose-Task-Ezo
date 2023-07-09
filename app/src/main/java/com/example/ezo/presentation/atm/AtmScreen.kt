package com.example.ezo.presentation.atm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ezo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AtmScreen(
    apiScreenViewModel: AtmScreenViewModel = hiltViewModel()
) {
    val uiState = apiScreenViewModel.state.collectAsState()
    var textField2000 by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var textField500 by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var textField200 by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var textField100 by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var textFieldWithdrawal by remember {
        mutableStateOf(TextFieldValue(""))
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 30.dp),
            text = stringResource(id = R.string.current_available_denomination_in_machine),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.note_2000),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = uiState.value.denomination2000.noOfNotes.toString(),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            TextField(
                modifier = Modifier.width(100.dp),
                value = textField2000,
                onValueChange = {
                    textField2000 = it
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.note_500),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = uiState.value.denomination500.noOfNotes.toString(),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            TextField(
                modifier = Modifier.width(100.dp),
                value = textField500,
                onValueChange = {
                    textField500 = it
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.note_200),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = uiState.value.denomination200.noOfNotes.toString(),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            TextField(
                modifier = Modifier.width(100.dp),
                value = textField200,
                onValueChange = {
                    textField200 = it
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.note_100),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = uiState.value.denomination100.noOfNotes.toString(),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            TextField(
                modifier = Modifier.width(100.dp),
                value = textField100,
                onValueChange = {
                    textField100 = it
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.total),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = uiState.value.totalAmount.toString(),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Button(onClick = {
                if(textField2000.text.isBlank() &&
                    textField500.text.isBlank() &&
                    textField200.text.isBlank() &&
                    textField100.text.isBlank()
                ){
                    return@Button
                }

                apiScreenViewModel.handleEvent(
                    AtmScreenEvents.AmountDeposited(
                        noOf100Notes = textField100.text.toLongOrNull() ?: 0,
                        noOf500Notes = textField500.text.toLongOrNull() ?: 0,
                        noOf200Notes = textField200.text.toLongOrNull() ?: 0,
                        noOf2000Notes = textField2000.text.toLongOrNull() ?: 0,
                    )
                )
                textField2000 = TextFieldValue("")
                textField500 = TextFieldValue("")
                textField200 = TextFieldValue("")
                textField100 = TextFieldValue("")
            }) {
                Text(text = stringResource(id = R.string.deposit))
            }
        }
        Spacer(modifier = Modifier.height(20.dp))


        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            TextField(
                modifier = Modifier.width(200.dp),
                value = textFieldWithdrawal,
                onValueChange = {
                    textFieldWithdrawal = it
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
            Button(onClick = {
                if(textFieldWithdrawal.text.isBlank()){
                    return@Button
                }
                apiScreenViewModel.handleEvent(
                    AtmScreenEvents.AmountWithdrawal(
                        textFieldWithdrawal.text.toLong()
                    )
                )
                textFieldWithdrawal = TextFieldValue("")
            }) {
                Text(text = stringResource(id = R.string.withdrawal))
            }
        }


    }
}