package com.example.to_docompose.ui.screens.task


import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.to_docompose.R
import com.example.to_docompose.data.models.Priority
import com.example.to_docompose.data.models.ToDoTask
import com.example.to_docompose.ui.theme.topAppbarBackgroundColor
import com.example.to_docompose.ui.theme.topAppbarContentColor
import com.example.to_docompose.util.Action


@Composable
fun TaskAppBar(
    task: ToDoTask?,
    navigateToListScreen: (Action) -> Unit,
) {
    if(task == null){
        NewTaskAppBar(navigateToListScreen = navigateToListScreen)
    }else{
        ExistingTaskAppBar(selectedTask = task!!, navigateToListScreen = navigateToListScreen )
    }

}

@Composable
fun NewTaskAppBar(
    navigateToListScreen: (Action) -> Unit,

    ) {
    TopAppBar(
        navigationIcon = {
            BackAction(onBackClicked = navigateToListScreen)
        },
        title = {
            Text(
                text = stringResource(id = R.string.add_task),
                color = MaterialTheme.colors.topAppbarContentColor,


                )
        },
        backgroundColor = MaterialTheme.colors.topAppbarBackgroundColor,
        actions = {
            AddAction(onAddClicked = navigateToListScreen)
        }


    )
}

@Composable
fun BackAction(
    onBackClicked: (Action) -> Unit,

    ) {
    IconButton(onClick = { onBackClicked(Action.NO_ACTION) }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(id = R.string.back_arrow),
            tint = MaterialTheme.colors.topAppbarContentColor
        )


    }

}

@Composable
fun AddAction(
    onAddClicked: (Action) -> Unit,

    ) {
    IconButton(onClick = { onAddClicked(Action.ADD) }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = "Add Task",
            tint = MaterialTheme.colors.topAppbarContentColor
        )


    }

}


@Composable
fun ExistingTaskAppBar(
    selectedTask: ToDoTask,
    navigateToListScreen: (Action) -> Unit,

    ) {
    TopAppBar(
        navigationIcon = {
            CloseAction(onCloseClicked = navigateToListScreen)
        },
        title = {
            Text(
                text = selectedTask.title,
                color = MaterialTheme.colors.topAppbarContentColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,


                )
        },
        backgroundColor = MaterialTheme.colors.topAppbarBackgroundColor,
        actions = {
            DeleteAction(onDeleteClicked = navigateToListScreen)
            UpdateAction(onUpdateClicked = navigateToListScreen)
        }


    )
}


@Composable
fun CloseAction(
    onCloseClicked: (Action) -> Unit,

    ) {
    IconButton(onClick = { onCloseClicked(Action.NO_ACTION) }) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = stringResource(id = R.string.close_icon),
            tint = MaterialTheme.colors.topAppbarContentColor
        )


    }

}


@Composable
fun DeleteAction(
    onDeleteClicked: (Action) -> Unit,

    ) {
    IconButton(onClick = { onDeleteClicked(Action.DELETE) }) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.delete_icon),
            tint = MaterialTheme.colors.topAppbarContentColor
        )


    }

}

@Composable
fun UpdateAction(
    onUpdateClicked: (Action) -> Unit,

    ) {
    IconButton(onClick = { onUpdateClicked(Action.UPDATE) }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(id = R.string.update_icon),
            tint = MaterialTheme.colors.topAppbarContentColor
        )


    }

}


@Composable
@Preview
fun NewTaskAppBarPreview() {
    NewTaskAppBar(
        navigateToListScreen = {}
    )
}

@Composable
@Preview
fun ExistingTaskAppBarPreview() {
    ExistingTaskAppBar(
        selectedTask = ToDoTask(
            id = 1,
            title = "Preview Task",
            description = "Description Preview Task",
            priority = Priority.MEDIUM
        ),
        navigateToListScreen = {}
    )
}