package com.cincinnatiai.composepipelines

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testThatCategoriesLoad() {
        // Given
        composeTestRule.onNodeWithTag("dropdownTitle").assertExists()

        waitForCategoriesToBeDisplayed()
    }

    @Test
    fun testThatDropdown_hasEngineering() {
        // Given
        waitForCategoriesToBeDisplayed()

        // When
        composeTestRule.onNodeWithContentDescription("Category Dropdown").performClick()

        // Then
        composeTestRule.onNodeWithText("engineering").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Option: engineering").assertIsDisplayed()
    }

    @Test
    fun testThatWhenEngineeringSelected_LazyColumnHasItems() {
        // Given
        waitForCategoriesToBeDisplayed()
        composeTestRule.onNodeWithContentDescription("Category Dropdown").performClick()
        composeTestRule.onNodeWithText("engineering").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Option: engineering").assertIsDisplayed()


        // When
        composeTestRule.onNodeWithText("engineering").performClick()

        // Then
        Thread.sleep(3000)
        composeTestRule.onNodeWithContentDescription("Unit Test and Integration Test Creator")
            .assertIsDisplayed()
        composeTestRule.onNodeWithTag("Item:6").performClick()

        // Wait till next screen
        Thread.sleep(4000)

    }

    private fun waitForCategoriesToBeDisplayed() {
        Thread.sleep(1800)
        composeTestRule.onNodeWithText("Categories").assertIsDisplayed()
    }
}