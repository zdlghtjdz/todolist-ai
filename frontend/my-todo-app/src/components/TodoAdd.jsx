import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { addTodo } from "../api/todoAPi";

const TodoAdd = () => {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const navigate = useNavigate();

  const handleNavigateBack = () => {
    navigate("/");
  };

  const handleAddTask = async () => {
    console.log(title);
    if (!title.trim()) return;
    const newTask = {
      title: title,
      description: description,
      status: "PENDING",
      dueDate: null,
    };
    try {
      await addTodo(newTask);
    } catch (e) {
      console.error(e);
    }
    navigate("/");
  };

  return (
    <div>
      <button onClick={handleNavigateBack}>뒤로 가기</button>
      <p>새로운 할 일</p>
      <p>제목</p>
      <input
        id="title"
        onChange={(e) => {
          setTitle(e.target.value);
        }}
      ></input>
      <p>설명</p>
      <textarea
        id="description"
        onChange={(e) => {
          setDescription(e.target.value);
        }}
      ></textarea>
      <button onClick={handleAddTask}>추가</button>
    </div>
  );
};

export default TodoAdd;
