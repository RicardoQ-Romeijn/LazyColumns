package com.example.LazyColumn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.example.LazyColumn.ui.theme.RetrofitJsonJetpackTheme
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.navigationcomposercondatos.SetUpNavGraph

class MainActivity : ComponentActivity() {

    lateinit var navControler: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetrofitJsonJetpackTheme {
                Surface(color = MaterialTheme.colors.background) {
                    navControler = rememberNavController()
                    SetUpNavGraph(navController = navControler)
                }
            }
        }
    }
}
