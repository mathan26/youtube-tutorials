import React, { useState } from "react";

type addFormProps = {
  title: string;
  completed: boolean;
};

interface TodoFormProps {
  onCreateTodo: (todo: addFormProps) => void;
}

const TodoForm = ({ onCreateTodo }: TodoFormProps) => {
  const [title, setTitle] = useState("");

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (title.trim()) {
      onCreateTodo({ title, completed: false });
      setTitle("");
    } else {
      alert("Please enter todo name");
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div className="mb-2">
        <input
          type="text"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          placeholder="Add a new task"
          className="w-full p-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-blue-500"
        />
      </div>
      <button
        type="submit"
        className="w-full p-2 bg-fuchsia-600 text-white rounded hover:bg-fuchsia-700"
      >
        Add task
      </button>
    </form>
  );
};

export default TodoForm;
