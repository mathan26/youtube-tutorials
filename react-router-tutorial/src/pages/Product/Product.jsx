import React from 'react';
import { Link, useLoaderData } from 'react-router';

const Product = () => {
    const { products } = useLoaderData();

    return (
        <div className="min-h-screen bg-gray-50">
            <div className="container mx-auto py-8">
                <h1 className="text-3xl font-bold text-gray-800 mb-6">Products</h1>
                <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
                    {products.map((product) => (
                        <Link
                            to={`/products/${product.id}`}
                            key={product.id}
                            className="block bg-white shadow-md rounded-lg overflow-hidden hover:shadow-lg transition"
                        >
                            <img
                                src={product.thumbnail}
                                alt={product.title}
                                className="w-full h-48 object-cover"
                            />
                            <div className="p-4 text-center">
                                <h2 className="text-lg font-bold text-gray-800">{product.title}</h2>
                                <p className="text-blue-500 font-semibold">${product.price}</p>
                            </div>
                        </Link>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default Product;
