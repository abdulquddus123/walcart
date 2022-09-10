package com.example.walcartapp.view.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.walcartapp.R
import com.example.walcartapp.model.Categories
import com.example.walcartapp.model.Category
import com.example.walcartapp.viewModel.CategoryViewModel

@ExperimentalMaterialApi
@Composable

fun ExpandableCard(viewModel: CategoryViewModel = hiltViewModel()) {
    val getCategories = viewModel.getCategoryData.observeAsState()

    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Browse Category",
                            color = Color.Black
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {

                            }) {
                            Icon(

                                painter = painterResource(id = R.drawable.ic_back),
                                contentDescription = "Back"
                            )
                        }
                    },
                    // below line is use to give background color
                    backgroundColor = colorResource(id = R.color.white),
                    contentColor = Color.Black,
                    elevation = 0.dp
                )
            },
        ) {


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray)
            ) {


                Card(
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 0.dp, 0.dp)
                        .fillMaxWidth()
                        .animateContentSize(
                            animationSpec = tween(
                                durationMillis = 300,
                                easing = LinearOutSlowInEasing
                            )
                        ),
                    backgroundColor = Color(0xffF5F5F5),

                    onClick = {
                        expandedState = !expandedState
                    }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {
                        viewModel.getCategoryData()
                        if (!viewModel.isLoading.value) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                        if (viewModel.isLoading.value) {
                            if (viewModel.getCategoryData.value?.data?.getCategories?.result?.categories?.size ?: 0 > 0) {
                                LazyColumn(modifier = Modifier.padding(10.dp)) {
                                    item(getCategories.value?.data?.getCategories?.result?.categories?.size) {

                                    }
                                    items(getCategories.value?.data?.getCategories?.result?.categories?.size!!) { index ->
                                        categoryTitleView(
                                            getCategories.value?.data?.getCategories?.result?.categories?.get(
                                                index
                                            )!!
                                        )


                                    }

                                }

                            }
                        }

                    }
                }
            }

        }
    }

}

@Composable
fun categoryTitleView(data: Categories) {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(6f),
            text = data.bnName.toString(),
            fontSize = 12.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        IconButton(
            modifier = Modifier
                .weight(1f)
                .alpha(ContentAlpha.medium)
                .rotate(rotationState),
            onClick = {
                expandedState = !expandedState
            }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_down_24),
                contentDescription = "Drop-Down Arrow"
            )
        }
    }
    if (expandedState) {
        Column() {
            Image(
                painter = rememberAsyncImagePainter(data?.image?.url),
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )
            Text(
                text = data?.image?.name.toString(),
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}