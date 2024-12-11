import React from "react";

const Login = () => {
    return (
        <div className="bg-gray-100 flex items-center justify-center min-h-screen">
            <div className="w-full max-w-md p-6 bg-white rounded-lg shadow-md">
                <h1 className="text-center font-bold text-3xl">Login</h1>
                <form className="mt-6">
                    <div className="mb-4">
                        <label htmlFor="username" className="block text-sm font-medium text-gray-600">User name</label>
                        <input type="text" name="username" id="username" placeholder="Username"
                               className="w-full mt-2 p-2 border rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 text-gray-800"/>
                    </div>

                    <div className="mb-4">
                        <label htmlFor="password" className="block text-sm font-medium text-gray-600">Password</label>
                        <input
                            type="password"
                            id="password"
                            name="password"
                            className="w-full mt-2 p-2 border rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 text-gray-800"
                            placeholder="Enter your password"
                            required
                        />
                    </div>
                    <div>
                        <button
                            type="submit"
                            className="w-full px-4 py-2 text-white bg-indigo-500 rounded-md hover:bg-indigo-600 focus:outline-none focus:ring focus:ring-indigo-300"
                        >
                            Login
                        </button>
                    </div>
                </form>
            </div>

        </div>
    );
};

export default Login;
