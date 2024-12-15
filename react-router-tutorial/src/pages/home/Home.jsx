import React from 'react';
import { Form, Link } from 'react-router';

const Home = () => {
    const categories = [
        'beauty',
        'fragrances',
        'furniture',
        'groceries',
        'home-decoration',
        'kitchen-accessories',
        'laptops',
        'mens-shirts',
        'mens-shoes',
        'mens-watches',
        'mobile-accessories',
        'motorcycle',
        'skin-care',
        'smartphones',
        'sports-accessories',
        'sunglasses',
        'tablets',
        'tops',
        'vehicle',
        'womens-bags',
        'womens-dresses',
        'womens-jewellery',
        'womens-shoes',
        'womens-watches',
    ];

    return (
        <div className="min-h-screen bg-gray-50">
            {/* Banner */}
            <div className="w-full h-64 bg-blue-500 flex items-center justify-center text-white">
                <h1 className="text-4xl font-bold">Welcome to React Router Tutorial</h1>
            </div>

            <div className="w-full p-4 bg-gray-100">
                <Form
                    className="flex flex-col sm:flex-row items-center justify-between gap-4"
                    method="post"
                >
                    <input
                        type="text"
                        name="search"
                        placeholder="Search a product: example: Laptop, smartphones ..."
                        className="flex-grow p-3 border border-gray-300 rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
                    />
                    <input
                        type="submit"
                        value="Search"
                        className="bg-blue-500 text-white px-6 py-3 rounded-lg hover:bg-blue-600 transition duration-300 shadow-sm cursor-pointer"
                    />
                </Form>
            </div>

            {/* Categories */}
            <div className="container mx-auto py-8">
                <h2 className="text-2xl font-bold text-gray-800 mb-6">Shop by Category</h2>
                <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
                    {categories.map((category, index) => (
                        <Link
                            to={`/products/category/${category.toLowerCase()}`}
                            key={index}
                            className="relative flex items-center justify-center bg-gradient-to-br from-blue-500 to-indigo-500 text-white shadow-lg rounded-lg p-6 hover:from-blue-600 hover:to-indigo-600 transition-all duration-300 group"
                        >
                            <p className="text-xl font-bold group-hover:scale-110 transform transition-transform">
                                {category.toUpperCase()}
                            </p>
                            <div className="absolute inset-0 border-2 border-transparent rounded-lg group-hover:border-white transition-all duration-300"></div>
                        </Link>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default Home;
