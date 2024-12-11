import React from "react";
import {Link, NavLink} from "react-router";

const Navbar = () => {
    return (<nav className="bg-blue-600 shadow-md">
        <div className="container mx-auto flex items-center justify-between px-6 py-4">
            <div className="text-3xl font-bold text-white">
                <Link to="/">V-Shopify</Link>
            </div>

            <div className="flex space-x-8 text-lg text-white">
                <NavLink to="/"
                         className={({isActive}) =>
                             isActive ? "hover:text-blue-300 transition duration-300 font-bold underline"
                                 : "hover:text-blue-300 transition duration-300"}>
                    Home
                </NavLink>
                <NavLink to="/products"
                         className={({isActive}) =>
                             isActive ? "hover:text-blue-300 transition duration-300 font-bold underline"
                                 : "hover:text-blue-300 transition duration-300"}
                >
                    Products
                </NavLink>
                <NavLink to="/cart"
                         className={({isActive}) =>
                             isActive ? "hover:text-blue-300 transition duration-300 font-bold underline"
                                 : "hover:text-blue-300 transition duration-300"}
                >
                    Cart
                </NavLink>
            </div>
        </div>
    </nav>);
};

export default Navbar;
