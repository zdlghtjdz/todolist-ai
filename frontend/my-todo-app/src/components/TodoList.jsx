import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { fetchTodos, addTodo, deleteTodo } from "../api/todoAPi";

const TodoList = () => {
  const [tasks, setTasks] = useState([]);
  const [newTaskTitle, setNewTaskTitle] = useState("");

  useEffect(() => {
    fetchTodos()
      .then(setTasks)
      .catch((error) => console.error("Error : ", error));
  }, []);

  const handleAddTask = async () => {
    if (!newTaskTitle.trim()) return;
    const newTask = {
      title: newTaskTitle,
      description: "",
      status: "PENDING",
      dueDate: null,
    };
    const addedTask = await addTodo(newTask);
    setTasks([...tasks, addedTask]);
    setNewTaskTitle("");
  };

  return (
    <div>
      <h2> 할 일 목록 </h2>
      {tasks.length > 0 ? (
        <ul>
          {tasks.map((task) => (
            <li key={task.id}>
              <Link to={`/todo/${task.id}`}>{task.title}</Link>
            </li>
          ))}
        </ul>
      ) : (
        <p>할 일이 없습니다.</p>
      )}
    </div>
  );
};

export default TodoList;
