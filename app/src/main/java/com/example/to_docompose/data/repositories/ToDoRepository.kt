package com.example.to_docompose.data.repositories

import com.example.to_docompose.data.ToDoDao
import com.example.to_docompose.data.models.ToDoTask
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject


@ViewModelScoped //Hilt Library
class ToDoRepository  @Inject constructor(private val toDoDao: ToDoDao){

    val getAllTasks: Flow<List<ToDoTask>> = toDoDao.getAllTask()
    val sortByLowPriority: Flow<List<ToDoTask>> = toDoDao.sortByLowPriority()
    val sortByHighPriority: Flow<List<ToDoTask>> = toDoDao.sortByHighPriority()

    fun getSelectedTask(taskId: Int) : Flow<ToDoTask> {
        return toDoDao.getSelectedTask(taskId = taskId)
    }

    suspend fun addTask(todoTask: ToDoTask){
        toDoDao.addTask(toDoTask = todoTask)
    }

    suspend fun updateTask(todoTask: ToDoTask){
        toDoDao.updateTask(toDoTask = todoTask)
    }

    suspend fun deleteTask(todoTask: ToDoTask){
        toDoDao.deleteTask(toDoTask = todoTask)
    }


    suspend fun deleteAllTasks( ){
        toDoDao.deleteAllTasks( )
    }

    fun searchDatabase(searchQuery: String): Flow<List<ToDoTask>> {
       return  toDoDao.searchDatabase(searchQuery = searchQuery)
    }

}