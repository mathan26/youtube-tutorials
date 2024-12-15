import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router';
import { getProductByCategory } from '../../api/products-api.js';

const ProductCategory = () => {
    const { value } = useParams();
    const [category, setCategory] = useState([]);

    useEffect(() => {
        const fetechCategories = async () => {
            const { products } = await getProductByCategory(value);
            setCategory(products);
        };
        fetechCategories();
    }, [value]);

    return (
        <>
            <h1 className="font-bold text-3xl text-purple-500 text-center mb-4">
                List of {value} related products...
            </h1>
            <div className="flex flex-wrap gap-6 p-4 justify-center items-center">
                {category.map((product, index) => (
                    <div
                        key={index}
                        className="flex flex-col items-center bg-white shadow-md rounded-lg overflow-hidden w-full sm:w-1/2 lg:w-1/3 xl:w-1/4 p-4"
                    >
                        <img
                            src={product.thumbnail} // Assuming product has an image field
                            alt={product.title}
                            className="w-full h-48 object-cover rounded-md mb-4"
                        />
                        <h3 className="text-lg font-semibold text-gray-800 mb-2">
                            {product.title}
                        </h3>
                        <p className="text-sm text-gray-600 mb-2">{product.description}</p>
                        <div className="flex justify-between items-center w-full">
                            <span className="text-xl font-bold text-blue-600">
                                ${product.price}
                            </span>
                            <button className="bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-600 transition duration-300">
                                Add to Cart
                            </button>
                        </div>
                    </div>
                ))}
            </div>
        </>
    );
};

export default ProductCategory;
