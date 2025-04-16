package com.example.myapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
import com.example.book.ui.book.BookDetailScreen
import com.example.book.ui.book.BookSearchScreen


@Composable
fun BookNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "search"
    ) {
        composable("search") {
            BookSearchScreen { book ->
                navController.navigate("details/${book.id}")
            }
        }

        composable(
            route = "details/{bookId}",
            arguments = listOf(navArgument("bookId") { type = NavType.StringType })
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getString("bookId")
            BookDetailScreen(bookId = bookId)
        }
    }
}