package com.example.thirty_day

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thirty_day.model.Menu
import com.example.thirty_day.model.MenuDish.menuDish


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuApp() {
    Scaffold(topBar = { MenuAppBar() }) { it ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = it
        ) {
            items(menuDish) {
                MenuCard(menu = it)
            }
        }

    }

}


@Composable
fun MenuCard(
    menu: Menu,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    Card(modifier.padding(start = 16.dp, end = 16.dp)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
                .padding(bottom = 8.dp)
        ) {
            MenuName(menuName = menu.nameMenu)
            Spacer(modifier = modifier.height(8.dp))
            MenuImage(menuImage = menu.imageMenu,
                expanded = expanded, onClick = {expanded = !expanded})
//            Spacer(modifier = modifier.height(16.dp))
//            MenuDescription(menuDescription = menu.nameDish)
        }
        if(expanded){
            Spacer(modifier = modifier.height(8.dp))
         MenuDescription(menuDescription = menu.nameDish)
        }
    }
}


@Composable
fun MenuName(
    @StringRes menuName: Int,
) {
    Text(
        text = stringResource(id = menuName),
        fontSize = 16.sp, fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 8.dp)
    )

}


@Composable
fun MenuImage(
    @DrawableRes menuImage: Int,
    expanded:Boolean,
    onClick: () ->Unit,
    modifier: Modifier = Modifier
) {

    Image(

        painter = painterResource(id = menuImage),
        contentDescription = null,
        modifier = Modifier.fillMaxSize()
            .clickable { onClick() }
    )
}

@Composable
fun MenuDescription(
    @StringRes menuDescription: Int,
) {
    Text(
        text = stringResource(id = menuDescription),
        textAlign = TextAlign.Start,
        modifier = Modifier.padding(8.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Menu for 30 day",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        })
}