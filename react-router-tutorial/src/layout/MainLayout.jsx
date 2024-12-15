import React from 'react';
import Navbar from '../component/Navbar.jsx';
import { Outlet, useNavigation } from 'react-router';

const MainLayout = () => {
    const navigation = useNavigation();
    return (
        <div className="flex flex-col min-h-screen">
            <Navbar />
            {navigation.state === 'loading' ? (
                <>
                    <div className="flex justify-center items-center h-64">
                        <div className="animate-spin rounded-full h-16 w-16 border-t-4 border-blue-500"></div>
                    </div>
                </>
            ) : (
                <Outlet />
            )}
        </div>
    );
};

export default MainLayout;
