package com.example.happybirthday

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.happybirthday.ui.theme.HappyBirthdayTheme

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class TipCalculationInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.happybirthday", appContext.packageName)
    }

    @Test
    fun calculate_20_percent_tip_default() {
        composeTestRule.setContent {
            HappyBirthdayTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TipCalculator(
                        userName = "Coding Agent",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        composeTestRule.onNodeWithText("Total Amount").performTextInput("100")

        composeTestRule.waitUntil(timeoutMillis = 5_000L) {
            // Returns true when the condition is successfully met
            composeTestRule.onNodeWithText("Tip Amount = 20.0").isDisplayed()
        }
    }

    @Test
    fun calculate_40_percent_tip_entered() {
        composeTestRule.setContent {
            HappyBirthdayTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TipCalculator(
                        userName = "Coding Agent",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        composeTestRule.onNodeWithText("Total Amount").performTextInput("100")
        composeTestRule.onNodeWithText("Tip percentage").performTextClearance()
        composeTestRule.onNodeWithText("Tip percentage").performTextInput("40")

        composeTestRule.waitUntil(timeoutMillis = 5_000L) {
            // Returns true when the condition is successfully met
            composeTestRule.onNodeWithText("Tip Amount = 40.0").isDisplayed()
        }
    }

    @Test
    fun calculate_no_input_entered() {
        composeTestRule.setContent {
            HappyBirthdayTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TipCalculator(
                        userName = "Coding Agent",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        composeTestRule.waitUntil(timeoutMillis = 5_000L) {
            // Returns true when the condition is successfully met
            composeTestRule.onNodeWithText("Please enter a total amount.").isDisplayed()
        }
    }

    @Test
    fun calculate_total_amount_input_not_entered() {
        composeTestRule.setContent {
            HappyBirthdayTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TipCalculator(
                        userName = "Coding Agent",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        composeTestRule.onNodeWithText("Tip percentage").performTextClearance()
        composeTestRule.onNodeWithText("Tip percentage").performTextInput("40")

        composeTestRule.waitUntil(timeoutMillis = 5_000L) {
            // Returns true when the condition is successfully met
            composeTestRule.onNodeWithText("Please enter a total amount.").isDisplayed()
        }
    }

    @Test
    fun calculate_tip_percentage_input_not_entered() {
        composeTestRule.setContent {
            HappyBirthdayTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TipCalculator(
                        userName = "Coding Agent",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        composeTestRule.onNodeWithText("Total Amount").performTextInput("100")
        composeTestRule.onNodeWithText("Tip percentage").performTextClearance()

        composeTestRule.waitUntil(timeoutMillis = 5_000L) {
            // Returns true when the condition is successfully met
            composeTestRule.onNodeWithText("Please enter a valid tip amount (valid integer or double) and between 0.0 and 100.0 inclusive").isDisplayed()
        }
    }

    @Test
    fun calculate_tip_invalid_number_total_amount() {
        composeTestRule.setContent {
            HappyBirthdayTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TipCalculator(
                        userName = "Coding Agent",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        composeTestRule.onNodeWithText("Total Amount").performTextInput("a")

        composeTestRule.waitUntil(timeoutMillis = 5_000L) {
            // Returns true when the condition is successfully met
            composeTestRule.onNodeWithText("Please enter a valid total amount number or double").isDisplayed()
        }
    }

    @Test
    fun calculate_tip_invalid_number_tip_percentage() {
        composeTestRule.setContent {
            HappyBirthdayTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TipCalculator(
                        userName = "Coding Agent",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        composeTestRule.onNodeWithText("Total Amount").performTextInput("100")
        composeTestRule.onNodeWithText("Tip percentage").performTextClearance()
        composeTestRule.onNodeWithText("Tip percentage").performTextInput("a")

        composeTestRule.waitUntil(timeoutMillis = 5_000L) {
            // Returns true when the condition is successfully met
            composeTestRule.onNodeWithText("Please enter a valid tip amount (valid integer or double) and between 0.0 and 100.0 inclusive").isDisplayed()
        }
    }

    @Test
    fun calculate_invalid_number_tip_percentage_less_than_0() {
        composeTestRule.setContent {
            HappyBirthdayTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TipCalculator(
                        userName = "Coding Agent",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        composeTestRule.onNodeWithText("Total Amount").performTextInput("100")
        composeTestRule.onNodeWithText("Tip percentage").performTextClearance()
        composeTestRule.onNodeWithText("Tip percentage").performTextInput("-10")

        composeTestRule.waitUntil(timeoutMillis = 5_000L) {
            // Returns true when the condition is successfully met
            composeTestRule.onNodeWithText("Please enter a valid tip amount (valid integer or double) and between 0.0 and 100.0 inclusive").isDisplayed()
        }
    }

    @Test
    fun calculate_invalid_number_tip_percentage_more_than_100() {
        composeTestRule.setContent {
            HappyBirthdayTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TipCalculator(
                        userName = "Coding Agent",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        composeTestRule.onNodeWithText("Total Amount").performTextInput("100")
        composeTestRule.onNodeWithText("Tip percentage").performTextClearance()
        composeTestRule.onNodeWithText("Tip percentage").performTextInput("120")

        composeTestRule.waitUntil(timeoutMillis = 5_000L) {
            // Returns true when the condition is successfully met
            composeTestRule.onNodeWithText("Please enter a valid tip amount (valid integer or double) and between 0.0 and 100.0 inclusive").isDisplayed()
        }
    }

    @Test
    fun calculate_valid_number_tip_percentage_0() {
        composeTestRule.setContent {
            HappyBirthdayTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TipCalculator(
                        userName = "Coding Agent",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        composeTestRule.onNodeWithText("Total Amount").performTextInput("100")
        composeTestRule.onNodeWithText("Tip percentage").performTextClearance()
        composeTestRule.onNodeWithText("Tip percentage").performTextInput("0")

        composeTestRule.waitUntil(timeoutMillis = 5_000L) {
            // Returns true when the condition is successfully met
            composeTestRule.onNodeWithText("Tip Amount = 0.0").isDisplayed()
        }
    }

    @Test
    fun calculate_valid_number_tip_percentage_100() {
        composeTestRule.setContent {
            HappyBirthdayTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TipCalculator(
                        userName = "Coding Agent",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        composeTestRule.onNodeWithText("Total Amount").performTextInput("100")
        composeTestRule.onNodeWithText("Tip percentage").performTextClearance()
        composeTestRule.onNodeWithText("Tip percentage").performTextInput("100")

        composeTestRule.waitUntil(timeoutMillis = 5_000L) {
            // Returns true when the condition is successfully met
            composeTestRule.onNodeWithText("Tip Amount = 100.0").isDisplayed()
        }
    }

    @Test
    fun calculate_valid_number_total_amount_tip_percentage_decimal() {
        composeTestRule.setContent {
            HappyBirthdayTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TipCalculator(
                        userName = "Coding Agent",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        composeTestRule.onNodeWithText("Total Amount").performTextInput("100.0")
        composeTestRule.onNodeWithText("Tip percentage").performTextClearance()
        composeTestRule.onNodeWithText("Tip percentage").performTextInput("12.25")

        composeTestRule.waitUntil(timeoutMillis = 5_000L) {
            // Returns true when the condition is successfully met
            composeTestRule.onNodeWithText("Tip Amount = 12.25").isDisplayed()
        }
    }
}