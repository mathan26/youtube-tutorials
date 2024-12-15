import axios from 'axios';
import { redirect } from 'react-router';

export const getAllProducts = async () => {
    const response = await axios.get('https://dummyjson.com/products');
    return response.data;
};

export const getSingleProduct = async ({ params }) => {
    const response = await axios.get(`https://dummyjson.com/products/${params.id}`);
    return response.data;
};

export const getProductByCategory = async (categoryName) => {
    const response = await axios.get(`https://dummyjson.com/products/category/${categoryName}`);
    return response.data;
};

export const submitForm = async ({ request }) => {
    const formData = await request.formData();
    const name = formData.get('search');
    return redirect(`/products/search?search=${name}`);
};
