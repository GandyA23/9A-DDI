package com.example.activity6avilagandy.model

import com.example.activity6avilagandy.MainActivity

data class Collection(val uid : String, val title : String, val image : String, val description : String = MainActivity.DEFAULT_DESCRIPTION) {
}