import React from "react";
import Home from "./pages/home/Home";
import Product from "./pages/Product/Product";
import Cart from "./pages/cart/Cart.jsx";
import {
  createBrowserRouter,
  createRoutesFromElements,
  Route,
  RouterProvider,
} from "react-router";
import Navbar from "./component/Navbar.jsx";
import MainLayout from "./layout/MainLayout.jsx";
import ErrorPage from "./pages/ErrorPage.jsx";
import { getAllProducts, getSingleProduct } from "./api/products-api.js";
import ProductDetails from "./pages/Product/product-details/ProductDetails.jsx";

const App = () => {
  const routes = createBrowserRouter(
    createRoutesFromElements(
      <Route element={<MainLayout />} errorElement={<ErrorPage />}>
        <Route path="/" element={<Home />} />
        <Route path="/products" element={<Product />} loader={getAllProducts} />
        <Route
          path="/products/:id"
          element={<ProductDetails />}
          loader={getSingleProduct}
        />
        <Route path="/cart" element={<Cart />} />
      </Route>,
    ),
  );

  return <RouterProvider router={routes} />;
};

export default App;
