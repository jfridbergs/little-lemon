package com.example.littlelemon

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.provider.ContactsContract
import android.provider.Settings.Global.getString
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.littlelemon.navigation.UserProfile
import com.example.littlelemon.ui.theme.LittleLemonTheme



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Onboarding(navController: NavHostController) {

    val karlaFamily = FontFamily(
        Font(R.font.karla_regular, FontWeight.Bold),
    )

    val context = LocalContext.current
    val sharedPreferences by lazy {
        context.getSharedPreferences("LittleLemon", MODE_PRIVATE)
    }


    var firstName by remember {mutableStateOf(TextFieldValue(""))}
    var lastName by remember {mutableStateOf(TextFieldValue(""))}
    var email by remember {mutableStateOf(TextFieldValue(""))}
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(id = R.string.logo_description),
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .padding(20.dp),
        contentScale = ContentScale.FillWidth)
        Surface(modifier = Modifier.fillMaxWidth(),
                color = colorResource(R.color.primary1),
        ){
            Text(
                text = stringResource(id = R.string.invitation),
                fontSize = 25.sp,
                fontFamily = karlaFamily,
                color = colorResource(R.color.white),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp),
                textAlign = TextAlign.Center,
            )
        }
        
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .fillMaxHeight(0.85f)
            ){
            Text(
                text = stringResource(id = R.string.personal_information),
                fontSize = 20.sp,
                color = colorResource(id = R.color.secondary4),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)
            )
            OutlinedTextField(
                value = firstName,
                onValueChange = {firstName = it},
                label = ({Text(text = stringResource(
                    id = R.string.first_name
                ))}),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp),
                shape = RoundedCornerShape(7.dp),
            )
            OutlinedTextField(
                value = lastName,
                onValueChange = {lastName = it},
                label = ({Text(text = stringResource(
                    id = R.string.last_name
                ))}),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp),
                shape = RoundedCornerShape(7.dp),
            )
            OutlinedTextField(
                value = email,
                onValueChange = {email = it},
                label = ({Text(text = stringResource(
                    id = R.string.email
                ))}),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp),
                shape = RoundedCornerShape(7.dp),
            )

        }
        Button(onClick = {
            if(firstName.text=="" || lastName.text=="" || email.text==""){
                Toast.makeText(context, context.getString(  R.string.empty_fields), Toast.LENGTH_SHORT).show()
            } else {

                with (sharedPreferences.edit()) {
                    putString("first_name", firstName.text)
                    putString("last_name", lastName.text)
                    putString("email", email.text)
                    apply()
                }
                Toast.makeText(context, context.getString(  R.string.registration_successful), Toast.LENGTH_SHORT).show()
                navController.navigate(UserProfile.route)
            }
                         },
            modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
            shape = RoundedCornerShape(7.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.primary2))) {
            Text(text = stringResource(id = R.string.register), fontSize = 16.sp, color = colorResource(id = R.color.secondary4))
        }

    }
}



@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    LittleLemonTheme {
        //Onboarding()
    }
}