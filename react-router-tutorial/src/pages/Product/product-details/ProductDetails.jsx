import React from "react";
import { Link, useLoaderData } from "react-router";
import { useCart } from "../../../context/CartContext.jsx";
// import { useParams, Link } from "react-router-dom";
// import { products } from "./mockData";

const ProductDetails = () => {
  const product = useLoaderData();
  const { state, setCart } = useCart();
  console.log(state);
  return (
    <div className="min-h-screen bg-gray-50">
      <div className="container mx-auto py-12 px-4">
        <Link
          to="/products"
          className="text-blue-500 hover:underline mb-4 inline-block"
        >
          &larr; Back to Products
        </Link>
        <div className="flex flex-col lg:flex-row gap-8">
          <div className="w-full lg:w-1/2">
            <img
              src={product.thumbnail}
              alt={product.name}
              className="w-full h-auto rounded-lg shadow-lg"
            />
          </div>
          <div className="w-full lg:w-1/2">
            <h1 className="text-3xl font-bold text-gray-800">
              {product.title}
            </h1>
            <p className="text-sm text-gray-500 mt-2">
              Category: {product.category}
            </p>
            <p className="text-2xl font-semibold text-blue-500 mt-4">
              ${product.price}
            </p>
            <p className="text-gray-600 mt-6 leading-relaxed">
              {product.description}
            </p>
            <button
              className="mt-8 px-6 py-3 bg-blue-500 text-white font-bold rounded-lg hover:bg-blue-600 transition"
              onClick={() => {
                setCart({ title: product.title, price: product.price });
              }}
            >
              Add to Cart
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ProductDetails;
