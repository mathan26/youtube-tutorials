import React from "react";

const Product= () => {

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


    return (
        <div className="min-h-screen bg-gray-50">
            <div className="container mx-auto py-8">
                <h1 className="text-3xl font-bold text-gray-800 mb-6">Products</h1>
                <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
                    {products.map((product) => (
                        <a
                            href={`/products/${product.id}`}
                            key={product.id}
                            className="block bg-white shadow-md rounded-lg overflow-hidden hover:shadow-lg transition"
                        >
                            <img
                                src={product.image}
                                alt={product.name}
                                className="w-full h-48 object-cover"
                            />
                            <div className="p-4">
                                <h2 className="text-lg font-bold text-gray-800">{product.name}</h2>
                                <p className="text-blue-500 font-semibold">${product.price}</p>
                            </div>
                        </a>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default Product
