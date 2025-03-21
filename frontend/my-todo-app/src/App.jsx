import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { useEffect, useState } from "react";
import { fetchTodos, deleteTodo } from "./api/todoAPi";
import TodoList from "./components/TodoList";
import TodoDetail from "./components/TodoDetail";
import TodoAdd from "./components/TodoAdd";
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

  return (
    <Router>
      <div>
        <h1>To-Do List</h1>
        <Routes>
          <Route path="/" element={<TodoList />} />
          <Route path="/todo/:id" element={<TodoDetail />} />
          <Route path="/todo/new" element={<TodoAdd />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
