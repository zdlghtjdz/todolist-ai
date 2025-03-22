import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { fetchTodos, addTodo } from "../api/todoAPi";

const TodoList = () => {
  const [tasks, setTasks] = useState([]);

  useEffect(() => {
    fetchTodos()
      .then(setTasks)
      .catch((error) => console.error("Error : ", error));
  }, []);

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
      <button>
        <Link to={`/todo/new`}>새로운 할 일 추가</Link>
      </button>
    </div>
  );
};

export default TodoList;
