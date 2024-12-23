import axios from "axios";
import { Todo } from "./components/type";

export const createTodo = async (todo: addFormProps) => {
  const response = axios.post<Promise<Todo>>("/api/todos", todo);
  return response;
};

export const getAllTodos = async () => {
  const response = axios.get("/api/todos");
  return response;
};

export const updateTodo = async (id, todo) => {
  const response = axios.put(`/api/todos/${id}`, todo);
  return response;
};

export const deleteTodoById = async (id) => {
  axios.delete(`/api/todos/${id}`);
};
