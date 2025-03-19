import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { fetchTodo } from "../api/todoAPi";

const TodoDetail = () => {
  let { id } = useParams();
  const [task, setTask] = useState([]);

  useEffect(() => {
    fetchTodo(id)
      .then(setTask())
      .catch((error) => {
        console.error("Error : ", error);
      });
  }, []);

  return (
    <div>
      <h2>할 일 상세 페이지</h2>
      <p>할 일 ID : {id}</p>
      <textarea></textarea>
      <button onclick="">수정</button>
      <button onclick="">삭제</button>
    </div>
  );
};

export default TodoDetail;
