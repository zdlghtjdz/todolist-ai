
const API_BASE_URL = "http://localhost:8080"

export const fetchTodos = async () => {

    const response = await fetch('${API_BASE_URL}/tasks');
    if(!response.ok) {
        throw new Error("Failed to fetch tasks");
    }
    return response.json();
}