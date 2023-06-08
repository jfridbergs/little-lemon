package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemon.data.MenuItemRoom
import com.example.littlelemon.navigation.UserProfile
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavHostController, menuItems: List<MenuItemRoom>){

    val markaziaFamily = FontFamily(
        Font(R.font.markazitext_regular, FontWeight.Normal),
    )

    val karlaFamily = FontFamily(
        Font(R.font.karla_regular, FontWeight.Normal),
    )


    var filteredMenuItems by remember {
        mutableStateOf(emptyList<MenuItemRoom>())
    }
    filteredMenuItems = menuItems
    var menuItemsGroup by remember {
        mutableStateOf(emptyList<MenuItemRoom>())
    }

    var filteredMenuItemsGroup by remember {
        mutableStateOf(emptyList<MenuItemRoom>())
    }



    var searchPhrase by remember{
        mutableStateOf("")
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(colorResource(id = R.color.white))){
        Box(Modifier.fillMaxWidth()){
            Image(painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(id = R.string.logo_description),
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(20.dp)
                    .align(Alignment.Center),
                contentScale = ContentScale.FillWidth)
            Image(painter = painterResource(id = R.drawable.profile),
                contentDescription = stringResource(id = R.string.logo_description),
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterEnd)
                    .size(50.dp)
                    .clickable { navController.navigate(UserProfile.route) },
                contentScale = ContentScale.Fit)
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.primary1))
            .padding(start = 10.dp, end = 10.dp)){
            Text(
                text = stringResource(id = R.string.hero_title),
                fontSize = 64.sp,
                fontFamily = markaziaFamily,
                color = colorResource(id = R.color.primary2)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Column(Modifier.fillMaxWidth(0.55f)){
                    Text(
                        text = stringResource(id = R.string.hero_subtitle),
                        fontSize = 40.sp,
                        fontFamily = markaziaFamily,
                        color = colorResource(id = R.color.secondary3)
                    )
                    Text(
                        text = stringResource(id = R.string.hero_description),
                        fontSize = 18.sp,
                        lineHeight = 18.sp,
                        fontFamily = karlaFamily,
                        color = colorResource(id = R.color.secondary3)
                    )
                }
                Image(painter = painterResource(id = R.drawable.hero_image),
                    contentDescription = stringResource(id = R.string.logo_description),
                    modifier = Modifier
                        .size(150.dp)
                        .padding(top = 10.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop)
            }
            TextField(
                value = searchPhrase,
                label = { Text("Search")},
                onValueChange = {searchPhrase = it},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp)
                    .background(
                        colorResource(id = R.color.secondary3),
                        shape = RoundedCornerShape(7.dp)
                    )
                    ,
                shape = RoundedCornerShape(7.dp),
                leadingIcon = { Icon( imageVector = Icons.Default.Search, contentDescription = "Search") }
            )
        }

        if(menuItemsGroup.isNotEmpty()) {
            if (searchPhrase.isNotEmpty()) {
                filteredMenuItemsGroup =
                    menuItemsGroup.filter { it.title.contains(searchPhrase, true) }
            } else {
                filteredMenuItemsGroup = menuItemsGroup
            }
        }else {
            if (searchPhrase.isNotEmpty()) {
                filteredMenuItems = menuItems.filter{it.title.contains(searchPhrase, true) }
            } else {
                filteredMenuItems = menuItems
            }


        }
        println("filteredmenuitems ${filteredMenuItems}")
        Text(
            text= stringResource(id =R.string.order_title ),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = colorResource(id = R.color.secondary4),
            modifier = Modifier.padding(start=10.dp, top = 20.dp, end = 10.dp, bottom = 10.dp)
        )

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            menuItems.groupBy{it.category}.forEach {
                val category = it.key
                Button(onClick={
                    menuItemsGroup = it.value
                    println("${it.key} button was pressed")
                    println(it.value)
                },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.secondary3))){
                    Text(text = category.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.ROOT
                        ) else it.toString()
                    },
                        color = colorResource(id = R.color.primary1),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

            }
        }
        Divider(color = colorResource(id = R.color.secondary3), thickness = 1.dp, modifier = Modifier.padding(start = 10.dp, end = 10.dp, top=10.dp))
        if(menuItemsGroup.isNotEmpty()){
            MenuItemsList(items = filteredMenuItemsGroup)
        } else {
            MenuItemsList(items = filteredMenuItems)
        }


    }


}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun MenuItemsList(items: List<MenuItemRoom>) {
    val karlaFamily = FontFamily(
        Font(R.font.karla_regular, FontWeight.Normal),
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp)
    ) {
        items(
            items = items,
            itemContent = { menuItem ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(Modifier.fillMaxWidth(0.7f)) {
                        Text(
                            text = menuItem.title,
                            fontSize = 18.sp,
                            fontFamily = karlaFamily,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(id = R.color.secondary4)
                        )
                        Text(
                            text = menuItem.description,
                            maxLines = 2,
                            fontSize = 16.sp,
                            fontFamily = karlaFamily,
                            lineHeight = 16.sp,
                            color = colorResource(id = R.color.secondary4),
                            modifier = Modifier.padding(top = 5.dp, bottom = 15.dp)
                        )
                        Text(
                            text="$${"%.2f".format(menuItem.price)}",
                            fontSize = 16.sp,
                            fontFamily = karlaFamily,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(id = R.color.secondary4)
                        )
                    }
                   //Text(menuItem.image)
                    GlideImage(
                        model = menuItem.image,
                        contentDescription =menuItem.title,
                        modifier = Modifier.size(100.dp),
                        contentScale = ContentScale.Crop)
                }
                Divider(color = colorResource(id = R.color.secondary3), thickness = 1.dp, modifier = Modifier.padding(10.dp))
            }
                   
        )
    }
}