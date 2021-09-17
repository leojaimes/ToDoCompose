package com.example.to_docompose.ui.screens.list

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_docompose.data.models.Priority
import com.example.to_docompose.data.models.ToDoTask
import com.example.to_docompose.ui.theme.*
import com.example.to_docompose.util.RequestState

@ExperimentalMaterialApi
@Composable
fun ListContent(
    tasks: RequestState<List<ToDoTask>>,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
  if(tasks is RequestState.Success){
      if(tasks.data.isEmpty()){
          EmptyContent()
      }else{
          DisplayTasks(
              tasks = tasks.data,
              navigateToTaskScreen = navigateToTaskScreen
          )
      }
  }else if(tasks is RequestState.Loading){

  }
}


@ExperimentalMaterialApi
@Composable
fun DisplayTasks(
    tasks: List<ToDoTask>,
    navigateToTaskScreen: (taskId: Int) -> Unit
){
    LazyColumn(){
        items(
            items=tasks,
            key= { task ->
                task.id
            }
        ){ task->
            TaskItem(
                todoTask = task,
                navigateToTaskScreen =  navigateToTaskScreen
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun TaskItem(
    todoTask: ToDoTask,
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.taskItemBackgroundColor,
        shape = RectangleShape,
        elevation = TASK_ITEM_ELEVATION,
        onClick = {
            navigateToTaskScreen(todoTask.id)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(all = LARGE_PADDING)
                .fillMaxWidth()
        ) {
            Row() {
                Text(
                    modifier = Modifier.weight(8f),
                    text= todoTask.title,
                    color = MaterialTheme.colors.taskItemTextColor,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Canvas(
                        modifier = Modifier

                            .size(PRIORITY_INDICATOR_SIZE),
                        onDraw = {
                            drawCircle(
                                color = todoTask.priority.color
                            )

                        }
                    )
                }

            }
            Text(
                modifier =Modifier.fillMaxWidth(),
                text = todoTask.description,
                color= MaterialTheme.colors.taskItemTextColor ,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,

            )

        }

    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun TaskItemPreview(){
    TaskItem(
        todoTask = ToDoTask(
            id = 1,
            title = "Todo Task Example",
            description = "Some RANDOM text",
            priority = Priority.MEDIUM,
        ),
        navigateToTaskScreen = {}
    )
}