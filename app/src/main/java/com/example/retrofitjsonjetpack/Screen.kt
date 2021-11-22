package com.example.navigationcomposercondatos

sealed class Screen(val route: String) {
    object Home : Screen(route = "home_screen")
    object Second : Screen("second_screen/{id}/{name}"){
 //       fun passId(id: Int): String {
   //         return this.route.replace("$id",id.toString())
     //   }
        fun passId_Name(
            id:Int,
            name: String): String {
            return "second_screen/$id/$name"
        }
    }
}