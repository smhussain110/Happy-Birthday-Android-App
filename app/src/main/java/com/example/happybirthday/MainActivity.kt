package com.example.happybirthday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.ui.theme.HappyBirthdayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HappyBirthdayTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Hard coded the affirmation quotes for now.
                    val affirmationList: List<String> = listOf(
                        "Hello World!!",
                        "Keep Going!!",
                        "You can do it!!",
                        "Just do it.",
                        "What is up",
                        "What is happening",
                        "Lets crush it today",
                        "Lets do it today"
                    )
                    // Call the Affirmation List Composable - this displays these
                    // affirmation quotes as list
                    AffirmationList(
                        affirmationList = affirmationList,
                        modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@VisibleForTesting
internal fun calculateTipAmount(
    totalAmount: Double,
    tipPercentage: Double
): Double {
    val tipAmount: Double = totalAmount * tipPercentage / 100
    return tipAmount
}

@Composable
fun TipCalculator(userName: String, modifier: Modifier = Modifier) {
    Column (
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text (
            text = "Hello $userName\n\nEnter Total Amount:",
            fontSize = 48.sp,
            lineHeight = 60.sp,
            textAlign = TextAlign.Center
        )
        val totalAmountTextState: TextFieldState =
            rememberTextFieldState(initialText = "")
        OutlinedTextField (
            state = totalAmountTextState,
            label = { Text("Total Amount") },
            textStyle =
                TextStyle(
                    fontSize = 64.sp,
                ),
            modifier = Modifier
                .padding(16.dp)
        )
        val tipPercentageTextState: TextFieldState =
            rememberTextFieldState(initialText = "20.0")
        OutlinedTextField (
            state = tipPercentageTextState,
            label = { Text("Tip percentage") },
            textStyle =
                TextStyle(
                    fontSize = 64.sp,
                ),
            modifier = Modifier
                .padding(16.dp)
        )
        println("here log: totalAmountTextState = $totalAmountTextState, tipPercentageTextState = $tipPercentageTextState")
        val totalAmount: Double = totalAmountTextState.text.toString().toDoubleOrNull() ?: -1.0
        val tipPercent: Double = tipPercentageTextState.text.toString().toDoubleOrNull() ?: -1.0
        val tipAmount: Double = calculateTipAmount(
            totalAmount = totalAmount,
            tipPercentage = tipPercent
        )
        if (totalAmountTextState.text.toString().isEmpty()) {
            Text (
                text = "Please enter a total amount.",
                fontSize = 42.sp,
                lineHeight = 60.sp,
                textAlign = TextAlign.Center
            )
        } else if (totalAmount < 0.0) {
            Text (
                text = "Please enter a valid total amount number or double",
                fontSize = 42.sp,
                lineHeight = 60.sp,
                textAlign = TextAlign.Center
            )
        } else if (tipPercent !in 0.0..100.0) {
            Text (
                text = "Please enter a valid tip amount (valid integer or double) and between 0.0 and 100.0 inclusive",
                fontSize = 42.sp,
                lineHeight = 60.sp,
                textAlign = TextAlign.Center
            )
        } else {
            Text (
                text = "Tip Amount = $tipAmount",
                fontSize = 42.sp,
                lineHeight = 60.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun GreetingText(message: String, from: String, modifier: Modifier = Modifier) {
    Column (
        verticalArrangement = Arrangement.Center,
        modifier = modifier
        ) {
        Text(
            text = message,
            fontSize = 48.sp,
            lineHeight = 60.sp,
            textAlign = TextAlign.Center,
        )
        Text(
            text = from,
            fontSize = 36.sp,
            modifier = Modifier
                .padding(16.dp)
                .align(alignment = Alignment.End)
        )
        val inputFieldTextState = rememberTextFieldState(initialText = "")
        var submittedText by remember { mutableStateOf("") }
        OutlinedTextField(
            state = inputFieldTextState,
            label = { Text("Text") },
            modifier = Modifier
                .padding(16.dp)
        )

        println("HEREEE input field text: " + inputFieldTextState.text)
        if (inputFieldTextState.text.toString().isNotEmpty()) {
            Text(
                text = "Typing Text: " + inputFieldTextState.text.toString(),
                fontSize = 42.sp,
                lineHeight = 60.sp,
                modifier = Modifier
                    .padding(16.dp)
            )
        }

        Button(onClick = { submittedText = inputFieldTextState.text.toString() }) {
            Text("Submit Text")
        }

        println("HEREEE submitted text: " + submittedText)
        if (submittedText.isNotEmpty()) {
            Text(
                text = "Submitted Text: " + submittedText,
                fontSize = 42.sp,
                lineHeight = 60.sp,
                modifier = Modifier
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun GreetingImage(message: String, from: String, modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.androidparty)
    Box(modifier) {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.5F
        )
        GreetingText(
            message = message,
            from = from,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        )
    }
}

@Preview(
    showBackground = true,
    name = "Birthday Card Preview")
@Composable
fun BirthdayCardPreview() {
    HappyBirthdayTheme {
        // GreetingText("Happy birthday Fellow Coder!", from = "From Android")
        GreetingImage("Happy birthday Fellow Coder!", from = "From Android")
    }
}

@Composable
fun AffirmationCard(affirmationText: String, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Text(
            text = "Affirmation Text: $affirmationText",
            fontSize = 42.sp,
            lineHeight = 60.sp,
            modifier = Modifier
                .padding(16.dp)
        )
    }
}

@Composable
fun AffirmationList(affirmationList: List<String>, modifier: Modifier = Modifier) {
    // Using Lazy column makes it into a scrollable list.
    LazyColumn(modifier = modifier) {
        items(affirmationList.size) { index ->
            AffirmationCard(affirmationList[index], modifier)
        }
    }
}

@Preview
@Composable
fun AffirmationCardPreview() {
    HappyBirthdayTheme {
        AffirmationCard("Keep Going!!")
    }
}

@Preview
@Composable
fun AffirmationListPreview() {
    HappyBirthdayTheme {
        AffirmationList(listOf("Keep Going!!", "Keep Going!!"))
    }
}