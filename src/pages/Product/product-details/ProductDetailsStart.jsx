import React from "react";
// import { useParams, Link } from "react-router-dom";
// import { products } from "./mockData";

const ProductDetails = () => {

    const products = [
        {
            id: 1,
            name: "Wireless Headphones",
            image: "/images/headphones.jpg",
            price: 99.99,
            description: "High-quality sound with long-lasting battery life.",
            category: "Electronics",
        },
        {
            id: 2,
            name: "Smartphone",
            image: "/images/smartphone.jpg",
            price: 499.99,
            description: "A sleek and powerful smartphone with excellent features.",
            category: "Electronics",
        },
        {
            id: 3,
            name: "Running Shoes",
            image: "/images/shoes.jpg",
            price: 69.99,
            description: "Comfortable running shoes for daily workouts.",
            category: "Sports",
        },
    ];
    const  id  = 3;
    const product = products.find((p) => p.id === parseInt(id));

    if (!product) {
        return (
            <div className="min-h-screen bg-gray-50 flex items-center justify-center">
                <h1 className="text-2xl text-gray-800">Product not found!</h1>
            </div>
        );
    }

    return (
        <div className="min-h-screen bg-gray-50">
            <div className="container mx-auto py-12 px-4">
                <a href="/products" className="text-blue-500 hover:underline mb-4 inline-block">
                    &larr; Back to Products
                </a>
                <div className="flex flex-col lg:flex-row gap-8">
                    <div className="w-full lg:w-1/2">
                        <img
                            src={product.image}
                            alt={product.name}
                            className="w-full h-auto rounded-lg shadow-lg"
                        />
                    </div>
                    <div className="w-full lg:w-1/2">
                        <h1 className="text-3xl font-bold text-gray-800">{product.name}</h1>
                        <p className="text-sm text-gray-500 mt-2">Category: {product.category}</p>
                        <p className="text-2xl font-semibold text-blue-500 mt-4">${product.price}</p>
                        <p className="text-gray-600 mt-6 leading-relaxed">{product.description}</p>
                        <button className="mt-8 px-6 py-3 bg-blue-500 text-white font-bold rounded-lg hover:bg-blue-600 transition">
                            Add to Cart
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ProductDetails;
