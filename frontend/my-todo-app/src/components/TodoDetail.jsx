import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { fetchTodo, deleteTodo } from "../api/todoAPi";

const TodoDetail = () => {
  let { id } = useParams();
  const [task, setTask] = useState([]);
  const navigate = useNavigate();

  const navigateBack = () => {
    navigate("/");
  };

  const handleDeleteTask = async () => {
    try {
      await deleteTodo(task.id);
      alert("삭제되었습니다.");
      navigate("/");
    } catch (error) {
      console.error("삭제 중 오류 발생", error);
    }
  };

  useEffect(() => {
    fetchTodo(id)
      .then((data) => setTask(data))
      .catch((error) => {
        console.error("Error : ", error);
      });
  }, [id]);

  return (
    <div>
      <button onClick={navigateBack}>뒤로가기</button>
      <h2>할 일 상세 페이지</h2>
      {task ? (
        <div>
          <p>할 일 ID : {task.id}</p>
          <p>제목 : {task.title}</p>
          <p>설명 : {task.descrition}</p>
          <button onClick="">수정</button>
          <button onClick={handleDeleteTask}>삭제</button>
        </div>
      ) : (
        <p>로딩 중....</p>
      )}
    </div>
  );
};

export default TodoDetail;
