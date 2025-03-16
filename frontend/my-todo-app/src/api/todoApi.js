
//const API_BASE_URL = "http://localhost:8080";
const API_BASE_URL = import.meta.env.VITE_API_BASE_URL;


export const fetchTodos = async () => {
    console.log("API_BASE_URL", API_BASE_URL);
    const response = await fetch(`${API_BASE_URL}/tasks`);
    if(!response.ok) {
        throw new Error("Failed to fetch tasks");
    }
    return response.json();
}