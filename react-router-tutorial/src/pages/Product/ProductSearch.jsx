import React, { useEffect, useState } from 'react';
import { Link, useSearchParams } from 'react-router';
import axios from 'axios';

const ProductSearch = () => {
    const [searchParams] = useSearchParams();
    const searchValue = searchParams.get('search');

    const [data, setData] = React.useState([]);
    useEffect(() => {
        const getSearchResults = async () => {
            const response = await axios.get(
                `https://dummyjson.com/products/search?q=${searchValue}`
            );
            console.log(response.data);
            setData(response.data.products);
        };
        getSearchResults();
    }, [searchValue]);

    return (
        <div>
            <h1>Search Results</h1>
            <div className="min-h-screen bg-gray-50">
                <div className="container mx-auto py-12 px-4">
                    <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
                        {data.length > 0}
                        {data.map((product) => (
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
                                    <h2 className="text-lg font-bold text-gray-800">
                                        {product.title}
                                    </h2>
                                    <p className="text-blue-500 font-semibold">${product.price}</p>
                                </div>
                            </Link>
                        ))}
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ProductSearch;
