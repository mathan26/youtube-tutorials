import React from "react";
import { Todo } from "./type";
import TodoItem from "./TodoItem";

interface TodoListProps {
  todoList: Todo[];
  onUpdateTodo: (todo: Todo, id: number) => void;
  onDelete: (id: number) => void;
}
const TodoList = ({ todoList, onUpdateTodo, onDelete }: TodoListProps) => {
  return (
    <ul>
      {todoList.map((todo) => (
        <TodoItem
          key={todo.id}
          todo={todo}
          onUpdateTodo={onUpdateTodo}
          onDelete={onDelete}
        />
      ))}
    </ul>
  );
};

export default TodoList;
