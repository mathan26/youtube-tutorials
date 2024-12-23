import React, { Suspense } from "react";
import Home from "./pages/home/Home";
import {
  createBrowserRouter,
  createRoutesFromElements,
  Route,
  RouterProvider,
} from "react-router";
import MainLayout from "./layout/MainLayout.jsx";
import ErrorPage from "./pages/ErrorPage.jsx";
import {
  getAllProducts,
  getSingleProduct,
  submitForm,
} from "./api/products-api.js";
import ProductCategory from "./pages/Product/ProductCategory.jsx";
import ProductSearch from "./pages/Product/ProductSearch.jsx";
import { CardProvider } from "./context/CartContext.jsx";
import ProtectedRoute from "./component/ProtectedRoute.jsx";

const Product = React.lazy(() => import("./pages/Product/Product.jsx"));
const ProductDetails = React.lazy(
  () => import("./pages/Product/product-details/ProductDetails.jsx")
);
const Cart = React.lazy(() => import("./pages/cart/Cart.jsx"));

const App = () => {
  const routes = createBrowserRouter(
    createRoutesFromElements(
      <>
        <Route element={<h1>you need to login</h1>} path="/login"></Route>
        <Route
          element={
            <ProtectedRoute>
              <MainLayout />
            </ProtectedRoute>
          }
          errorElement={<ErrorPage />}
        >
          <Route path="/" element={<Home />} action={submitForm} />
          <Route
            path="/products"
            element={
              <Suspense
                fallback={<h1>Fallback component is loading please wait.</h1>}
              >
                <Product />
              </Suspense>
            }
            loader={getAllProducts}
          />
          <Route
            path="/products/category/:value"
            element={<ProductCategory />}
          />
          <Route path="/products/search" element={<ProductSearch />} />

          <Route
            path="/products/:id"
            element={<ProductDetails />}
            loader={getSingleProduct}
          />
          <Route path="/cart" element={<Cart />} />
        </Route>
      </>
    )
  );

  return (
    <CardProvider>
      <RouterProvider router={routes} />
    </CardProvider>
  );
};

export default App;
