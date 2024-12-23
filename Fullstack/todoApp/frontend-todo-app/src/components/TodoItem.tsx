import { Todo } from "./type";

interface TodoListProps {
  todo: Todo;
  onUpdateTodo: (todo: Todo, id: number) => void;
  onDelete: (id: number) => void;
}

const TodoItem = ({ todo, onUpdateTodo, onDelete }: TodoListProps) => {
  return (
    <li className="flex items-center  my-2" key={todo.id}>
      <input
        type="checkbox"
        checked={todo.completed}
        onChange={() => onUpdateTodo(todo.id, todo)}
        className="h-5 w-5 text-blue-500"
      />
      <span
        className={`ml-3  ${
          todo.completed
            ? "line-through text-gray-500 "
            : "text-purple-500 font-semibold"
        }`}
      >
        {todo.title}
      </span>
      <button
        onClick={() => onDelete(todo.id)}
        className="text-white bg-red-400 rounded p-2  hover:text-red-700 ml-auto"
      >
        Delete
      </button>
    </li>
  );
};

export default TodoItem;
