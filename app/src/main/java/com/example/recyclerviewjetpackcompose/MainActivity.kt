package com.example.recyclerviewjetpackcompose

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.recyclerviewjetpackcompose.callbacks.DialogCallback
import com.example.recyclerviewjetpackcompose.composeUiFunctions.DialogBox3FA
import com.example.recyclerviewjetpackcompose.constant.constants
import com.example.recyclerviewjetpackcompose.ui.theme.RecyclerviewJetPackComposeTheme

class MainActivity : ComponentActivity() {

    private lateinit var openDialogCheck: MutableState<Boolean>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            openDialogCheck = remember {
                mutableStateOf(false) // Initially dialog is closed
            }
            RecyclerviewJetPackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val list = constants.fillpersonList() as List<String>
                    RecyclerView(list)
                    if (openDialogCheck.value) {
                        OpenDialog(this@MainActivity)
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun EachRow(desc: String, onItemClick: () -> Unit) {

        Card(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .clickable {
                    onItemClick()
                },
            shape = RoundedCornerShape(CornerSize(10.dp)),
            elevation = 5.dp,
        ) {
            Row(
                modifier = Modifier.padding(5.dp)
            ) {
                Image(
                    Icons.Default.Person, contentDescription = "person", modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterVertically)
                        .size(80.dp)
                        .clip(RoundedCornerShape(CornerSize(10.dp)))
                )
                Text(text = desc)
            }


        }
    }

    @Composable
    fun RecyclerView(list: List<String>) {

        LazyColumn {
            items(list) {
                EachRow(desc = it) {
                    openDialogCheck.value = true
                }
            }
        }
    }

    @Composable
    fun OpenDialog(mContext: Context) {
        RecyclerviewJetPackComposeTheme {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colors.background),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                DialogBox3FA {
                    object : DialogCallback {
                        override fun onSetupNowClick() {
                            Toast.makeText(
                                mContext,
                                "Click: Setup Now",
                                Toast.LENGTH_SHORT
                            ).show()
                            openDialogCheck.value = false
                        }

                        override fun doLaterClick() {
                            Toast.makeText(
                                mContext,
                                "Click: do Later",
                                Toast.LENGTH_SHORT
                            ).show()
                            openDialogCheck.value = false
                        }

                        override fun onCloseClick() {
                            openDialogCheck.value = false
                        }
                    }
                }
            }
        }
    }

}



