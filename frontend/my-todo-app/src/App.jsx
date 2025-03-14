import { useEffect, useState } from 'react';
import { fetchTodos } from "./api/todoAPi";
import reactLogo from './assets/react.svg';
import viteLogo from '/vite.svg';
import './App.css';

function App() {
  const [tasks, setTasks] = useState([]);


  useEffect(() => {
            fetchTodos().then(setTasks).catch((error) => console.error("Error fetching tasks.", error) );
       }, []);

      return (

              <div>
                  <h1>To-Do List</h1>
                  <ul>
                      {tasks.map((task) => (
                          <li key={task.id}>{task.title}</li>
                      ))}
                  </ul>
              </div>

            );
}

export default App;
