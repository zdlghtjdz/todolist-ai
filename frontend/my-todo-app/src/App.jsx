import { useEffect, useState } from "react";
import { fetchTodos, deleteTodo } from "./api/todoAPi";
import TodoList from "./components/TodoList";
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import "./App.css";

function App() {
  const [tasks, setTasks] = useState([]);

  useEffect(() => {
    fetchTodos()
      .then(setTasks)
      .catch((error) => console.error("Error fetching tasks.", error));
  }, []);

  const handleDelete = async function () {
    try {
      await deleteTodo(task.id);
    } catch (error) {
      console.error("Error fetching tasks.", error);
    }
  };

  return (
    <div>
      <h1>To-Do List</h1>
      <ul>
        {tasks.map((task) => (
          <>
            <li key={task.id}>
              {task.title} - {task.status}
              <button onClick={handleDelete()}>삭제</button>
            </li>
          </>
        ))}
      </ul>
      <TodoList />
    </div>
  );
}

export default App;
