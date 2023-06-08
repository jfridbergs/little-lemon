package com.example.littlelemon

import android.content.Context.MODE_PRIVATE
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun UserProfile(navController: NavHostController){

    val karlaFamily = FontFamily(
        Font(R.font.karla_regular, FontWeight.Normal),
    )
    
    val context = LocalContext.current
    val sharedPreferences by lazy {
        context.getSharedPreferences("LittleLemon", MODE_PRIVATE)
    }
    val firstName = sharedPreferences.getString("first_name", "no name was stored")
    val lastName = sharedPreferences.getString("last_name", "no name was stored")
    val email = sharedPreferences.getString("email", "no name was stored")

    val textBoxModifier = Modifier
        .fillMaxWidth()
        .padding(top = 10.dp, bottom = 10.dp)
        .background(colorResource(id = R.color.secondary3), shape = RoundedCornerShape(7.dp))
        .border(
            1.dp,
            SolidColor(colorResource(id = R.color.secondary4)),
            shape = RoundedCornerShape(7.dp)
        )
        .padding(7.dp)
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
        Image(painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(id = R.string.logo_description),
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .padding(20.dp),
            contentScale = ContentScale.FillWidth)
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .fillMaxHeight(0.85f)){
            Text(
                text = stringResource(id = R.string.profile_title),
                fontSize = 20.sp,
                color = colorResource(id = R.color.secondary4),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 20.dp, bottom = 20.dp))

            Text(text = stringResource(id = R.string.first_name), fontFamily = karlaFamily, fontSize=16.sp, modifier = Modifier.padding(top = 10.dp))
            if (firstName != null) {
                Text(
                    text = firstName,
                    fontFamily = karlaFamily,
                    fontSize=16.sp,
                    modifier = textBoxModifier
                )
            }

            Text(text = stringResource(id = R.string.last_name), fontFamily = karlaFamily, fontSize=16.sp, modifier = Modifier.padding(top = 10.dp))
            if (lastName != null) {
                Text(
                    text = lastName,
                    fontFamily = karlaFamily,
                    fontSize=16.sp,
                    modifier = textBoxModifier
                )
            }

            Text(text = stringResource(id = R.string.email), fontFamily = karlaFamily, fontSize=16.sp, modifier = Modifier.padding(top = 10.dp))
            if (email != null) {
                Text(
                    text = email,
                    fontFamily = karlaFamily,
                    fontSize=16.sp,
                    modifier = textBoxModifier
                )
            }
        }

        Button(onClick = {
            with (sharedPreferences.edit()) {
                putString("first_name", "")
                putString("last_name", "")
                putString("email", "")
                apply()
            }
            navController.navigate(com.example.littlelemon.navigation.Onboarding.route)
        },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            shape = RoundedCornerShape(7.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.primary2))) {
            Text(text = stringResource(id = R.string.logout), fontSize = 16.sp, color = colorResource(id = R.color.secondary4))
        }
    }

    BackHandler(true) {
        navController.navigate(com.example.littlelemon.navigation.Home.route)
    }

}

@Preview(showBackground = true)
@Composable
fun UserProfilePreview() {
    LittleLemonTheme {
        UserProfile(rememberNavController())
    }
}