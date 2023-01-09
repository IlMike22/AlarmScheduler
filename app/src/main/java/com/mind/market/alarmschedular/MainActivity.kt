package com.mind.market.alarmschedular

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*

import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mind.market.alarmschedular.ui.theme.AlarmSchedularTheme
import java.time.LocalDateTime

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlarmSchedularTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var secondsText by remember {
                        mutableStateOf("")
                    }
                    var message by remember {
                        mutableStateOf("")
                    }
                    val scheduler = AlarmScheduler(this)
                    var alarmItem: AlarmItem? = null

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        OutlinedTextField(
                            value = secondsText,
                            onValueChange = { secondsText = it },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(text = "Trigger alarm in seconds")
                            }
                        )
                        OutlinedTextField(
                            value = message,
                            onValueChange = { message = it },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(text = "Message")
                            }
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(
                                onClick = {
                                    alarmItem = AlarmItem(
                                        time = LocalDateTime.now()
                                            .plusSeconds(secondsText.toLong()),
                                        message = message
                                    )
                                    alarmItem?.let(scheduler::schedule)
                                    message = ""
                                    secondsText = ""
                                }
                            ) {
                                Text(text = "Schedule")
                            }

                            Button(
                                onClick = {
                                    alarmItem?.let(scheduler::cancel)
                                }
                            ) {
                                Text(text = "Cancel")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AlarmSchedularTheme {
        Greeting("Android")
    }
}