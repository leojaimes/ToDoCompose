package com.example.to_docompose

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp //dependency injection container for app
class ToDoApplication: Application()