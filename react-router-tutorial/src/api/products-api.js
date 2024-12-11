import axios from "axios";

export const getAllProducts = async () => {
  const response = await axios.get("https://dummyjson.com/products");
  console.log(response);
  return response.data;
};

export const getSingleProduct = async ({ params }) => {
  console.log(params);
  const response = await axios.get(
    `https://dummyjson.com/products/${params.id}`,
  );
  console.log(response);
  return response.data;
};
