import React from 'react';
import Navbar from "../component/Navbar.jsx";
import {Outlet} from "react-router";

const MainLayout = () => {
    return (
        <div className="flex flex-col min-h-screen">
            <Navbar/>
            <Outlet/>
        </div>
    );
};

export default MainLayout;