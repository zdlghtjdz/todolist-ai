import React, {useEffect, useState } from "react";
import { fetchTodos, addTodo, deleteTodo } from "../api/todoAPi";

const TodoList = () => {

       const [tasks, setTasks] = useState([]);
       const [newTaskTitle, setNewTaskTitle] = useState("");

       useEffect(() => {
            fetchTodos().then(setTasks).catch((error) => console.error("Error : ", error));
       }, []);

       const handleAddTask = async () => {

            if(!newTaskTitle.trim()) return;
            const newTask = { title : newTaskTitle, description : "", status : "pending", dueDate : null };
            const addedTask = await addTodo(newTask);
            setTasks([...tasks, addedTask]);
            setNewTaskTitle("");
       };

       return (
           <div>
                <h2> 할 일 목록 </h2>
                <input
                    type="text"
                    value={newTaskTitle}
                    onChange={(e) => setNewTaskTitle(e.target.value)}
                    placeholder="할 일을 입력하세요"
                />
                <button onClick={handleAddTask}>추가</button>
                <ul>
                    {tasks.map((task) => (
                          <li key={task.id}>
                                {task.title}
                          </li>
                    ))}
                </ul>
           </div>
       );
};