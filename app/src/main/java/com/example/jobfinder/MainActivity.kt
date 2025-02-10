package com.example.jobfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.jobfinder.navigation.JobFinderNavGraph
import com.example.jobfinder.ui.theme.JobFinderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JobFinderTheme {
                JobFinderNavGraph()
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun OfferCardPreview() {
    JobFinderTheme {
        MainActivity()
    }
}