
//const API_BASE_URL = "http://localhost:8080";
const API_BASE_URL = import.meta.env.VITE_API_BASE_URL;

export const addTodo = async (newTask) => {
    const response = await fetch(`${API_BASE_URL}/tasks`, {
        method : "POST",
        headers : { "Content-Type" : "application/json"},
        body : JSON.stringify(newTask)
    });

    if (!response.ok) {
        throw new Error("할 일 추가에 실패했습니다.");
    }

    return response.json();
};

export const deleteTodo = async (taskId) => {

    const response = await fetch(`${API_BASE_URL}/tasks/${taskId}`, {
        method : "DELETE"
    });


    if(!response.ok)
        throw new Error("할 일 삭제에 실패했습니다.");
};

export const fetchTodos = async () => {
    console.log("API_BASE_URL", API_BASE_URL);
    const response = await fetch(`${API_BASE_URL}/tasks`);
    if(!response.ok) {
        throw new Error("Failed to fetch tasks");
    }
    return response.json();
}