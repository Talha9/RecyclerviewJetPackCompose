package com.example.recyclerviewjetpackcompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.recyclerviewjetpackcompose.constant.constants
import com.example.recyclerviewjetpackcompose.ui.theme.RecyclerviewJetPackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecyclerviewJetPackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val list = constants.fillpersonList() as List<String>
                    RecyclerView(list)

                }
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun EachRow(desc: String) {
        Card(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
            shape = RoundedCornerShape(CornerSize(10.dp)),
            elevation = 5.dp,
            onClick = {
                onItemClick(desc)
            }
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
                EachRow(desc = it)
            }
        }
    }

    private fun onItemClick(desc: String){
        Toast.makeText(this,desc,Toast.LENGTH_SHORT).show()
    }


}



