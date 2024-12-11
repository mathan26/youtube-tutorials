import React from "react";

const Home = () => {

  const categories=[
    { "id": 1, "name": "Electronics", "image": "/images/electronics.jpg" },
    { "id": 2, "name": "Fashion", "image": "/images/fashion.jpg" },
    { "id": 3, "name": "Home & Furniture", "image": "/images/home-furniture.jpg" },
    { "id": 4, "name": "Sports", "image": "/images/sports.jpg" },
    { "id": 5, "name": "Books", "image": "/images/books.jpg" }
  ]

  return (
      <div className="min-h-screen bg-gray-50">
        {/* Banner */}
        <div className="w-full h-64 bg-blue-500 flex items-center justify-center text-white">
          <h1 className="text-4xl font-bold">Welcome to React Router Tutorial</h1>
        </div>

        {/* Categories */}
        <div className="container mx-auto py-8">
          <h2 className="text-2xl font-bold text-gray-800 mb-6">Shop by Category</h2>
          <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
            {categories.map((category) => (
                <div
                    key={category.id}
                    className="relative bg-white shadow-md rounded-lg overflow-hidden group"
                >
                  <img
                      src={category.image}
                      alt={category.name}
                      className="w-full h-48 object-cover group-hover:scale-105 transition-transform"
                  />
                  <div className="absolute inset-0 bg-black bg-opacity-25 opacity-0 group-hover:opacity-100 transition-opacity flex items-center justify-center">
                    <p className="text-white text-lg font-semibold">{category.name}</p>
                  </div>
                </div>
            ))}
          </div>
        </div>
      </div>
  );
};

export default Home;
