package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.littlelemon.navigation.UserProfile

@Composable
fun Home(navController: NavHostController){
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
                    .align(Alignment.TopEnd)
                    .size(50.dp)
                    .clickable { navController.navigate(UserProfile.route) },
                contentScale = ContentScale.Fit)
        }
        Text(text = "Home screen")
    }


}