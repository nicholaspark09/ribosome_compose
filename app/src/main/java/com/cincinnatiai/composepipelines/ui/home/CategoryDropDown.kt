package com.cincinnatiai.composepipelines.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.cincinnatiai.composepipelines.R
import com.cincinnatiai.composepipelines.model.Category

@Composable
fun CategoryDropDown(
    categoryState: HomeCategoryState,
    onCategorySelected: (Category) -> Unit
) {
    var isDropdownExpanded by remember {
        mutableStateOf(false)
    }
    var selectedOption: Category? by rememberSaveable {
        mutableStateOf(null)
    }

    Row(
        modifier =
        Modifier
            .padding(16.dp)
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = if (categoryState is HomeCategoryState.Loading) stringResource(id = R.string.loading) else (selectedOption?.title
                ?: stringResource(id = R.string.categories_title)),
            modifier = Modifier.padding(end = 16.dp).testTag("dropdownTitle"),
            style = TextStyle(color = Color.Black),
        )
        DropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = isDropdownExpanded,
            onDismissRequest = { isDropdownExpanded = false }) {
            DropdownMenuItem(
                text = { Text(stringResource(id = R.string.select_category)) },
                onClick = {
                    // NOOP
                })
            if (categoryState is HomeCategoryState.CategoriesLoaded) {
                categoryState.categories.map {
                    DropdownMenuItem(
                        text = { Text(it.title) },
                        modifier =
                        Modifier
                            .fillMaxWidth()
                            .semantics {
                                contentDescription = "Option: " + it.title
                            },
                        onClick = {
                            selectedOption = it
                            isDropdownExpanded = false
                            onCategorySelected(it)
                        })
                }
            }
        }
        IconButton(onClick = {
            isDropdownExpanded = !isDropdownExpanded
        }) {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Category Dropdown"
            )
        }
    }
}