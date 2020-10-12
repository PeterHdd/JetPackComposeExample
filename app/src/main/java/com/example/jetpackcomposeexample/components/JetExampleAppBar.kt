package com.example.jetpackcomposeexample.components

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.example.jetpackcomposeexample.theme.JetPackComposeExampleTheme

@Composable
fun JetPackComposeExampleAppBar(
        modifier: Modifier = Modifier,
        backgroundColor : Color,
        title: @Composable RowScope?.() -> Unit = {},
        actions: @Composable RowScope.() -> Unit = {}
) {
    Column {
        TopAppBar(
                modifier = modifier,
                backgroundColor = backgroundColor,
                elevation = 0.dp, // No shadow needed
                contentColor = MaterialTheme.colors.onSurface,
                actions = actions,
                title = { Row { title() } },
        )
        Divider()
    }
}

@Preview
@Composable
fun JetPackComposeExamplePreview() {
    JetPackComposeExampleTheme {
        JetPackComposeExampleAppBar(title = { Text("Test!") }, backgroundColor = Color.White)
    }
}
