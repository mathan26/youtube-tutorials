import React from "react";
import { useNavigate, useRouteError } from "react-router";

const ErrorPage = () => {
  const { data, status, statusText } = useRouteError();
  const navigate = useNavigate();

  console.log(data);
  console.log(status);
  console.log(statusText);
  return (
    <div className="flex flex-col justify-center items-center w-full min-h-screen">
      <h1>Oops! Something went wrong..</h1>
      <h1>
        {status} - {statusText}
      </h1>
      <button
        className="bg-blue-500 text-white rounded px-2 py-3"
        onClick={() => {
          navigate("/");
        }}
      >
        Go to Home
      </button>
    </div>
  );
};

export default ErrorPage;
