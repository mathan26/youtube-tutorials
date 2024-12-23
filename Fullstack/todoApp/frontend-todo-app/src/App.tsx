import React, { useEffect, useState } from "react";
import TodoForm from "./components/TodoForm";
import { createTodo, deleteTodoById, getAllTodos, updateTodo } from "./todoApi";
import TodoList from "./components/TodoList";
import { Todo } from "./components/type";

type addFormProps = {
  title: string;
  completed: boolean;
};

const App = () => {
  const [todos, setTodos] = useState([]);

  useEffect(() => {
    const getInitialTodos = async () => {
      const response = await getAllTodos();
      console.log(response.data);
      setTodos(response.data);
    };

    getInitialTodos();
  }, []);

  const handleCreateTodo = async (todo: addFormProps) => {
    //backend call for Adding todo

    const response = await createTodo(todo);
    console.log(response);
    setTodos([...todos, response.data]);
  };

  const handleUpdateTodo = async (id: number, todo: Todo) => {
    console.log("on update called");
    const updatedTodo = { ...todo, completed: !todo.completed };
    const response = await updateTodo(id, updatedTodo);
    setTodos(
      todos.map((todo) =>
        todo.id === id ? { ...todo, completed: !todo.completed } : todo
      )
    );
  };

  const handleDeleteById = (id: number) => {
    console.log("delete called");
    deleteTodoById(id);
    setTodos(todos.filter((todo) => todo.id !== id));
  };

  return (
    <div className="min-h-screen flex justify-center items-center bg-gray-100">
      <div className="max-w-md w-full space-y-6">
        {/* form */}
        <div className="bg-white p-8 rounded shadow-lg">
          <h1 className="text-2xl font-bold text-center mb-4">Task Manager</h1>
          <TodoForm onCreateTodo={handleCreateTodo} />
        </div>
        {/* Todolist rendering */}

        <div className="bg-white p-8 rounded-lg shadow-lg">
          <h2 className="text-2xl font-semibold mb-4">Things to do...</h2>
          <TodoList
            todoList={todos}
            onUpdateTodo={handleUpdateTodo}
            onDelete={handleDeleteById}
          />
        </div>
      </div>
    </div>
  );
};

export default App;
